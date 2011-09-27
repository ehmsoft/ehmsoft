package gui.Ver;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Keypad;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.TextField;

public class VerPersonaScreen extends FondoNormal {

	private EditableTextField _txtNombre;
	private EditableTextField _txtId;
	private EditableTextField _txtTelefono;
	private EditableTextField _txtDireccion;
	private EditableTextField _txtCorreo;
	private EditableTextField _txtNotas;

	public VerPersonaScreen() {

		_txtNombre = new EditableTextField("Nombre: ", TextField.NO_NEWLINE);
		_txtId = new EditableTextField("Cédula: ", TextField.NO_NEWLINE);
		_txtTelefono = new EditableTextField("Teléfono: ", TextField.NO_NEWLINE);
		_txtDireccion = new EditableTextField("Dirección: ",
				TextField.NO_NEWLINE);
		_txtCorreo = new EditableTextField("Correo: ", TextField.NO_NEWLINE);
		_txtNotas = new EditableTextField("Notas: ", 0);

		add(_txtNombre);
		add(_txtId);
		add(_txtTelefono);
		add(_txtDireccion);
		add(_txtCorreo);
		add(_txtNotas);
		addKeyListener(listener);
	}

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuEditar);
		menu.add(menuEditarTodo);
		menu.add(menuEliminar);
		menu.add(menuGuardar);
		menu.add(menuCerrar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 262147, 3) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR);
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 131075, 1) {

		public void run() {
			EditableTextField f = (EditableTextField) getFieldWithFocus();
			if (!f.isEditable()) {
				f.setEditable();
				f.setFocus();
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 131075,
			2) {

		public void run() {
			_txtNombre.setEditable();
			_txtId.setEditable();
			_txtTelefono.setEditable();
			_txtDireccion.setEditable();
			_txtCorreo.setEditable();
			_txtNotas.setEditable();
		}
	};

	private MenuItem menuCerrar = new MenuItem("Salir de Aplicación",
			1000000000, 9) {

		public void run() {
			fieldChangeNotify(Util.CERRAR);
			if (!getScreen().isVisible()) {
				System.exit(0);
			}
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
		fieldChangeNotify(Util.CERRAR);
		return false;
	}

	private KeyListener listener = new KeyListener() {

		public boolean keyUp(int keycode, int time) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean keyRepeat(int keycode, int time) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean keyDown(int keycode, int time) {
			if (Keypad.key(keycode) == Keypad.KEY_SEND) {
				fieldChangeNotify(Util.LLAMAR);
				return true;
			} else {
				return false;
			}
		}

		public boolean keyChar(char key, int status, int time) {
			// TODO Auto-generated method stub
			return false;
		}
	};
}
