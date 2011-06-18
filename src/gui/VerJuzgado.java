package gui;

import core.Juzgado;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class VerJuzgado extends MainScreen {
	
	EditableTextField _txtNombre;
	EditableTextField _txtCiudad;
	EditableTextField _txtDireccion;
	EditableTextField _txtTelefono;
	EditableTextField _txtTipo;
	
	Juzgado _juzgado;

	public VerJuzgado(Juzgado juzgado) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		setTitle("Ver juzgado");
		_juzgado = juzgado;
		
		_txtNombre = new EditableTextField("Nombre: ", _juzgado.getNombre());
		_txtCiudad = new EditableTextField("Ciudad: ", _juzgado.getCiudad());
		_txtDireccion = new EditableTextField("Direccion: ", _juzgado.getDireccion());
		_txtTelefono = new EditableTextField("Teléfono: ", _juzgado.getTelefono());
		_txtTipo = new EditableTextField("Tipo: ", _juzgado.getTipo());
		
		add(_txtNombre);
		add(_txtCiudad);
		add(_txtDireccion);
		add(_txtTelefono);
		add(_txtTipo);
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
}
