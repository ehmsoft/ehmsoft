package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.container.MainScreen;
import core.CampoPersonalizado;

public class VerCampoPersonalizado extends MainScreen {

	private EditableTextField _txtNombre;
	private EditableTextField _txtValor;
	private CheckboxField _cfObligatorio;
	private EditableTextField _txtLongMax;
	private EditableTextField _txtLongMin;

	private CampoPersonalizado _campoPersonalizado;

	public VerCampoPersonalizado(CampoPersonalizado campo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_campoPersonalizado = campo;

		setTitle("Ver camopo personalizado");

		_txtNombre = new EditableTextField("Nombre: ",
				_campoPersonalizado.getNombre());

		_txtValor = new EditableTextField("Valor: ",
				_campoPersonalizado.getValor());

		_cfObligatorio = new CheckboxField(" Obligatorio", _campoPersonalizado
				.isObligatorio().booleanValue());
		_cfObligatorio.setEditable(false);

		_txtLongMax = new EditableTextField(BasicEditField.FILTER_INTEGER);
		_txtLongMax.setLabel("Longitud máxima");
		_txtLongMax.setText(_campoPersonalizado.getLongitudMax() + "");

		_txtLongMin = new EditableTextField(BasicEditField.FILTER_INTEGER);
		_txtLongMin.setLabel("Longitud minima");
		_txtLongMin.setText(_campoPersonalizado.getLongitudMin() + "");

		add(_txtNombre);
		add(_txtValor);
		add(_cfObligatorio);
		add(_txtLongMax);
		add(_txtLongMin);
		addMenuItem(menuGuardar);
		addMenuItem(menuEditar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
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
				_txtLongMax.setEditable();
				_txtLongMin.setFocus();
			}
		}
	};

	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getValor() {
		return _txtValor.getText();
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
}