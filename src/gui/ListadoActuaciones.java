package gui;

import java.util.Vector;

import core.Actuacion;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoActuaciones extends MainScreen {

	private Object _selected;
	private ListadoActuacionesLista _lista;

	public ListadoActuaciones(Vector fuentes) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Listado de actuaciones");

		_lista = new ListadoActuacionesLista(fuentes) {
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		_lista.insert(0, "Nueva actuación");
		add(_lista);
		addMenuItem(menuVer);
	}

	public ListadoActuaciones() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Nueva actuación");

		_lista = new ListadoActuacionesLista() {
			protected boolean navigationClick(int status, int time) {
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		_lista.insert(0, "Nueva actuación");
		add(_lista);
		addMenuItem(menuVer);
	}
	
	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			int index = _lista.getSelectedIndex();
			VerActuacionController verActuacion = new VerActuacionController((Actuacion) _lista.get(_lista, index));
			UiApplication.getUiApplication().pushModalScreen(verActuacion.getScreen());
			verActuacion.actualizarActuacion();
			_lista.delete(index);
			_lista.insert(index,verActuacion.getActuacion());
			_lista.setSelectedIndex(index);
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
