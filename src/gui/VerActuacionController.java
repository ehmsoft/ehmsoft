package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;

public class VerActuacionController {
	private VerActuacion _screen;
	private Actuacion _actuacion;

	public VerActuacionController(Actuacion actuacion) {
		_screen = new VerActuacion(actuacion);
		_actuacion = actuacion;
	}

	public VerActuacion getScreen() {
		return _screen;
	}

	public void actualizarActuacion() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;
			Actuacion actuacion = _screen.getActuacion();

			if (!actuacion.getJuzgado().getId_juzgado().equals(_screen.getJuzgado().getId_juzgado()));
				cambio = true;
			if (!actuacion.getFecha().equals(_screen.getFecha()))
				cambio = true;
			if (!actuacion.getFechaProxima().equals(_screen.getFechaProxima()))
				cambio = true;
			if (!actuacion.getDescripcion().equals(_screen.getDescripcion()))
				cambio = true;

			if (cambio)
				_actuacion = new Actuacion(_screen.getJuzgado(), _screen.getFecha(), _screen.getFechaProxima(), _screen.getDescripcion(), _actuacion.getId_actuacion());
				persistence.actualizarActuacion(_actuacion);
		} catch (Exception e) {
			Dialog.alert("actualizarActuacion -> " + e.toString());
		}
	}
	
	public Actuacion getActuacion() {
		return _actuacion;
	}
}