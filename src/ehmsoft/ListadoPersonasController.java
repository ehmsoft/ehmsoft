package ehmsoft;

import net.rim.device.api.ui.UiApplication;
import core.Persona;
import gui.ListadoPersonas;
import persistence.Persistence;
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
		for(int i = 0; i < vectorPersonas.size(); i++)
			personas[i] = vectorPersonas.elementAt(i);
	}
	
	public Persona getSelected()
	{
		NuevaPersonaController nuevaPersona = new NuevaPersonaController(this.tipo);
		if(screen.getSelected() == "Nuevo demandante" || screen.getSelected() == "Nuevo demandado") {
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
