package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;
import core.Proceso;

public class VerProceso {
	private VerProcesoScreen _screen;
	private Proceso _proceso;

	public VerProceso(Proceso proceso) {
		_screen = new VerProcesoScreen(proceso);
		_proceso = proceso;
	}

	public VerProcesoScreen getScreen() {
		return _screen;
	}

	public void actualizarProceso() {
		if (_screen.isGuardado()) {
			try {
				Vector campos = null;
				campos = asignarValores(_screen.getValoresViejos());
				Persistence persistence = new Persistence();
				_proceso = new Proceso(_proceso.getId_proceso(),
						_screen.getDemandante(), _screen.getDemandado(),
						_screen.getFecha(), _screen.getJuzgado(),
						_screen.getRadicado(), _screen.getRadicadoUnico(),
						_screen.getActuaciones(), _screen.getEstado(),
						_screen.getCategoria(), _screen.getTipo(),
						_screen.getNotas(), campos,
						_screen.getPrioridad());
				persistence.actualizarProceso(_proceso);
				campos = asignarValores(_screen.getValoresNuevos());
				Enumeration e = campos.elements();
				while(e.hasMoreElements()) {
					persistence.guardarCampoPersonalizado((CampoPersonalizado)e.nextElement(), _proceso.getId_proceso());
				}
			} catch (Exception e) {
				Dialog.alert("actualizarProceso -> " + e.toString());
			}
		}
	}

	public Proceso getProceso() {
		return _proceso;
	}
	
	private Vector asignarValores(Vector txtFields) {
		Enumeration fields = txtFields.elements();
		Vector campos = new Vector();

		while (fields.hasMoreElements()) {
			EditableTextField txtField;
			String valor;
			CampoPersonalizado campo;

			txtField = (EditableTextField) fields.nextElement();
			valor = txtField.getText();
			campo = (CampoPersonalizado) txtField.getCookie();

			campo.setValor(valor);
			campos.addElement(campo);
		}
		return campos;
	}
}