package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;
import core.Proceso;

public class ListadoActuaciones {

	private Persistence _persistencia;
	private Vector _vectorActuaciones;
	private ListadoActuacionesScreen _screen;
	
	public ListadoActuaciones(Proceso proceso, long style, Vector actuaciones) {
		if (proceso != null) {
			try {
				_persistencia = new Persistence();
				_vectorActuaciones = _persistencia
						.consultarActuaciones(proceso);
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		} else {
			_vectorActuaciones = actuaciones;
		}
		_screen = new ListadoActuacionesScreen(proceso, style);
		addActuaciones();
	}

	public ListadoActuaciones(Proceso proceso) {
		this(proceso, 0, null);
	}
	
	public ListadoActuaciones(Vector vector, long style) {
		this(null, style, vector);
	}
	
	public ListadoActuaciones(Vector vector) {
		this(null, 0, vector);
	}

	public void setVectorActuaciones(Vector actuaciones) {
		_vectorActuaciones = actuaciones;
		addActuaciones();
	}

	private void addActuaciones() {
		Enumeration index;
		try {
			index = _vectorActuaciones.elements();
			while (index.hasMoreElements())
				_screen.addActuacion(index.nextElement());
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public Actuacion getSelected() {
		return (Actuacion) _screen.getSelected();
	}

	public ListadoActuacionesScreen getScreen() {
		return _screen;
	}
}
