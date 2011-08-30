package gui.Ver;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.TextField;

public class VerCategoriaScreen extends FondoNormal {

	private EditableTextField _txtDescripcion;

	public VerCategoriaScreen() {
		setTitle("Ver categoría");

		_txtDescripcion = new EditableTextField("Nombre: ",
				TextField.NO_NEWLINE);

		add(_txtDescripcion);
	}

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuEditar);
		menu.addSeparator();
		menu.add(menuEliminar);
		menu.add(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			_txtDescripcion.setEditable();
		}
	};

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR);
		}
	};

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public void setDescripcion(String text) {
		_txtDescripcion.setText(text);
	}

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}
