package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;
import core.Persona;

public class VerPersonaScreen extends MainScreen {

	private EditableTextField _txtNombre;
	private EditableTextField _txtId;
	private EditableTextField _txtTelefono;
	private EditableTextField _txtDireccion;
	private EditableTextField _txtCorreo;
	private EditableTextField _txtNotas;

	private Persona _persona;
	
	private boolean _guardar;

	public VerPersonaScreen(Persona persona) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		_guardar = false;

		_persona = persona;

		if (_persona.getTipo() == 1)
			setTitle("Ver demandante");
		else if (_persona.getTipo() == 2)
			setTitle("Ver demandado");
		else
			setTitle("Ver persona");

		_txtNombre = new EditableTextField("Nombre: ", _persona.getNombre(),
				BasicEditField.NO_NEWLINE);

		_txtId = new EditableTextField("Cédula: ", _persona.getId(),
				BasicEditField.NO_NEWLINE);

		_txtTelefono = new EditableTextField("Teléfono: ",
				_persona.getTelefono(), BasicEditField.NO_NEWLINE);

		_txtDireccion = new EditableTextField("Dirección: ",
				_persona.getDireccion(), BasicEditField.NO_NEWLINE);

		_txtCorreo = new EditableTextField("Correo: ", _persona.getCorreo(),
				BasicEditField.NO_NEWLINE);

		_txtNotas = new EditableTextField("Notas: ", _persona.getNotas(),
				BasicEditField.NO_NEWLINE);

		add(_txtNombre);
		add(_txtId);
		add(_txtTelefono);
		add(_txtDireccion);
		add(_txtCorreo);
		add(_txtNotas);
		addMenuItem(menuGuardar);
		addMenuItem(menuEditar);
		addMenuItem(menuEditarTodo);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			EditableTextField f = (EditableTextField) getFieldWithFocus();
			if (f.isEditable())
				;
			else {
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

	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getId() {
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

	public Persona getPersona() {
		return _persona;
	}
	
	public boolean isGuardado() {
		return _guardar;
	}
	
	public boolean onClose() {
		boolean cambio = false;
		if (!_persona.getNombre().equals(this.getNombre()))
			cambio = true;
		if (!_persona.getId().equals(this.getId()))
			cambio = true;
		if (!_persona.getTelefono().equals(this.getTelefono()))
			cambio = true;
		if (!_persona.getDireccion().equals(this.getDireccion()))
			cambio = true;
		if (!_persona.getCorreo().equals(this.getCorreo()))
			cambio = true;
		if (!_persona.getNotas().equals(this.getNotas()))
			cambio = true;
		if(!cambio) {
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
