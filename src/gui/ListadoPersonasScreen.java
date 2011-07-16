package gui;

import persistence.Persistence;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Persona;

public class ListadoPersonasScreen extends MainScreen {

	private Object _selected;
	private ListadoPersonasLista _lista;
	private int _tipo;

	public ListadoPersonasScreen(int tipo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_tipo = tipo;
		if (tipo == 1)
			setTitle("Listado de demandantes");
		else
			setTitle("Listado de demandados");

		_lista = new ListadoPersonasLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevaPersona n = new NuevaPersona(_tipo);
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						addPersona(n.getPersona());
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

		if (tipo == 1)
			_lista.insert(0, "Nuevo demandante");
		else
			_lista.insert(0, "Nuevo demandado");
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
			VerPersona verPersona = new VerPersona((Persona) _lista.get(_lista,
					index));
			UiApplication.getUiApplication().pushModalScreen(
					verPersona.getScreen());
			verPersona.actualizarPersona();
			_lista.delete(index);
			_lista.insert(index, verPersona.getPersona());
			_lista.setSelectedIndex(index);
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel;
			if (_tipo == 1) {
				sel = Dialog.ask("¿Desdea eliminar el demandante?", ask, 1);
			} else {
				sel = Dialog.ask("¿Desdea eliminar el demandado?", ask, 1);
			}
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				int index = _lista.getSelectedIndex();
				try {
					persistence.borrarPersona((Persona) _lista.get(_lista,
							index));
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}

				_lista.delete(index);
			}
		}
	};

	public void addPersona(Object persona) {
		_lista.insert(_lista.getSize(), persona);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
