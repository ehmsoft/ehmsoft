package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;

public class VerActuacionController {
	private VerActuacion _screen;

	public VerActuacionController(Actuacion actuacion) {
		_screen = new VerActuacion(actuacion);
	}

	public VerActuacion getScreen() {
		return _screen;
	}

	public void actualizarActuacion() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;
			Actuacion actuacion = _screen.getActuacion();

			if (!actuacion.getJuzgado().equals(_screen.getJuzgado()))
				cambio = true;
			if (!actuacion.getFecha().equals(_screen.getFecha()))
				cambio = true;
			if (!actuacion.getFechaProxima().equals(_screen.getFechaProxima()))
				cambio = true;
			if (actuacion.getDescripcion() != _screen.getDescripcion())
				cambio = true;

			if (cambio)
				persistence.actualizarActuacion(actuacion);
		} catch (Exception e) {
			Dialog.alert("actualizarActuacion -> " + e.toString());
		}
	}
}