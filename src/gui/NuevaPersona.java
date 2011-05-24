package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;

public class NuevaPersona extends MainScreen {

	private int _tipo;
	private BasicEditField _txtNombre;
	private BasicEditField _txtCedula;
	private BasicEditField _txtDireccion;
	private BasicEditField _txtTelefono;
	private BasicEditField _txtCorreo;
	private BasicEditField _txtNotas;

	public NuevaPersona(int tipo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		
		if (tipo == 1)
			setTitle("Nuevo demandante");
		else
			setTitle("Nuevo demandado");
		
		this.getMainManager().setBackground(BackgroundFactory.createLinearGradientBackground(0x0099CCFF,0x0099CCFF,0x00336699,0x00336699));
		
		VerticalFieldManager vertical = new VerticalFieldManager();
		Bitmap borderBitmap = Bitmap.getBitmapResource("rounded-border.png");
		vertical.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,12,12,12), borderBitmap));

		// Se inicializan con el estilo
		this._txtNombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		this._txtNombre.setLabel("Nombre: ");
		this._txtCedula = new BasicEditField(BasicEditField.NO_NEWLINE);
		this._txtCedula.setLabel("Id: ");
		this._txtDireccion = new BasicEditField(BasicEditField.NO_NEWLINE);
		this._txtDireccion.setLabel("Dirección: ");
		this._txtNotas = new BasicEditField("Notas: ", "");
		this._txtTelefono = new BasicEditField(BasicEditField.FILTER_PHONE);
		this._txtTelefono.setLabel("Teléfono: ");
		this._txtCorreo = new BasicEditField(BasicEditField.FILTER_EMAIL);
		this._txtCorreo.setLabel("Correo: ");
		// Se agregan los elementos a la pantalla

		vertical.add(this._txtNombre);
		vertical.add(new SeparatorField());
		vertical.add(this._txtCedula);
		vertical.add(new SeparatorField());
		vertical.add(this._txtDireccion);
		vertical.add(new SeparatorField());
		vertical.add(this._txtTelefono);
		vertical.add(new SeparatorField());
		vertical.add(this._txtCorreo);
		vertical.add(new SeparatorField());
		vertical.add(this._txtNotas);
		add(vertical);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			// TODO Auto-generated method stub
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	public int getTipo() {
		return _tipo;
	}

	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getCedula() {
		return _txtCedula.getText();
	}

	public String getDireccion() {
		return _txtDireccion.getText();
	}

	public String getTelefono() {
		return _txtTelefono.getText();
	}

	public String getCorreo() {
		return _txtCorreo.getText();
	}

	public String getNotas() {
		return _txtNotas.getText();
	}
	
	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}