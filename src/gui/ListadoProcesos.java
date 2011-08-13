package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Proceso;

public class ListadoProcesos {

	private Vector _vectorProcesos;
	private ListadoProcesosScreen _screen;

	public ListadoProcesos(long style) {
		try {
			_vectorProcesos = new Persistence().consultarProcesos();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}

		_screen = new ListadoProcesosScreen(style);
		addProcesos();
	}
	
	public ListadoProcesos() {
		this(0);
	}

	private void addProcesos() {
		Enumeration index;
		try {
			index = _vectorProcesos.elements();
			while (index.hasMoreElements())
				_screen.addProceso((Proceso)index.nextElement());
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
		return (Proceso) _screen.getSelected();
	}

	public ListadoProcesosScreen getScreen() {
		return _screen;
	}
}