package gui;

import persistence.Persistence;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Actuacion;
import core.Proceso;

public class ListadoActuacionesScreen extends MainScreen {

	private Object _selected;
	private ListadoActuacionesLista _lista;
	private Proceso _proceso;

	public ListadoActuacionesScreen(Proceso proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_proceso = proceso;

		setTitle("Nueva actuaci�n");

		_lista = new ListadoActuacionesLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevaActuacion n = new NuevaActuacion(_proceso);
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						addActuacion(n.getActuacion());
					} catch (Exception e) {
						return true;
					}
					return true;
				} else {
					_selected = get(_lista, getSelectedIndex());
					UiApplication.getUiApplication().popScreen(getScreen());
					return true;
				}
			}
		};

		_lista.insert(0, "Nueva actuaci�n");
		add(_lista);
	}

	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.get(_lista,
				_lista.getSelectedIndex()))) {
			menu.add(menuVer);
			menu.add(menuDelete);
		}
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			int index = _lista.getSelectedIndex();
			VerActuacion verActuacion = new VerActuacion(
					(Actuacion) _lista.get(_lista, index));
			UiApplication.getUiApplication().pushModalScreen(
					verActuacion.getScreen());
			try {
				verActuacion.actualizarActuacion();
				_lista.delete(index);
				_lista.insert(index, verActuacion.getActuacion());
				_lista.setSelectedIndex(index);
			} catch (Exception e) {
				_lista.delete(index);
			}
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("�Desdea eliminar la actuaci�n?", ask, 1);
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				int index = _lista.getSelectedIndex();
				try {
					persistence.borrarActuacion((Actuacion) _lista.get(_lista,
							index));
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				_lista.delete(index);
			}
		}
	};

	public void addActuacion(Object actuacion) {
		_lista.insert(_lista.getSize(), actuacion);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
