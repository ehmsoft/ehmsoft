package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class NuevoCampoPersonalizado {
	private CampoPersonalizado _campo;
	private NuevoCampoPersonalizadoScreen _screen;

	public NuevoCampoPersonalizado() {
		_screen = new NuevoCampoPersonalizadoScreen();
	}

	public NuevoCampoPersonalizadoScreen getScreen() {
		return _screen;
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}

	public void guardarCampo(String idProceso) {
		Persistence guardado = null;
		try {
			guardado = new Persistence();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		_campo = new CampoPersonalizado(_screen.getNombre(), null,
				_screen.isObligatorio(), _screen.getLongMax(),
				_screen.getLongMin());
		try {
			guardado.guardarCampoPersonalizado(_campo, idProceso);
		} catch (Exception e) {
			Dialog.alert(e.toString());
			e.printStackTrace();
		}
	}

	public void guardarCampo() {
		_campo = new CampoPersonalizado(_screen.getNombre(), null,
				_screen.isObligatorio(), _screen.getLongMax(),
				_screen.getLongMin());
	}
}
