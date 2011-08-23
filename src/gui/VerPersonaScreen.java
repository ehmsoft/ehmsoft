package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;

public class VerPersonaScreen extends FondoNormal {

	private EditableTextField _txtNombre;
	private EditableTextField _txtId;
	private EditableTextField _txtTelefono;
	private EditableTextField _txtDireccion;
	private EditableTextField _txtCorreo;
	private EditableTextField _txtNotas;
	
	public final int GUARDAR = 1;
	public final int ELIMINAR = 2;
	public final int CERRAR = 3;

	public VerPersonaScreen() {

		_txtNombre = new EditableTextField("Nombre: ", "", BasicEditField.NO_NEWLINE);
		_txtId = new EditableTextField("Cédula: ", "", BasicEditField.NO_NEWLINE);
		_txtTelefono = new EditableTextField("Teléfono: ", "", BasicEditField.NO_NEWLINE);
		_txtDireccion = new EditableTextField("Dirección: ", "", BasicEditField.NO_NEWLINE);
		_txtCorreo = new EditableTextField("Correo: ", "", BasicEditField.NO_NEWLINE);
		_txtNotas = new EditableTextField("Notas: ", "", 0);

		add(_txtNombre);
		add(_txtId);
		add(_txtTelefono);
		add(_txtDireccion);
		add(_txtCorreo);
		add(_txtNotas);
	}
	
	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuEditar);
		menu.add(menuEditarTodo);
		menu.addSeparator();
		menu.add(menuEliminar);
		menu.add(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(GUARDAR);
		}
	};
	
	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			fieldChangeNotify(ELIMINAR);
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			EditableTextField f = (EditableTextField) getFieldWithFocus();
			if (!f.isEditable()) {
				f.setEditable();
				f.setFocus();
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 0, 0) {

		public void run() {
			_txtNombre.setEditable();
			_txtId.setEditable();
			_txtTelefono.setEditable();
			_txtDireccion.setEditable();
			_txtCorreo.setEditable();
			_txtNotas.setEditable();
		}
	};
	
	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public void setNombre(String text) {
		_txtNombre.setText(text);
	}

	public void setCedula(String text) {
		_txtId.setText(text);
	}

	public void setTelefono(String text) {
		_txtTelefono.setText(text);
	}

	public void setDireccion(String text) {
		_txtDireccion.setText(text);
	}

	public void setCorreo(String text) {
		_txtCorreo.setText(text);
	}

	public void setNotas(String text) {
		_txtNotas.setText(text);
	}
	
	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getCedula() {
		return _txtId.getText();
	}

	public String getTelefono() {
		return _txtTelefono.getText();
	}

	public String getDireccion() {
		return _txtDireccion.getText();
	}

	public String getCorreo() {
		return _txtCorreo.getText();
	}

	public String getNotas() {
		return _txtNotas.getText();
	}

	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}
