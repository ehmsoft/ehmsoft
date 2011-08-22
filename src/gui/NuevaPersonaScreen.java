package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;

public class NuevaPersonaScreen extends FondoNormal {

	private BasicEditField _txtNombre;
	private BasicEditField _txtCedula;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtCorreo;
	private BasicEditField _txtNotas;
	
	public final int GUARDAR = 1;
	public final int CERRAR = 2;

	public NuevaPersonaScreen() {
		super();

		// Se inicializan con el estilo
		_txtNombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");
		_txtCedula = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtCedula.setLabel("Cédula: ");
		_txtDireccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDireccion.setLabel("Dirección: ");
		_txtNotas = new BasicEditField();
		_txtNotas.setLabel("Notas: ");
		_txtTelefono = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtTelefono.setLabel("Teléfono: ");
		_txtCorreo = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtCorreo.setLabel("Correo: ");
		// Se agregan los elementos a la pantalla

		add(_txtNombre);
		add(_txtCedula);
		add(_txtDireccion);
		add(_txtTelefono);
		add(_txtCorreo);
		add(_txtNotas);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(GUARDAR);
		}
	};

	public void showAlert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	/**
	 * @return El nombre ingresado en la pantalla, en caso de no existir se
	 *         retorna null
	 */
	public String getNombre() {
		return _txtNombre.getText();
	}

	/**
	 * @return La cedula ingresada en la pantalla, en caso de no existir se
	 *         retorna null
	 */
	public String getCedula() {
		return _txtCedula.getText();
	}

	/**
	 * @return La direccion ingresada en la pantalla, en caso de no existir se
	 *         retorna null
	 */
	public String getDireccion() {
		return _txtDireccion.getText();
	}

	/**
	 * @return El telefono ingresado en la pantalla, en caso de no existir se
	 *         retorna null
	 */
	public String getTelefono() {
		return _txtTelefono.getText();
	}

	/**
	 * @return El correo ingresado en la pantalla, en caso de no existir se
	 *         retorna null
	 */
	public String getCorreo() {
		return _txtCorreo.getText();
	}

	/**
	 * @return Las notas ingresadas en la pantalla, en caso de no existir se
	 *         retorna null
	 */
	public String getNotas() {
		return _txtNotas.getText();
	}

	/**
	 * @return Si el objeto sera guardado o no
	 */

	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}