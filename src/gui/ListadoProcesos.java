package gui;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;


public class ListadoProcesos extends MainScreen {

	/**
	 * 
	 */
	private Object _selected;
	private ListaListadoProcesos _lista;
	public ListadoProcesos(Object[] listadoProcesos) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Listado de procesos");

		_lista = new ListaListadoProcesos(){
			protected boolean navigationClick(int status, int time)
			{
				_selected = get(_lista, getSelectedIndex());
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
		};
		
		_lista.set(listadoProcesos);
		_lista.insert(0, "Nuevo proceso");
		
		add(_lista);
	}
	
	public void addProceso(Object proceso){
		_lista.insert(_lista.getSize(),proceso);
	}
	
	public Object getSelected(){
		return _selected;
	}
	
	public boolean onClose()
	{
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}