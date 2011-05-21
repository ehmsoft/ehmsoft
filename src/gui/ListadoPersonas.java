package gui;

import core.Persona;
import ehmsoft.ListadoPersonasController;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoPersonas extends MainScreen {

	/**
	 * 
	 */
	ListaListadoPersonas lista;
	ListadoPersonasController controller;
	public ListadoPersonas(final ListadoPersonasController controller) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		this.controller = controller;
		
		if(this.controller.getTipo() == (short)1)
			setTitle("Listado de demandantes");
		else if (this.controller.getTipo() == (short)2)
			setTitle("Listado de demandados");
		else
			setTitle("Listado de personas");
		
		lista = new ListaListadoPersonas()
		{
			protected boolean navigationClick(int status, int time)
			{
				if(getSelectedIndex() == 0)
				{
					controller.lanzarNuevaPersona(controller.getTipo());
				}
				else
				{
					controller.setSelected((Persona)get(this,getSelectedIndex()));
				}
				return true;
			}
		};
		lista.set(this.controller.getPersonas());
		add(lista);
	}
}
