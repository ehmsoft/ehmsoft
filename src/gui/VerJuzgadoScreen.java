package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;

public class VerJuzgadoScreen extends FondoNormal {

	private EditableTextField _txtNombre;
	private EditableTextField _txtCiudad;
	private EditableTextField _txtDireccion;
	private EditableTextField _txtTelefono;
	private EditableTextField _txtTipo;
	
	public final int GUARDAR = 1;
	public final int ELIMINAR = 2;
	public final int CERRAR = 3;

	public VerJuzgadoScreen() {

		setTitle("Ver juzgado");

		_txtNombre = new EditableTextField("Nombre: ", BasicEditField.NO_NEWLINE);
		_txtCiudad = new EditableTextField("Ciudad: ", BasicEditField.NO_NEWLINE);
		_txtDireccion = new EditableTextField("Direccion: ", BasicEditField.NO_NEWLINE);
		_txtTelefono = new EditableTextField("Teléfono: ", BasicEditField.NO_NEWLINE);
		_txtTipo = new EditableTextField("Tipo: ", BasicEditField.NO_NEWLINE);

		add(_txtNombre);
		add(_txtCiudad);
		add(_txtDireccion);
		add(_txtTelefono);
		add(_txtTipo);
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
			_txtCiudad.setEditable();
			_txtDireccion.setEditable();
			_txtTelefono.setEditable();
			_txtTipo.setEditable();
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

	public void setCiudad(String text) {
		_txtCiudad.setText(text);
	}

	public void setTelefono(String text) {
		_txtTelefono.setText(text);
	}

	public void setDireccion(String text) {
		_txtDireccion.setText(text);
	}

	public void setTipo(String text) {
		_txtTipo.setText(text);
	}
	
	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getCiudad() {
		return _txtCiudad.getText();
	}

	public String getTelefono() {
		return _txtTelefono.getText();
	}

	public String getDireccion() {
		return _txtDireccion.getText();
	}

	public String getTipo() {
		return _txtTipo.getText();
	}

	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}
