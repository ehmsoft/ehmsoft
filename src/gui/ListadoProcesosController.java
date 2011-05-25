package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Proceso;

public class ListadoProcesosController {

	private Persistence _persistencia;
	private Object[] _procesos;
	private Vector _vectorProcesos;
	private ListadoProcesos _screen;

	public ListadoProcesosController() {
		_persistencia = new Persistence();

		_vectorProcesos = _persistencia.consultarProcesos();

		if (_vectorProcesos == null)
			_procesos = new Object[0];
		else {
			_procesos = new Object[_vectorProcesos.size()];
			transformarListado();
		}
		_screen = new ListadoProcesos(_procesos);
	}

	private void transformarListado() {
		int i = 0;

		Enumeration index = _vectorProcesos.elements();

		while (index.hasMoreElements()) {
			_procesos[i] = index.nextElement();
			i++;
		}
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