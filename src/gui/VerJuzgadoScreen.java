package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Juzgado;

public class VerJuzgadoScreen extends MainScreen {

	private EditableTextField _txtNombre;
	private EditableTextField _txtCiudad;
	private EditableTextField _txtDireccion;
	private EditableTextField _txtTelefono;
	private EditableTextField _txtTipo;

	private Juzgado _juzgado;

	private boolean _guardar = false;
	private boolean _eliminar = false;

	public VerJuzgadoScreen(Juzgado juzgado) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		setTitle("Ver juzgado");
		_juzgado = juzgado;

		_txtNombre = new EditableTextField("Nombre: ", _juzgado.getNombre());
		_txtCiudad = new EditableTextField("Ciudad: ", _juzgado.getCiudad());
		_txtDireccion = new EditableTextField("Direccion: ",
				_juzgado.getDireccion());
		_txtTelefono = new EditableTextField("Teléfono: ",
				_juzgado.getTelefono());
		_txtTipo = new EditableTextField("Tipo: ", _juzgado.getTipo());

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
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};
	
	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desea eliminar lel juzgado?", ask, 1);
			if (sel == 0) {
				_eliminar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
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

	public Juzgado getJuzgado() {
		return _juzgado;
	}

	public boolean isGuardado() {
		return _guardar;
	}
	
	public boolean isEliminado() {
		return _eliminar;
	}

	public boolean onClose() {
		boolean cambio = false;
		if (!_juzgado.getNombre().equals(this.getNombre()))
			cambio = true;
		if (!_juzgado.getCiudad().equals(this.getCiudad()))
			cambio = true;
		if (!_juzgado.getTelefono().equals(this.getTelefono()))
			cambio = true;
		if (!_juzgado.getDireccion().equals(this.getDireccion()))
			cambio = true;
		if (!_juzgado.getTipo().equals(this.getTipo()))
			cambio = true;
		if (!cambio) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 1);
			if (sel == 0) {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else {
				return false;
			}
		}
	}
}
