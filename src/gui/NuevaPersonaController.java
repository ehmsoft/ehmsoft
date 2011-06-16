package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

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
		Persistence guardado = null;
		try {
			guardado = new Persistence();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			Dialog.alert(e1.toString());
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
