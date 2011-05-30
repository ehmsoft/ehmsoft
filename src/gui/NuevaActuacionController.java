package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;

public class NuevaActuacionController {
	private Actuacion _actuacion;
	private NuevaActuacion _screen;
	private String _idProceso;

	public NuevaActuacionController(String idProceso) {
		_idProceso = idProceso;
		this._screen = new NuevaActuacion();
	}

	public NuevaActuacion getScreen() {
		return _screen;
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}

	public void guardarActuacion() {
		Persistence guardado = null;
		try {
			guardado = new Persistence();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		_actuacion = new Actuacion(_screen.getJuzgado(), _screen.getFecha(), _screen.getFechaProxima(), _screen.getDescripcion());
		try {
			guardado.guardarActuacion(_actuacion, _idProceso);
		} catch (Exception e) {
			Dialog.alert(e.toString());
			e.printStackTrace();
		}
	}
}