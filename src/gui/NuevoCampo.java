package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class NuevoCampo {
	private CampoPersonalizado _campo;
	private NuevoCampoScreen _screen;

	public NuevoCampo() {
		_screen = new NuevoCampoScreen();
	}

	public NuevoCampoScreen getScreen() {
		return _screen;
	}

	public CampoPersonalizado getCampo() throws Exception {
		if (_campo == null) {
			guardarCampo();
		}
		return _campo;
	}

	public CampoPersonalizado getCampo(boolean guardado) throws Exception {
		if (!guardado) {
			_campo = new CampoPersonalizado(_screen.getNombre(), null,
					_screen.isObligatorio(), _screen.getLongMax(),
					_screen.getLongMin());
			return _campo;
		} else {
			return getCampo();
		}
	}

	public void guardarCampo() throws Exception {
		if (_screen.isGuardado()) {
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
				guardado.guardarAtributo(_campo);
			} catch (Exception e) {
				Dialog.alert(e.toString());
				e.printStackTrace();
			}
		} else {
			throw new Exception("No se esta guardando el elemento");
		}
	}
}
