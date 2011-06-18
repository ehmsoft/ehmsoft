package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class VerCampoPersonalizadoController {
	private VerCampoPersonalizado _screen;

	public VerCampoPersonalizadoController(CampoPersonalizado campo) {
		_screen = new VerCampoPersonalizado(campo);
	}

	public VerCampoPersonalizado getScreen() {
		return _screen;
	}

	public void actualizarCampoPersonalizado() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;
			CampoPersonalizado campo = _screen.getCampoPersonalizado();

			if (campo.getNombre() != _screen.getNombre())
				cambio = true;
			if (campo.getValor() != _screen.getValor())
				cambio = true;
			if (campo.isObligatorio() != _screen.isObligatorio())
				cambio = true;
			if (campo.getLongitudMax() != _screen.getLongitudMax())
				cambio = true;
			if (campo.getLongitudMin() != _screen.getLongitudMin())
				cambio = true;

			if (cambio)
				persistence.actualizarCampoPersonalizado(campo);
		} catch (Exception e) {
			Dialog.alert("actualizarCampoPersonalizado -> " + e.toString());
		}
	}
}
