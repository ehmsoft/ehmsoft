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

public class NuevoProceso {

	private Proceso _proceso;
	private NuevoProcesoScreen _screen;

	/**
	 * Se crea un NuevoProceso y le asocia una pantalla, con este objeto se
	 * crearan nuevos Procesos
	 */
	public NuevoProceso() {
		_screen = new NuevoProcesoScreen();
	}

	/**
	 * @param demandante
	 *            Se asigna una Persona demandante a la creacion del Proceso
	 */
	public void setDemandante(Persona demandante) {
		_screen.setDemandante(demandante);
	}

	/**
	 * @param demandado
	 *            Se asigna una Persona demandado a la creacion del Proceso
	 */
	public void setDemandado(Persona demandado) {
		_screen.setDemandado(demandado);
	}

	/**
	 * @param juzgado
	 *            Se asigna un Juzgado a la creacion del Proceso
	 */
	public void setJuzgado(Juzgado juzgado) {
		_screen.setJuzgado(juzgado);
	}

	/**
	 * @return El nuevo Proceso, sí este no ha sido guardado con el metodo
	 *         guardarProceso(); se realiza el llamado a este metodo
	 */
	public Proceso getProceso() throws Exception {
		if (_proceso == null) {
			guardarProceso();
		}
		return _proceso;
	}

	/**
	 * @return La pantalla asociada al objeto
	 */
	public NuevoProcesoScreen getScreen() {
		return _screen;
	}

	/**
	 * Se crea el nuevo Proceso, basado en los datos capturados por la pantalla
	 * y se guarda en la base de datos
	 */
	public void guardarProceso() throws Exception {
		if (_screen.isGuardar()) {
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
						_screen.getCategoria(), _screen.getTipo(),
						_screen.getNotas(), campos, _screen.getPrioridad());
				try {
					guardado.guardarProceso(_proceso);
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		} else {
			throw new Exception("No se guardará el proceso");
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
