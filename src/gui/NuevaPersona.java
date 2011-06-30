package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class NuevaPersona {
	private Persona _persona;
	private NuevaPersonaScreen _screen;

	/**
	 * @param tipo Se crea una NuevaPersona, con un tipo:
	 * 1 para demandante
	 * 2 para demandado
	 */
	public NuevaPersona(int tipo) {
		_screen = new NuevaPersonaScreen(tipo);
	}

	/**
	 * @return La pantalla asociada al objeto
	 */
	public NuevaPersonaScreen getScreen() {
		return _screen;
	}

	/**
	 * @return la nueva Persona creada, sí esta no ha sido guardada
	 * previamente con guardarPersona(); se invoca dicho metodo
	 */
	public Persona getPersona() {
		if(_persona == null) {
			guardarPersona();
		}
		return _persona;
	}

	/**
	 * Crea el nuevo objeto Persona, y lo guarda en la base de datos
	 * usando la informacion capturada desde la pantalla
	 */
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
			Dialog.alert("guardarPersona() -> " + e.toString());
		}
	}
}
