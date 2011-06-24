package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;
import core.Proceso;

public class ListadoActuaciones {

	private Persistence _persistencia;
	private Vector _vectorActuaciones;
	private ListadoActuacionesScreen _screen;
	private Proceso _proceso;

	public ListadoActuaciones(Proceso proceso) {
		_proceso = proceso;
		try {
			_persistencia = new Persistence();
			_vectorActuaciones = _persistencia.consultarActuaciones(proceso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		_screen = new ListadoActuacionesScreen();
		addActuaciones();
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
		NuevaActuacion nuevaActuacion = new NuevaActuacion(
				_proceso);
		if (String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(
					nuevaActuacion.getScreen());
			nuevaActuacion.guardarActuacion();
			_screen.addActuacion(nuevaActuacion.getActuacion());
			return nuevaActuacion.getActuacion();
		} else
			return (Actuacion) _screen.getSelected();
	}

	public ListadoActuacionesScreen getScreen() {
		return _screen;
	}
}
