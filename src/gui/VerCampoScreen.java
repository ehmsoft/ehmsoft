package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.Dialog;
import core.CampoPersonalizado;

public class VerCampoScreen extends FondoNormal {

	private EditableTextField _txtNombre;
	private EditableTextField _txtValor;
	private CheckboxField _cfObligatorio;
	private EditableTextField _txtLongMax;
	private EditableTextField _txtLongMin;

	private CampoPersonalizado _campoPersonalizado;

	private boolean _guardar;
	private boolean _eliminar;

	public VerCampoScreen(CampoPersonalizado campo) {
		_guardar = false;

		_campoPersonalizado = campo;

		setTitle("Ver camopo personalizado");

		_txtNombre = new EditableTextField("Nombre: ",
				_campoPersonalizado.getNombre());
		add(_txtNombre);
		
		if(_campoPersonalizado.getValor() != null) {
			_txtValor = new EditableTextField("Valor: ",
					_campoPersonalizado.getValor());
			add(_txtValor);
		}

		_cfObligatorio = new CheckboxField(" Obligatorio", _campoPersonalizado
				.isObligatorio().booleanValue());
		_cfObligatorio.setEditable(false);
		add(_cfObligatorio);

		_txtLongMax = new EditableTextField(BasicEditField.FILTER_INTEGER);
		_txtLongMax.setLabel("Longitud máxima");
		_txtLongMax.setText(_campoPersonalizado.getLongitudMax() + "");
		add(_txtLongMax);

		_txtLongMin = new EditableTextField(BasicEditField.FILTER_INTEGER);
		_txtLongMin.setLabel("Longitud minima");
		_txtLongMin.setText(_campoPersonalizado.getLongitudMin() + "");
		add(_txtLongMin);

 		addMenuItem(menuGuardar);
		addMenuItem(menuEditar);
		addMenuItem(menuEditarTodo);
		addMenuItem(menuEliminar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();

			if (f.equals(_txtNombre)) {
				_txtNombre.setEditable();
				_txtNombre.setFocus();
			}
			if (f.equals(_txtValor)) {
				_txtValor.setEditable();
				_txtValor.setFocus();
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
			_txtValor.setEditable();
			_cfObligatorio.setEditable(true);
			_txtLongMax.setEditable();
			_txtLongMin.setEditable();
		}
	};

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desea eliminar el campo?", ask, 1);
			if (sel == 0) {
				_eliminar = true;
			}
		}
	};

	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getValor() {
		if(_txtValor != null) {
			return _txtValor.getText();
		} else {
			return null;
		}
	}

	public Boolean isObligatorio() {
		return new Boolean(_cfObligatorio.getChecked());
	}

	public int getLongitudMax() {
		return Integer.parseInt(_txtLongMax.getText());
	}

	public int getLongitudMin() {
		return Integer.parseInt(_txtLongMin.getText());
	}

	public CampoPersonalizado getCampoPersonalizado() {
		return _campoPersonalizado;
	}

	public boolean isGuardado() {
		return _guardar;
	}

	public boolean isEliminado() {
		return _eliminar;
	}

	public boolean onClose() {
		boolean cambio = false;
		if (!_campoPersonalizado.getNombre().equals(this.getNombre()))
			cambio = true;
		else if(_campoPersonalizado.getValor() != null) {
			if (!_campoPersonalizado.getValor().equals(this.getValor()))
				cambio = true;
		}
		else if (!_campoPersonalizado.isObligatorio().equals(this.isObligatorio()))
			cambio = true;
		else if (_campoPersonalizado.getLongitudMax() != this.getLongitudMax())
			cambio = true;
		else if (_campoPersonalizado.getLongitudMin() != this.getLongitudMin())
			cambio = true;
		if (!cambio) {
			UiApplication.getUiApplication().popScreen(getScreen());
			return true;
		} else {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = Dialog.ask("Se han detectado cambios", ask, 1);
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