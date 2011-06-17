package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class VerPersonaController {
	private VerPersona _screen;

	public VerPersonaController(Persona persona) {
		_screen = new VerPersona(persona);
	}

	public VerPersona getScreen() {
		return _screen;
	}

	public void actualizarPersona() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;
			Persona persona = _screen.getPersona();

			if (persona.getNombre() != _screen.getNombre())
				cambio = true;
			if (persona.getId() != _screen.getId())
				cambio = true;
			if (persona.getTelefono() != _screen.getTelefono())
				cambio = true;
			if (persona.getDireccion() != _screen.getDireccion())
				cambio = true;
			if (persona.getCorreo() != _screen.getCorreo())
				cambio = true;
			if (persona.getNotas() != _screen.getNotas())
				cambio = true;

			if (cambio)
				persistence.actualizarPersona(persona);
		} catch (Exception e) {
			Dialog.alert("actualizarPersona -> " + e.toString());
		}
	}
}
