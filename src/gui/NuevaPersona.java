package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.container.MainScreen;

public class NuevaPersona extends MainScreen {

	private int tipo;
	private BasicEditField nombre;
	private BasicEditField cedula;
	private BasicEditField direccion;
	private BasicEditField telefono;
	private BasicEditField correo;
	private BasicEditField notas;

	public NuevaPersona(int tipo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		if (tipo == 1)
			setTitle("Nuevo demandante");
		else
			setTitle("Nuevo demandado");

		// Se inicializan con el estilo
		this.nombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		this.nombre.setLabel("Nombre: ");
		this.cedula = new BasicEditField(BasicEditField.NO_NEWLINE);
		this.cedula.setLabel("Id: ");
		this.direccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		this.direccion.setLabel("Dirección: ");
		this.notas = new BasicEditField("Notas: ", "");
		this.telefono = new BasicEditField(BasicEditField.FILTER_PHONE);
		this.telefono.setLabel("Teléfono: ");
		this.correo = new BasicEditField(BasicEditField.FILTER_EMAIL);
		this.correo.setLabel("Correo: ");
		// Se agregan los elementos a la pantalla

		add(this.nombre);
		add(this.cedula);
		add(this.direccion);
		add(this.telefono);
		add(this.correo);
		add(this.notas);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			// TODO Auto-generated method stub
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	public int getTipo() {
		return tipo;
	}

	public String getNombre() {
		return nombre.getText();
	}

	public String getCedula() {
		return cedula.getText();
	}

	public String getDireccion() {
		return direccion.getText();
	}

	public String getTelefono() {
		return telefono.getText();
	}

	public String getCorreo() {
		return correo.getText();
	}

	public String getNotas() {
		return notas.getText();
	}
	
	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}