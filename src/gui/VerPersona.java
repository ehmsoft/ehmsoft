package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class VerPersona {
	private VerPersonaScreen _screen;
	private Persona _persona;

	public VerPersona(Persona persona) {
		_screen = new VerPersonaScreen(persona);
		_persona = persona;
	}

	public VerPersonaScreen getScreen() {
		return _screen;
	}

	public void actualizarPersona() {
		if (_screen.isGuardado()) {
			try {
				Persistence persistence = new Persistence();
				boolean cambio = false;

				if (!_persona.getNombre().equals(_screen.getNombre()))
					cambio = true;
				if (!_persona.getId().equals(_screen.getId()))
					cambio = true;
				if (!_persona.getTelefono().equals(_screen.getTelefono()))
					cambio = true;
				if (!_persona.getDireccion().equals(_screen.getDireccion()))
					cambio = true;
				if (!_persona.getCorreo().equals(_screen.getCorreo()))
					cambio = true;
				if (!_persona.getNotas().equals(_screen.getNotas()))
					cambio = true;

				if (cambio) {
					_persona = new Persona(_persona.getTipo(), _screen.getId(),
							_screen.getNombre(), _screen.getTelefono(),
							_screen.getDireccion(), _screen.getCorreo(),
							_screen.getNotas(), _persona.getId_persona());
					persistence.actualizarPersona(_persona);
				}
			} catch (Exception e) {
				Dialog.alert("actualizarPersona -> " + e.toString());
			}
		}
	}

	public Persona getPersona() {
		return _persona;
	}
}
