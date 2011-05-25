package gui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoJuzgados extends MainScreen {

	private Object selected;
	private ListaListadoJuzgados lista;
	public ListadoJuzgados() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		setTitle("Listado de juzgados");
		
		lista = new ListaListadoJuzgados()
		{
			protected boolean navigationClick(int status, int time)
			{
				selected = get(lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};
	
		lista.insert(0, "Nuevo juzgado");
		add(lista);
	}
	
	public void addJuzgado(Object juzgado) {
		lista.insert(lista.getSize(), juzgado);
	}
	
	public Object getSelected() {
		return selected;
	}
	
	public boolean onClose()
	{
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
