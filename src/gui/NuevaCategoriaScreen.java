package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;

public class NuevaCategoriaScreen extends FondoNormal {

	private BasicEditField _txtDescripcion;
	private boolean _guardar = false;

	public NuevaCategoriaScreen() {
		setTitle("Nueva categoría");

		_txtDescripcion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripcion: ");

		add(_txtDescripcion);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			if (_txtDescripcion.getTextLength() == 0) {
				Dialog.inform("Todos los campos están vacíos, no se guardará");
				UiApplication.getUiApplication().popScreen(getScreen());
			} else {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public boolean isGuardado() {
		return _guardar;
	}

	public boolean onClose() {
		if (_txtDescripcion.getTextLength() != 0) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 2);
			if (sel == 0) {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
			if (sel == 1) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			}
			if (sel == 2) {
				return false;
			}
		} else {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		}
		return false;
	}
}
