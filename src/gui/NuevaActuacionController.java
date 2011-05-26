package gui;

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
		Persistence guardado = new Persistence();
		_actuacion = new Actuacion(_screen.getJuzgado(), _screen.getFecha(), _screen.getFechaProxima(), _screen.getDescripcion());
		try {
			guardado.guardarActuacion(_actuacion, _idProceso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}