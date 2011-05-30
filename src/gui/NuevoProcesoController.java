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
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Vector campos = null;
		try {
			campos = asignarValores(_screen.getCampos(), _screen.getValores());
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private Vector asignarValores(Vector campos, Vector valores) {
		Enumeration iCampos = campos.elements();
		Enumeration iValores = valores.elements();

		while (iCampos.hasMoreElements())
			((CampoPersonalizado) iCampos.nextElement())
					.setValor(((BasicEditField) iValores.nextElement())
							.getText());
		return campos;
	}
}
