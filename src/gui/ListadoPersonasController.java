package gui;

import net.rim.device.api.ui.UiApplication;
import core.Persona;
import persistence.Persistence;

import java.util.Enumeration;
import java.util.Vector;

public class ListadoPersonasController {
	
	private int tipo;
	private Persistence persistencia;
	private Object[] personas;
	private Vector vectorPersonas;
	private ListadoPersonas screen;
	
	public ListadoPersonasController(int tipo)
	{
		this.tipo = tipo;
		this.persistencia = new Persistence();
		
		if(tipo == 1)
			this.vectorPersonas = persistencia.consultarDemandantes();
		else
			this.vectorPersonas = persistencia.consultarDemandantes();

		if(this.vectorPersonas == null)
			this.personas = new Object[0];
		else
		{
			this.personas = new Object[vectorPersonas.size()];
			transformarListado();
		}
		this.screen = new ListadoPersonas(tipo, this.personas);
	}
	
	private void transformarListado()
	{
		int i = 0;
		
		Enumeration index = vectorPersonas.elements();
		
		while(index.hasMoreElements()) 
		{
			personas[i] = index.nextElement();			
			i++;
		}
	}
	
	public Persona getSelected()
	{
		NuevaPersonaController nuevaPersona = new NuevaPersonaController(this.tipo);
		if(String.class.isInstance(screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(nuevaPersona.getScreen());
			nuevaPersona.guardarPersona();
			screen.addPersona(nuevaPersona.getPersona());
			return nuevaPersona.getPersona();
		}
		else
			return (Persona)screen.getSelected();
	}
	
	public ListadoPersonas getScreen()
	{
		return screen;
	}
}
