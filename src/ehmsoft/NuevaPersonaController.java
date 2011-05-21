package ehmsoft;

import core.Persona;
import gui.NuevaPersona;
import persistence.Persistence;

public class NuevaPersonaController {
	private Persona persona;
	private NuevaPersona screen;

	public NuevaPersonaController(int tipo) {
		this.screen = new NuevaPersona(tipo);
	}

	public NuevaPersona getScreen() {
		return screen;
	}

	public Persona getPersona() {
		return persona;
	}

	public void guardarPersona() {
		Persistence guardado = new Persistence();
		persona = new Persona(1, screen.getCedula(), screen.getNombre(),
				screen.getTelefono(), screen.getDireccion(),
				screen.getCorreo(), screen.getNotas());
		guardado.guardarPersona(persona);		
	}
}
