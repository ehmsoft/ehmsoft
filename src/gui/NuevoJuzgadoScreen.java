package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;

public class NuevoJuzgadoScreen extends FondoNuevos {

	private BasicEditField _txtNombre;
	private BasicEditField _txtCiudad;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtTipo;
	private boolean _guardar;

	/**
	 * Crea un NuevoJuzgadoScreen que es la pantalla para capturar los datos
	 * para crear un nuevo Juzgado
	 */
	public NuevoJuzgadoScreen() {
		setTitle("Nuevo juzgado");

		_txtNombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");

		_txtCiudad = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtCiudad.setLabel("Ciudad: ");

		_txtDireccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDireccion.setLabel("Dirección: ");

		_txtTelefono = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtTelefono.setLabel("Teléfono: ");

		_txtTipo = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtTipo.setLabel("Tipo: ");

		_vertical.add(_txtNombre);
		_vertical.add(_txtCiudad);
		_vertical.add(_txtDireccion);
		_vertical.add(_txtTelefono);
		_vertical.add(_txtTipo);
		add(_vertical);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

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
	public boolean isGuardado() {
		return _guardar;
	}

	public boolean onClose() {
		if (_txtNombre.getTextLength() == 0 && _txtCiudad.getTextLength() == 0
				&& _txtDireccion.getTextLength() == 0
				&& _txtTelefono.getTextLength() == 0
				&& _txtTipo.getTextLength() == 0) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desea descartar los cambios realizados?",
					ask, 1);
			if (sel == 0) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else {
				return false;
			}
		}
	}
}
