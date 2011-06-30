package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;
import core.Proceso;

public class NuevaActuacion {
	private Actuacion _actuacion;
	private NuevaActuacionScreen _screen;
	private Proceso _proceso;

	public NuevaActuacion() {
		_screen = new NuevaActuacionScreen();
	}

	public NuevaActuacion(Proceso proceso) {
		_proceso = proceso;
		_screen = new NuevaActuacionScreen();
	}

	public void setProceso(Proceso proceso) {
		_proceso = proceso;
	}

	public Proceso getProceso() {
		return _proceso;
	}

	public NuevaActuacionScreen getScreen() {
		return _screen;
	}

	public Actuacion getActuacion() {
		if(_actuacion == null) {
			guardarActuacion();
		}
		return _actuacion;
	}

	public void guardarActuacion() {
		Persistence guardado = null;
		_actuacion = new Actuacion(_screen.getJuzgado(), _screen.getFecha(),
				_screen.getFechaProxima(), _screen.getDescripcion());
		try {
			guardado = new Persistence();
		}catch(Exception e) {
			Dialog.alert("New -> "+e.toString());
		}
		try {
			guardado.guardarActuacion(_actuacion, _proceso.getId_proceso());
		} catch (java.lang.NullPointerException e) {
			Dialog.alert("Error interno: No se asignó proceso");
		}
		catch (Exception e) {
			Dialog.alert("Guardar -> "+e.toString());
		}
	}
}