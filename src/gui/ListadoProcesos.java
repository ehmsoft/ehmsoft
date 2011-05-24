package gui;

import ehmsoft.Main;
import net.rim.device.api.ui.container.MainScreen;


public class ListadoProcesos extends MainScreen {

	/**
	 * 
	 */
	public ListadoProcesos(final Main theApp, Object[] objetos) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Listado de procesos");

		ListaListadoProcesos lista = new ListaListadoProcesos(){
			protected boolean navigationClick(int status, int time)
			{
				Object proceso = (Object)get(this, getSelectedIndex());
				return true;
			}
		};
		
		lista.set(objetos);
		
		add(lista);
	}
}