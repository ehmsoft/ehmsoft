package ehmsoft;

import net.rim.device.api.ui.UiApplication;
import core.Persona;
import gui.ListadoPersonas;
import persistence.Persistence;

import java.util.Enumeration;
import java.util.Vector;

public class ListadoJuzgadosController {
	
	private int tipo;
	private Persistence persistencia;
	private Object[] juzgados;
	private Vector vectorJuzgados;
	private ListadoPersonas screen;
	
	public ListadoJuzgadosController(int tipo)
	{
		this.tipo = tipo;
		this.persistencia = new Persistence();
		
		this.vectorJuzgados = persistencia.consultarJuzgados();

		if(this.vectorJuzgados == null)
			this.juzgados = new Object[0];
		else
		{
			this.juzgados = new Object[vectorJuzgados.size()];
			transformarListado();
		}
		this.screen = new ListadoPersonas(tipo, this.juzgados);
	}
	
	private void transformarListado()
	{
		int i = 0;
		
		Enumeration index = vectorJuzgados.elements();
		
		while(index.hasMoreElements()) 
		{
			juzgados[i] = index.nextElement();			
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
