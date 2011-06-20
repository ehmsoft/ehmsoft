package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class VerPersonaController {
	private VerPersona _screen;
	private Persona _persona;

	public VerPersonaController(Persona persona) {
		_screen = new VerPersona(persona);
		_persona = persona;
	}

	public VerPersona getScreen() {
		return _screen;
	}

	public void actualizarPersona() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;

			if (_persona.getNombre() != _screen.getNombre())
				cambio = true;
			if (_persona.getId() != _screen.getId())
				cambio = true;
			if (_persona.getTelefono() != _screen.getTelefono())
				cambio = true;
			if (_persona.getDireccion() != _screen.getDireccion())
				cambio = true;
			if (_persona.getCorreo() != _screen.getCorreo())
				cambio = true;
			if (_persona.getNotas() != _screen.getNotas())
				cambio = true;

			if (cambio) {
				_persona = new Persona(_persona.getTipo(), _screen.getId(), _screen.getNombre(), _screen.getTelefono(), _screen.getDireccion(), _screen.getCorreo(), _screen.getNotas(), _persona.getId_persona());
				persistence.actualizarPersona(_persona);
			}
		} catch (Exception e) {
			Dialog.alert("actualizarPersona -> " + e.toString());
		}
	}
	
	public Persona getPersona() {
		return _persona;
	}
}
