package gui.Nuevos;

import gui.FondoNormal;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.TextField;

public class NuevoJuzgadoScreen extends FondoNormal {

	private BasicEditField _txtNombre;
	private BasicEditField _txtCiudad;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtTipo;

	public final int GUARDAR = 1;
	public final int CERRAR = 2;

	/**
	 * Crea un NuevoJuzgadoScreen que es la pantalla para capturar los datos
	 * para crear un nuevo Juzgado
	 */
	public NuevoJuzgadoScreen() {
		setTitle("Nuevo juzgado");

		_txtNombre = new BasicEditField(TextField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");

		_txtCiudad = new BasicEditField(TextField.NO_NEWLINE);
		_txtCiudad.setLabel("Ciudad: ");

		_txtDireccion = new BasicEditField(TextField.NO_NEWLINE);
		_txtDireccion.setLabel("Dirección: ");

		_txtTelefono = new BasicEditField(TextField.NO_NEWLINE);
		_txtTelefono.setLabel("Teléfono: ");

		_txtTipo = new BasicEditField(TextField.NO_NEWLINE);
		_txtTipo.setLabel("Tipo: ");

		add(_txtNombre);
		add(_txtCiudad);
		add(_txtDireccion);
		add(_txtTelefono);
		add(_txtTipo);
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
	 * @return El nombre ingresado en la pantalla
	 */
	public String getNombre() {
		return _txtNombre.getText();
	}

	/**
	 * @return La ciudad ingresada en la pantalla
	 */
	public String getCiudad() {
		return _txtCiudad.getText();
	}

	/**
	 * @return La direccion ingresada en la pantalla
	 */
	public String getDireccion() {
		return _txtDireccion.getText();
	}

	/**
	 * @return El telefono ingresado en la pantalla
	 */
	public String getTelefono() {
		return _txtTelefono.getText();
	}

	/**
	 * @return El tipo ingresado en la pantalla
	 */
	public String getTipo() {
		return _txtTipo.getText();
	}

	/**
	 * @return Si el objeto sera guardado o no
	 */

	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}
