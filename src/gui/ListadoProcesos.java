package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;
import core.Proceso;

public class ListadoProcesos extends MainScreen {

	/**
	 * 
	 */
	private Object _selected;
	private ListadoProcesosLista _lista;

	public ListadoProcesos(Object[] listadoProcesos) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Listado de procesos");

		_lista = new ListadoProcesosLista() {
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		_lista.set(listadoProcesos);
		_lista.insert(0, "Nuevo proceso");

		add(_lista);
		addMenuItem(menuVer);
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			int index = _lista.getSelectedIndex();
			VerProcesoController verProceso = new VerProcesoController(
					(Proceso) _lista.get(_lista, index));
			UiApplication.getUiApplication().pushModalScreen(
					verProceso.getScreen());
			verProceso.actualizarProceso();
			_lista.delete(index);
			_lista.insert(index, verProceso.getProceso());
			_lista.setSelectedIndex(index);
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