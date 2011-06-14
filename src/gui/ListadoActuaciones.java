package gui;

import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoActuaciones extends MainScreen {

	private Object selected;
	private ListadoActuacionesLista lista;

	public ListadoActuaciones(Vector fuentes) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Listado de actuaciones");

		lista = new ListadoActuacionesLista(fuentes) {
			protected boolean navigationClick(int status, int time) {
				selected = get(lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		lista.insert(0, "Nueva actuación");
		add(lista);
	}

	public ListadoActuaciones() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Nueva actuación");

		lista = new ListadoActuacionesLista() {
			protected boolean navigationClick(int status, int time) {
				selected = get(lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		lista.insert(0, "Nueva actuación");
		add(lista);
	}

	public void addActuacion(Object actuacion) {
		lista.insert(lista.getSize(), actuacion);
	}

	public Object getSelected() {
		return selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
