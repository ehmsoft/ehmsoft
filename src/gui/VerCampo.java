package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class VerCampo {
	private VerCampoScreen _screen;
	private CampoPersonalizado _campo;

	public VerCampo(CampoPersonalizado campo) {
		_screen = new VerCampoScreen(campo);
		_campo = campo;
	}

	public VerCampoScreen getScreen() {
		return _screen;
	}

	public void actualizarCampoPersonalizado() throws Exception {
		if (_screen.isGuardado()) {
			try {
				Persistence persistence = new Persistence();
				boolean cambio = false;
				CampoPersonalizado campo = _screen.getCampoPersonalizado();

				if (!campo.getNombre().equals(_screen.getNombre()))
					cambio = true;
				if (!campo.getValor().equals(_screen.getValor()))
					cambio = true;
				if (!campo.isObligatorio().equals(_screen.isObligatorio()))
					cambio = true;
				if (campo.getLongitudMax() != _screen.getLongitudMax())
					cambio = true;
				if (campo.getLongitudMin() != _screen.getLongitudMin())
					cambio = true;

				if (cambio)
					_campo = new CampoPersonalizado(_screen.getNombre(),
							_screen.getValor(), _screen.isObligatorio(),
							_screen.getLongitudMax(), _screen.getLongitudMin());
				persistence.actualizarCampoPersonalizado(_campo);
			} catch (Exception e) {
				Dialog.alert("actualizarCampoPersonalizado -> " + e.toString());
			}
		}
		if (_screen.isEliminado()) {
			Persistence p;
			try {
				p = new Persistence();
				p.borrarCampoPersonalizado(_campo);
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			throw new Exception("Se est� eliminando el campo");
		}
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}
}