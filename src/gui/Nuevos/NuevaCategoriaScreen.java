package gui.Nuevos;

import gui.FondoNormal;
import gui.Util;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.TextField;

public class NuevaCategoriaScreen extends FondoNormal implements
		NuevaCategoriaInterface {

	private BasicEditField _txtDescripcion;

	public NuevaCategoriaScreen() {
		setTitle("Nueva categor�a");

		_txtDescripcion = new BasicEditField(TextField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripci�n: ");

		add(_txtDescripcion);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
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
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}
