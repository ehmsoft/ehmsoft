package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.container.MainScreen;

public class NuevoJuzgado extends MainScreen {

	/**
	 * 
	 */
	
	private BasicEditField _txtNombre;
	private BasicEditField _txtCiudad;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtTipo;
	private BasicEditField _txtId_juzgado;
	
	public NuevoJuzgado() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Nuevo juzgado");
		
		_txtNombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");
		
		_txtCiudad = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtCiudad.setLabel("Ciudad: ");
		
		_txtDireccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDireccion.setLabel("Direcci�n: ");
		
		_txtTelefono = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtTelefono.setLabel("Tel�fono: ");
		
		_txtTipo = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtTipo.setLabel("Tipo: ");
		
		_txtId_juzgado = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtId_juzgado.setLabel("Id del juzgado: ");
		
		add(_txtNombre);
		add(_txtCiudad);
		add(_txtDireccion);
		add(_txtTelefono);
		add(_txtTipo);
		add(_txtId_juzgado);
		addMenuItem(menuGuardar);
	}
	
	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			// TODO Auto-generated method stub
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};
	
	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getCiudad() {
		return _txtCiudad.getText();
	}

	public String getDireccion() {
		return _txtDireccion.getText();
	}

	public String getTelefono() {
		return _txtTelefono.getText();
	}

	public String getTipo() {
		return _txtTipo.getText();
	}

	public String getId_juzgado() {
		return _txtId_juzgado.getText();
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
