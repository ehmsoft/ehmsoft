package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Proceso;

public class ListadoProcesosController {

	private Persistence _persistencia;
	private Vector _vectorProcesos;
	private ListadoProcesos _screen;

	public ListadoProcesosController() {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}

		try {
			_vectorProcesos = _persistencia.consultarProcesos();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}

		_screen = new ListadoProcesos();
		addProcesos();
	}

	private void addProcesos() {
		Enumeration index;
		try {
			index = _vectorProcesos.elements();
			while (index.hasMoreElements())
				_screen.addProceso(index.nextElement());
		} catch (NullPointerException e) {
			Dialog.alert(e.toString());
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}
	
	public void setVectorProcesos(Vector procesos) {
		_vectorProcesos = procesos;
		addProcesos();
	}

	public Proceso getSelected() {
		NuevoProcesoController nuevoProceso = new NuevoProcesoController();
		if (String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(
					nuevoProceso.getScreen());
			nuevoProceso.guardarProceso();
			_screen.addProceso(nuevoProceso.getProceso());
			return nuevoProceso.getProceso();
		} else
			return (Proceso) _screen.getSelected();
	}

	public ListadoProcesos getScreen() {
		return _screen;
	}
}