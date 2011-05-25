package gui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoPersonas extends MainScreen {

	private Object selected;
	private ListaListadoPersonas lista;

	public ListadoPersonas(int tipo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		if (tipo == 1)
			setTitle("Listado de demandantes");
		else
			setTitle("Listado de demandados");

		lista = new ListaListadoPersonas() {
			protected boolean navigationClick(int status, int time) {
				selected = get(lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};

		if (tipo == 1)
			lista.insert(0, "Nuevo demandante");
		else
			lista.insert(0, "Nuevo demandado");
		add(lista);
	}

	public void addPersona(Object persona) {
		lista.insert(lista.getSize(), persona);
	}

	public Object getSelected() {
		return selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
