package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.BasicEditField;
import persistence.Persistence;
import core.CampoPersonalizado;
import core.Proceso;

public class NuevoProcesoController {

	private Proceso _proceso;
	private NuevoProceso _screen;

	public NuevoProcesoController() {
		_screen = new NuevoProceso();
	}

	public Proceso getProceso() {
		return _proceso;
	}

	public NuevoProceso getScreen() {
		return _screen;
	}

	public void guardarProceso() {
		Persistence guardado = null;
		try {
			guardado = new Persistence();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		Vector campos = null;
		try {
			campos = asignarValores(_screen.getValores());
		} catch (Exception e) {
		} finally {
			_proceso = new Proceso(_screen.getDemandante(),
					_screen.getDemandado(), _screen.getFecha(),
					_screen.getJuzgado(), _screen.getRadicado(),
					_screen.getRadicadoUnico(), null, _screen.getEstado(),
					_screen.getCategoria(), null, _screen.getNotas(), campos,
					_screen.getPrioridad());
			try {
				guardado.guardarProceso(_proceso);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Vector asignarValores(Vector txtFields) {
		Enumeration fields = txtFields.elements();
		Vector campos = new Vector();

		while (fields.hasMoreElements()) {
			BasicEditField txtField;
			String valor;
			CampoPersonalizado campo;

			txtField = (BasicEditField) fields.nextElement();
			valor = txtField.getText();
			campo = (CampoPersonalizado) txtField.getCookie();

			campo.setValor(valor);
			campos.addElement(campo);
		}
		return campos;
	}
}
