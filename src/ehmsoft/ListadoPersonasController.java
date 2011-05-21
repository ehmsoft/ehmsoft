package ehmsoft;

import net.rim.device.api.ui.container.MainScreen;
import core.Persona;
import gui.ListadoPersonas;
import persistence.Persistence;
import java.util.Vector;

public class ListadoPersonasController extends MainScreen {
	
	private Persona selected;
	private short tipo;
	public Main theApp;
	private Persistence persistencia;
	private Object[] personas;
	private Vector persistence;
	private ListadoPersonas screen;
	private NuevoProcesoController controller;
	public ListadoPersonasController(Main theApp, NuevoProcesoController controller,short tipo)
	{
		this.persistencia = new Persistence();
		this.theApp = theApp;
		this.tipo = tipo;
		this.persistence = persistencia.consultarPersonas();
		if(this.persistence == null)
		{
			this.personas = new Object[1];
			this.personas[0] = "Nuevo";
		}
		else
		{
			this.personas = new Object[persistence.size()+1];
			this.personas[0] = "Nuevo";
			transformarListado();
		}
		this.screen = new ListadoPersonas(this);
		this.theApp.pushScreen(this.screen);		
	}
	
	private void transformarListado()
	{
		for(int i = 1; i < persistence.size(); i++)
			personas[i] = persistence.elementAt(i-1);
	}
	
	public Object[] getPersonas()
	{
		return personas;
	}
	
	public Persona getSelected()
	{
		return selected;
	}
	
	public void setSelected(Persona persona)
	{
		selected = persona;
	}
	
	public void lanzarNuevaPersona(short tipo)
	{
		NuevaPersonaController nuevaPersona = new NuevaPersonaController(
				theApp, tipo);
		setSelected(nuevaPersona.getPersona());
		nuevaPersona = null;
		redibujar();
		synchronized (controller) {
			controller.notify();			
		}
	}

	public short getTipo() {
		return tipo;
	}
	
	private void redibujar()
	{
		Object[] nuevo = new Object[personas.length+1];
		for(int i = 0; i < personas.length; i++)
			nuevo[i] = personas[i];
		nuevo[personas.length] = selected;
		personas = nuevo;		
	}
}
