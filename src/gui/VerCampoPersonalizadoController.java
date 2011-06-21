package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class VerCampoPersonalizadoController {
	private VerCampoPersonalizado _screen;
	private CampoPersonalizado _campo;

	public VerCampoPersonalizadoController(CampoPersonalizado campo) {
		_screen = new VerCampoPersonalizado(campo);
		_campo = campo;
	}

	public VerCampoPersonalizado getScreen() {
		return _screen;
	}

	public void actualizarCampoPersonalizado() {
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

	public CampoPersonalizado getCampo() {
		return _campo;
	}
}
