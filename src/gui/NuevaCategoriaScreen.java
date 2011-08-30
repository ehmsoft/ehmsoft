package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;

public class NuevaCategoriaScreen extends FondoNormal implements
		NuevaCategoriaInterface {

	private BasicEditField _txtDescripcion;

	public static final int GUARDAR = 1;
	public static final int CERRAR = 2;

	public NuevaCategoriaScreen() {
		setTitle("Nueva categor�a");

		_txtDescripcion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripcion: ");

		add(_txtDescripcion);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(GUARDAR);
		}
	};

	public void alert(String string) {
		Dialog.alert(string);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}
