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
				
				if (!_campo.getNombre().equals(_screen.getNombre()))
					cambio = true;
				else if(_campo.getValor() != null) {
					if (!_campo.getValor().equals(_screen.getValor()))
						cambio = true;
				}
				else if (!_campo.isObligatorio().equals(_screen.isObligatorio()))
					cambio = true;
				else if (_campo.getLongitudMax() != _screen.getLongitudMax())
					cambio = true;
				else if (_campo.getLongitudMin() != _screen.getLongitudMin())
					cambio = true;

				if (cambio)
					_campo = new CampoPersonalizado(_campo.getId_campo(),_campo.getId_atributo(),_screen.getNombre(),
							_screen.getValor(), _screen.isObligatorio(),
							_screen.getLongitudMax(), _screen.getLongitudMin());
				persistence.actualizarAtributo(_campo);
				if(_campo.getValor() != null) {
					persistence.actualizarCampoPersonalizado(_campo);
				}
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
			throw new Exception("Se está eliminando el campo");
		}
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}
}
