package gui;

import persistence.Persistence;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Proceso;

public class ListadoProcesosScreen extends MainScreen {

	private Object _selected;
	private ListadoProcesosLista _lista;

	public ListadoProcesosScreen() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Listado de procesos");

		_lista = new ListadoProcesosLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevoProceso n = new NuevoProceso();
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						addProceso(n.getProceso());
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

		_lista.insert(0, "Nuevo proceso");

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
			VerProceso verProceso = new VerProceso((Proceso) _lista.get(_lista,
					index));
			UiApplication.getUiApplication().pushModalScreen(
					verProceso.getScreen());
			verProceso.actualizarProceso();
			_lista.delete(index);
			_lista.insert(index, verProceso.getProceso());
			_lista.setSelectedIndex(index);
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Persistence persistence = null;
			try {
				persistence = new Persistence();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			int index = _lista.getSelectedIndex();
			try {
				persistence.borrarProceso((Proceso) _lista.get(_lista, index));
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}

			_lista.delete(index);
		}
	};

	public void addProceso(Object proceso) {
		_lista.insert(_lista.getSize(), proceso);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}