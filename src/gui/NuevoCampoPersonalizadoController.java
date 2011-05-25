package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class NuevoCampoPersonalizadoController {
	private CampoPersonalizado _campo;
	private NuevoCampoPersonalizado _screen;

	public NuevoCampoPersonalizadoController() {
		_screen = new NuevoCampoPersonalizado();
	}

	public NuevoCampoPersonalizado getScreen() {
		return _screen;
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}

	public void guardarCampo() {
		Persistence guardado = new Persistence();
		_campo = new CampoPersonalizado(_screen.getNombre(), null,
				_screen.isObligatorio(), _screen.getLongMax(),
				_screen.getLongMin());
		try {
			guardado.guardarCampoPersonalizado(_campo, null);
		} catch (Exception e) {
			Dialog.alert(e.toString());
			e.printStackTrace();
		}
	}
}
