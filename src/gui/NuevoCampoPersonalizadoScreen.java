package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevoCampoPersonalizadoScreen extends MainScreen {

	/**
	 * Crea una nueva ventana para la creación de un nuevo campo personalizado
	 */
	private BasicEditField _txtNombre;
	private CheckboxField _cbObligatorio;
	private BasicEditField _txtLongMax;
	private BasicEditField _txtLongMin;

	public NuevoCampoPersonalizadoScreen() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Nuevo campo personalizado");
		VerticalFieldManager vertical = new VerticalFieldManager();

		_txtNombre = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");

		_cbObligatorio = new CheckboxField(" Obligatorio", false);

		_txtLongMax = new BasicEditField(BasicEditField.NO_NEWLINE
				| BasicEditField.FILTER_INTEGER);
		_txtLongMax.setLabel("Longitud máxima: ");

		_txtLongMin = new BasicEditField(BasicEditField.NO_NEWLINE
				| BasicEditField.FILTER_INTEGER);
		_txtLongMin.setLabel("Longitud mínima: ");

		vertical.add(_txtNombre);
		vertical.add(_cbObligatorio);
		vertical.add(_txtLongMax);
		vertical.add(_txtLongMin);

		add(vertical);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			// TODO Auto-generated method stub
			if (getLongMax() >= getLongMin())
				UiApplication.getUiApplication().popScreen(getScreen());
			else
				Dialog.alert("La longitud mínima no puede ser mayor que la longitud máxima, corrija y guarde nuevamente");
		}
	};

	public String getNombre() {
		return _txtNombre.getText();
	}

	public Boolean isObligatorio() {
		return new Boolean(_cbObligatorio.getChecked());
	}

	public int getLongMax() {
		int lon;
		try {
			lon = Integer.parseInt(_txtLongMax.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
		return lon;
	}

	public int getLongMin() {
		int lon;
		try {
			lon = Integer.parseInt(_txtLongMin.getText());
		} catch (NumberFormatException e) {
			return 0;
		}
		return lon;
	}
}