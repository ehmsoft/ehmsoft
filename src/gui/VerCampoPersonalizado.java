package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class VerCampoPersonalizado {
	private VerCampoPersonalizadoScreen _screen;
	private CampoPersonalizado _campo;

	public VerCampoPersonalizado(CampoPersonalizado campo) {
		_screen = new VerCampoPersonalizadoScreen(campo);
		_campo = campo;
	}

	public VerCampoPersonalizadoScreen getScreen() {
		return _screen;
	}

	public void actualizarCampoPersonalizado() {
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
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}
}
