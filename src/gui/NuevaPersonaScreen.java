package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;

public class NuevaPersonaScreen extends FondoNuevos {

	private int _tipo;
	private BasicEditField _txtNombre;
	private BasicEditField _txtCedula;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtCorreo;
	private BasicEditField _txtNotas;

	/**
	 * @param tipo Se crea una NuevaPersonaScreen con el tipo de Persona:
	 * 1 para demandante
	 * 2 para demandado
	 */
	public NuevaPersonaScreen(int tipo) {
		super();
		_tipo = tipo;
		if (tipo == 1)
			setTitle("Nuevo demandante");
		else
			setTitle("Nuevo demandado");

		// Se inicializan con el estilo
		_txtNombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");
		_txtCedula = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtCedula.setLabel("Id: ");
		_txtDireccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDireccion.setLabel("Dirección: ");
		_txtNotas = new BasicEditField();
		_txtNotas.setLabel("Notas: ");
		_txtTelefono = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtTelefono.setLabel("Teléfono: ");
		_txtCorreo = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtCorreo.setLabel("Correo: ");
		// Se agregan los elementos a la pantalla

		_vertical.add(_txtNombre);
		_vertical.add(_txtCedula);
		_vertical.add(_txtDireccion);
		_vertical.add(_txtTelefono);
		_vertical.add(_txtCorreo);
		_vertical.add(_txtNotas);
		add(_vertical);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	/**
	 * @return El tipo de la nueva Persona:
	 * 1 para demandante
	 * 2 para demandado
	 */
	public int getTipo() {
		return _tipo;
	}

	/**
	 * @return El nombre ingresado en la pantalla, en caso de no
	 * existir se retorna null
	 */
	public String getNombre() {
		return _txtNombre.getText();
	}

	/**
	 * @return La cedula ingresada en la pantalla, en caso de no
	 * existir se retorna null
	 */
	public String getCedula() {
		return _txtCedula.getText();
	}

	/**
	 * @return La direccion ingresada en la pantalla, en caso de no
	 * existir se retorna null
	 */
	public String getDireccion() {
		return _txtDireccion.getText();
	}

	/**
	 * @return El telefono ingresado en la pantalla, en caso de no
	 * existir se retorna null
	 */
	public String getTelefono() {
		return _txtTelefono.getText();
	}

	/**
	 * @return El correo ingresado en la pantalla, en caso de no
	 * existir se retorna null
	 */
	public String getCorreo() {
		return _txtCorreo.getText();
	}

	/**
	 * @return Las notas ingresadas en la pantalla, en caso de no
	 * existir se retorna null
	 */
	public String getNotas() {
		return _txtNotas.getText();
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}