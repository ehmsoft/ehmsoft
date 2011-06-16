package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;
import core.Proceso;

public class ListadoActuacionesController {

	private Persistence _persistencia;
	private Vector _vectorActuaciones;
	private ListadoActuaciones _screen;
	private Proceso _proceso;

	public ListadoActuacionesController(Proceso proceso, Vector fuentes) {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			_vectorActuaciones = _persistencia.consultarActuaciones(proceso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		_screen = new ListadoActuaciones(fuentes);
		addActuaciones();
	}

	public ListadoActuacionesController(Proceso proceso) {
		try {
			_persistencia = new Persistence();
			_vectorActuaciones = _persistencia.consultarActuaciones(proceso);
		} catch (Exception e) {
			e.printStackTrace();
		}

		_screen = new ListadoActuaciones();
		addActuaciones();
	}
	
	public ListadoActuacionesController(Vector fuentes) {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			e.printStackTrace();
			Dialog.alert(e.toString());
		}

		_screen = new ListadoActuaciones(fuentes);
		addActuaciones();
	}
	
	public ListadoActuacionesController() {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			e.printStackTrace();
			Dialog.alert(e.toString());
		}

		_screen = new ListadoActuaciones();
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
		NuevaActuacionController nuevaActuacion = new NuevaActuacionController(_proceso);
		if (String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(
					nuevaActuacion.getScreen());
			nuevaActuacion.guardarActuacion();
			_screen.addActuacion(nuevaActuacion.getActuacion());
			return nuevaActuacion.getActuacion();
		} else
			return (Actuacion) _screen.getSelected();
	}

	public ListadoActuaciones getScreen() {
		return _screen;
	}
}
