package gui;

import ehmsoft.NuevaPersonaController;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.container.MainScreen;

public class NuevaPersona extends MainScreen {

	/**
	 * 
	 */
	private NuevaPersonaController controller;
	private BasicEditField nombre;
	private BasicEditField id;
	private BasicEditField direccion;
	private BasicEditField telefono;
	private BasicEditField correo;
	private BasicEditField notas;
	public NuevaPersona(NuevaPersonaController controller) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		this.controller = controller;
		
		if(this.controller.getTipo() == (short)1)
			setTitle("Nuevo Demandante");
		else
			setTitle("Nuevo Demandado");
		
		//Se inicializan con el estilo
		this.nombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		this.nombre.setLabel("Nombre: ");
		this.id = new BasicEditField(BasicEditField.NO_NEWLINE);
		this.id.setLabel("Id: ");
		this.direccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		this.direccion.setLabel("Dirección: ");
		this.notas = new BasicEditField("Notas: ", "");		
		this.telefono = new BasicEditField(BasicEditField.FILTER_PHONE);
		this.telefono.setLabel("Teléfono: ");
		this.correo = new BasicEditField(BasicEditField.FILTER_EMAIL);
		this.correo.setLabel("Correo: ");
		
		//Se agregan los elementos a la pantalla
		
		add(this.nombre);
		add(this.id);
		add(this.direccion);
		add(this.telefono);
		add(this.correo);
		add(this.notas);
		addMenuItem(menuGuardar);
	}
	
	private final MenuItem menuGuardar = new MenuItem("Guardar",0,0) {
		
		public void run() {
			// TODO Auto-generated method stub
			controller.guardarPersona();
		}
	};

	public String getNombre() {
		
		return nombre.getText();
	}

	public String getId() {
		return id.getText();
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

}
