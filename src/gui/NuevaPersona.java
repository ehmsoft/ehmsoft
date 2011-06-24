package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class NuevaPersona {
	private Persona _persona;
	private NuevaPersonaScreen _screen;

	public NuevaPersona(int tipo) {
		this._screen = new NuevaPersonaScreen(tipo);
	}

	public NuevaPersonaScreen getScreen() {
		return _screen;
	}

	public Persona getPersona() {
		return _persona;
	}

	public void guardarPersona() {
		Persistence guardado = null;
		try {
			guardado = new Persistence();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		_persona = new Persona(_screen.getTipo(), _screen.getCedula(),
				_screen.getNombre(), _screen.getTelefono(),
				_screen.getDireccion(), _screen.getCorreo(), _screen.getNotas());
		try {
			guardado.guardarPersona(_persona);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Dialog.alert(e.toString());
		}
	}
}
