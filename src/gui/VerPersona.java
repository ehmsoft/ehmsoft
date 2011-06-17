package gui;

import core.Persona;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.container.MainScreen;

public class VerPersona extends MainScreen {

	/**
	 * 
	 */
	private EditableTextField _txtNombre;
	private BasicEditField _txtId;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtCorreo;
	private BasicEditField _txtNotas;

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

		_txtId = new BasicEditField("Cédula: ", _persona.getId());
		_txtId.setEditable(false);

		_txtTelefono = new BasicEditField("Teléfono: ", _persona.getTelefono());
		_txtTelefono.setEditable(false);

		_txtDireccion = new BasicEditField("Dirección: ",
				_persona.getDireccion());
		_txtDireccion.setEditable(false);

		_txtCorreo = new BasicEditField("Correo: ", _persona.getCorreo());
		_txtCorreo.setEditable(false);

		_txtNotas = new BasicEditField("Notas: ", _persona.getNotas());
		_txtNotas.setEditable(false);

		add(_txtNombre);
		add(_txtId);
		add(_txtTelefono);
		add(_txtDireccion);
		add(_txtCorreo);
		add(_txtNotas);
		addMenuItem(menuGuardar);
	}

	protected boolean navigationClick(int status, int time) {
		if (((EditableTextField) getFieldWithFocus()).isEditable());
		else
			((EditableTextField) getFieldWithFocus()).setEditable();
		return true;
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
