package ehmsoft;

import net.rim.device.api.ui.container.MainScreen;
import core.Persona;
import gui.NuevaPersona;

public class NuevaPersonaController extends MainScreen{
	
	public Main theApp;
	private short tipo;
	private Persona persona;
	private NuevaPersona screen;
	public NuevaPersonaController(Main theApp, short tipo)
	{
		this.theApp = theApp;
		this.tipo = tipo;
		this.screen = new NuevaPersona(this);
		this.theApp.pushScreen(this.screen);
	}
	
	public short getTipo()
	{
		return tipo;
	}
	
	public Persona getPersona()
	{
		return persona;
	}
	
	public void guardarPersona()
	{
		this.persona = this.persona.personaNueva(
				(short)1,screen.getId(), screen.getNombre(), screen.getTelefono(), 
				screen.getDireccion(), screen.getCorreo(), screen.getNotas());
		theApp.popScreen(screen);
	}

}
