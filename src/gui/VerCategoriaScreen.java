package gui;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Categoria;

public class VerCategoriaScreen extends MainScreen {

	private EditableTextField _txtDescripcion;

	private Categoria _categoria;

	private boolean _guardar;
	private boolean _eliminar;

	public VerCategoriaScreen(Categoria categoria) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_guardar = false;

		_categoria = categoria;

		setTitle("Ver categoría");

		_txtDescripcion = new EditableTextField("Nombre: ",
				_categoria.getDescripcion(), BasicEditField.NO_NEWLINE);

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
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			EditableTextField f = (EditableTextField) getFieldWithFocus();
			if (f.isEditable())
				;
			else {
				f.setEditable();
				f.setFocus();
			}
		}
	};

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desea eliminar la categoría?", ask, 1);
			if (sel == 0) {
				_eliminar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public Categoria getCategoria() {
		return _categoria;
	}

	public boolean isGuardado() {
		return _guardar;
	}

	public boolean isEliminado() {
		return _eliminar;
	}

	public boolean onClose() {
		boolean cambio = false;
		if (!_categoria.getDescripcion().equals(this.getDescripcion()))
			cambio = true;
		if (!cambio) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 2);
			if (sel == 0) {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else {
				return false;
			}
		}
	}
}
