package gui.Ver;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;

public class VerCampoScreen extends FondoNormal {

	private EditableTextField _txtNombre;
	private CheckboxField _cfObligatorio;
	private EditableTextField _txtLongMax;
	private EditableTextField _txtLongMin;

	public VerCampoScreen() {
		setTitle("Ver campo personalizado");

		_txtNombre = new EditableTextField("Nombre: ", "");
		add(_txtNombre);

		_cfObligatorio = new CheckboxField();
		_cfObligatorio.setLabel(" Obligatorio");
		_cfObligatorio.setEditable(false);
		add(_cfObligatorio);

		_txtLongMax = new EditableTextField("Longitud máxima: ", BasicEditField.FILTER_INTEGER);
		add(_txtLongMax);

		_txtLongMin = new EditableTextField("Longitud minima: ", BasicEditField.FILTER_INTEGER);
		add(_txtLongMin);

 		addMenuItem(menuGuardar);
		addMenuItem(menuEditar);
		addMenuItem(menuEditarTodo);
		addMenuItem(menuEliminar);
	}
	
	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuEditar);
		menu.add(menuEditarTodo);
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
			Field f = getFieldWithFocus();

			if (f.equals(_txtNombre)) {
				_txtNombre.setEditable();
				_txtNombre.setFocus();
			}
			if (f.equals(_cfObligatorio)) {
				_cfObligatorio.setEditable(true);
				_cfObligatorio.setFocus();
			}
			if (f.equals(_txtLongMax)) {
				_txtLongMax.setEditable();
				_txtLongMin.setFocus();
			}
			if (f.equals(_txtLongMin)) {
				_txtLongMin.setEditable();
				_txtLongMin.setFocus();
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 0, 0) {

		public void run() {
			_txtNombre.setEditable();
			_cfObligatorio.setEditable(true);
			_txtLongMax.setEditable();
			_txtLongMin.setEditable();
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
	
	public void setNombre(String text) {
		_txtNombre.setText(text);
	}

	public void setObligatorio(boolean obligatorio) {
		_cfObligatorio.setChecked(obligatorio);
	}

	public void setLongitudMax(String text) {
		_txtLongMax.setText(text);
	}

	public void setLongitudMin(String text) {
		_txtLongMin.setText(text);
	}

	public String getNombre() {
		return _txtNombre.getText();
	}

	public Boolean isObligatorio() {
		return new Boolean(_cfObligatorio.getChecked());
	}

	public int getLongitudMax() {
		if (_txtLongMax.getText().length() == 0) {
			return 0;
		} else {
			return Integer.parseInt(_txtLongMax.getText());
		}
	}

	public int getLongitudMin() {
		if(_txtLongMin.getText().length() == 0) {
			return 0;
		} else {
			return Integer.parseInt(_txtLongMin.getText());
		}
	}

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}