package ehmsoft;

import core.Persona;
import gui.NuevaPersona;
import persistence.Persistence;

public class NuevaPersonaController {
	private Persona _persona;
	private NuevaPersona _screen;

	public NuevaPersonaController(int tipo) {
		this._screen = new NuevaPersona(tipo);
	}

	public NuevaPersona getScreen() {
		return _screen;
	}

	public Persona getPersona() {
		return _persona;
	}

	public void guardarPersona() {
		Persistence guardado = new Persistence();
		_persona = new Persona(1, _screen.getCedula(), _screen.getNombre(),
				_screen.getTelefono(), _screen.getDireccion(),
				_screen.getCorreo(), _screen.getNotas());
		guardado.guardarPersona(_persona);		
	}
}
