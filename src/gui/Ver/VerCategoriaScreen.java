package gui.Ver;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.TextField;

public class VerCategoriaScreen extends FondoNormal {

	private EditableTextField _txtDescripcion;

	public VerCategoriaScreen() {
		_txtDescripcion = new EditableTextField("Nombre: ",
				TextField.NO_NEWLINE);

		add(_txtDescripcion);
	}

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuEditar);
		menu.add(menuEliminar);
		menu.add(menuGuardar);
		menu.add(menuCerrar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 131075, 1) {

		public void run() {
			_txtDescripcion.setEditable();
		}
	};

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 131075, 2) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR);
		}
	};
	private MenuItem menuCerrar = new MenuItem("Salir de Aplicación",
			1000000000, 9) {

		public void run() {
			fieldChangeNotify(Util.CERRAR);

			UiApplication.getUiApplication().invokeLater(new Runnable() {
				
				public void run() {
					while (getScreen().isVisible());
					System.exit(0);
				}
			});
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
