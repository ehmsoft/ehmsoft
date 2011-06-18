package gui;

import core.Persona;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class VerPersona extends MainScreen {

	private EditableTextField _txtNombre;
	private EditableTextField _txtId;
	private EditableTextField _txtTelefono;
	private EditableTextField _txtDireccion;
	private EditableTextField _txtCorreo;
	private EditableTextField _txtNotas;

	private Persona _persona;

	public VerPersona(Persona persona) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_persona = persona;

		if (_persona.getTipo() == 1)
			setTitle("Ver demandante");
		else if (_persona.getTipo() == 2)
			setTitle("Ver demandado");
		else
			setTitle("Ver persona");

		_txtNombre = new EditableTextField("Nombre: ", _persona.getNombre());

		_txtId = new EditableTextField("Cédula: ", _persona.getId());

		_txtTelefono = new EditableTextField("Teléfono: ", _persona.getTelefono());

		_txtDireccion = new EditableTextField("Dirección: ",
				_persona.getDireccion());

		_txtCorreo = new EditableTextField("Correo: ", _persona.getCorreo());

		_txtNotas = new EditableTextField("Notas: ", _persona.getNotas());

		add(_txtNombre);
		add(_txtId);
		add(_txtTelefono);
		add(_txtDireccion);
		add(_txtCorreo);
		add(_txtNotas);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			UiApplication.getUiApplication().popScreen(getScreen());
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
}
