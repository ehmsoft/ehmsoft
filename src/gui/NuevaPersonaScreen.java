package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;

public class NuevaPersonaScreen extends FondoNormal {

	private int _tipo;
	private BasicEditField _txtNombre;
	private BasicEditField _txtCedula;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtCorreo;
	private BasicEditField _txtNotas;
	private boolean _guardar;

	/**
	 * @param tipo
	 *            Se crea una NuevaPersonaScreen con el tipo de Persona: 1 para
	 *            demandante 2 para demandado
	 */
	public NuevaPersonaScreen(int tipo) {
		super();
		_guardar = false;
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
			if (_txtNombre.getTextLength() == 0
					|| _txtTelefono.getTextLength() == 0) {
				Object[] ask = { "Guardar", "Cancelar" };
				int sel = Dialog.ask(
						"Nombre y/o Teléfono se considera(n) importante(s)",
						ask, 1);
				if (sel == 0) {
					if (_txtNombre.getTextLength() == 0
							&& _txtCedula.getTextLength() == 0
							&& _txtDireccion.getTextLength() == 0
							&& _txtTelefono.getTextLength() == 0
							&& _txtCorreo.getTextLength() == 0
							&& _txtNotas.getTextLength() == 0) {
						Dialog.inform("Todos los campos están vacíos, no se guardará");
						UiApplication.getUiApplication().popScreen(getScreen());
					} else {
						_guardar = true;
						UiApplication.getUiApplication().popScreen(getScreen());
					}
				}
			} else {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};

	/**
	 * @return El tipo de la nueva Persona: 1 para demandante 2 para demandado
	 */
	public int getTipo() {
		return _tipo;
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
	public boolean isGuardado() {
		return _guardar;
	}

	public boolean onClose() {
		if (_txtNombre.getTextLength() == 0 && _txtCedula.getTextLength() == 0
				&& _txtDireccion.getTextLength() == 0
				&& _txtTelefono.getTextLength() == 0
				&& _txtCorreo.getTextLength() == 0
				&& _txtNotas.getTextLength() == 0) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 2);
			if (sel == 0) {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
			if (sel == 1) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
			if (sel == 2) {
				return false;
			} else
				return false;
		}
	}
}