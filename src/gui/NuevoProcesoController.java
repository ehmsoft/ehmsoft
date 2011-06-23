package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;
import core.Juzgado;
import core.Persona;
import core.Proceso;

public class NuevoProcesoController {

	private Proceso _proceso;
	private NuevoProceso _screen;

	public NuevoProcesoController() {
		_screen = new NuevoProceso();
	}

	public void setDemandante(Persona demandante) {
		_screen.setDemandante(demandante);
	}

	public void setDemandado(Persona demandado) {
		_screen.setDemandado(demandado);
	}

	public void setJuzgado(Juzgado juzgado) {
		_screen.setJuzgado(juzgado);
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
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		Vector campos = null;
		try {
			campos = asignarValores(_screen.getValores());
		} catch (Exception e) {
			Dialog.alert(e.toString());
		} finally {
			_proceso = new Proceso(_screen.getDemandante(),
					_screen.getDemandado(), _screen.getFecha(),
					_screen.getJuzgado(), _screen.getRadicado(),
					_screen.getRadicadoUnico(), null, _screen.getEstado(),
					_screen.getCategoria(), _screen.getTipo(), _screen.getNotas(), campos,
					_screen.getPrioridad());
			try {
				guardado.guardarProceso(_proceso);
			} catch (Exception e) {
				Dialog.alert(e.toString());
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
