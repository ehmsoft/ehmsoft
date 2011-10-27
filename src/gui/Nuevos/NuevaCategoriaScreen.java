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
		_txtDescripcion = new BasicEditField(TextField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripción: ");

		add(_txtDescripcion);
		addMenuItem(menuGuardar);
		addMenuItem(menuCerrar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private MenuItem menuCerrar = new MenuItem("Salir de Aplicación",
			1000000000, 15) {

		public void run() {
			fieldChangeNotify(Util.CERRAR);
			if (!getScreen().isVisible()) {
				System.exit(0);
			}
		}
	};

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
