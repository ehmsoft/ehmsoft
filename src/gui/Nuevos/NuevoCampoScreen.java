package gui.Nuevos;

import gui.FondoNormal;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.TextField;

public class NuevoCampoScreen extends FondoNormal {

	/**
	 * Crea una nueva ventana para la creación de un nuevo campo personalizado
	 */
	private BasicEditField _txtNombre;
	private CheckboxField _cbObligatorio;
	private BasicEditField _txtLongMax;
	private BasicEditField _txtLongMin;

	public final int GUARDAR = 1;
	public final int CERRAR = 2;

	public NuevoCampoScreen() {

		setTitle("Nuevo campo personalizado");

		_txtNombre = new BasicEditField(TextField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");

		_cbObligatorio = new CheckboxField(" Obligatorio", false);

		_txtLongMax = new BasicEditField(TextField.NO_NEWLINE
				| BasicEditField.FILTER_INTEGER);
		_txtLongMax.setLabel("Longitud máxima: ");

		_txtLongMin = new BasicEditField(TextField.NO_NEWLINE
				| BasicEditField.FILTER_INTEGER);
		_txtLongMin.setLabel("Longitud mínima: ");

		add(_txtNombre);
		add(_cbObligatorio);
		add(_txtLongMax);
		add(_txtLongMin);

		addMenuItem(menuGuardar);
		addMenuItem(menuCerrar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 0) {

		public void run() {
			fieldChangeNotify(GUARDAR);
		}
	};

	private MenuItem menuCerrar = new MenuItem("Salir de Aplicación",
			1000000000, 15) {

		public void run() {
			fieldChangeNotify(CERRAR);
			if (!getScreen().isVisible()) {
				System.exit(0);
			}
		}
	};

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public String getNombre() {
		return _txtNombre.getText();
	}

	public boolean isObligatorio() {
		return _cbObligatorio.getChecked();
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

	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}