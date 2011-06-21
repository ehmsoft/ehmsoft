package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;
import core.Proceso;

public class NuevaActuacionController {
	private Actuacion _actuacion;
	private NuevaActuacion _screen;
	private Proceso _proceso;

	public NuevaActuacionController() {
		_screen = new NuevaActuacion();
	}

	public NuevaActuacionController(Proceso proceso) {
		_proceso = proceso;
		_screen = new NuevaActuacion();
	}

	public void setProceso(Proceso proceso) {
		_proceso = proceso;
	}

	public Proceso getProceso() {
		return _proceso;
	}

	public NuevaActuacion getScreen() {
		return _screen;
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}

	public void guardarActuacion() {
		Persistence guardado = null;
		_actuacion = new Actuacion(_screen.getJuzgado(), _screen.getFecha(),
				_screen.getFechaProxima(), _screen.getDescripcion());
		try {
			guardado = new Persistence();
			guardado.guardarActuacion(_actuacion, _proceso.getId_proceso());
		} catch (Exception e) {
			Dialog.alert(e.toString());
			e.printStackTrace();
		}
	}
}