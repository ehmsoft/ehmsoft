package gui;

import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.CheckboxField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.GridFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevaCitaPopUp extends PopupScreen {

	private BasicEditField _txtDescripcion;
	private CheckboxField _cbAlarma;
	private BasicEditField _txtTiempo;
	private ObjectChoiceField _ocTiempo;
	private GridFieldManager _grid;
	private SeparatorField _separator;
	private ButtonField _btnAceptar;
	private ButtonField _btnCancelar;
	private LabelField _lblFecha;
	private DateField _dfFecha;

	public final int GUARDAR = 1;
	public final int CERRAR = 2;

	public NuevaCitaPopUp(String descripcion, Date fecha) {
		super(new VerticalFieldManager());

		LabelField labelField = new LabelField("Crear cita",
				Field.FIELD_HCENTER);
		add(labelField);
		add(new SeparatorField());

		_grid = new GridFieldManager(1, 2, 8);
		_grid.setColumnProperty(0, GridFieldManager.FIXED_SIZE, 200);
		_grid.setColumnProperty(1, GridFieldManager.PREFERRED_SIZE, 20);

		_txtDescripcion = new BasicEditField("Descripci�n: ", descripcion);
		add(_txtDescripcion);

		_lblFecha = new LabelField(fecha.toString());
		add(_lblFecha);

		_dfFecha = new DateField("Fecha: ", fecha.getTime(),
				DateField.DATE_TIME);
		add(_dfFecha);

		_cbAlarma = new CheckboxField("Alarma", false);
		_cbAlarma.setChangeListener(listenerTiempo);
		add(_cbAlarma);

		_txtTiempo = new BasicEditField("Anticipaci�n: ", null, 3,
				BasicEditField.FILTER_INTEGER);
		_txtTiempo.setText("5");

		Object[] choices = { "Minutos", "Horas", "D�as" };
		_ocTiempo = new ObjectChoiceField(null, choices, 0, FIELD_LEFT);

		_grid.add(_txtTiempo);
		_grid.add(_ocTiempo);

		_separator = new SeparatorField();
		add(_separator);
		_btnAceptar = new ButtonField("Aceptar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnAceptar.setChangeListener(listenerAceptar);
		add(_btnAceptar);
		_btnCancelar = new ButtonField("Cancelar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnCancelar.setChangeListener(listenerCancelar);
		add(_btnCancelar);
	}

	private FieldChangeListener listenerAceptar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(GUARDAR);
		}
	};

	private FieldChangeListener listenerCancelar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(CERRAR);
		}
	};

	private FieldChangeListener listenerTiempo = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (_cbAlarma.getChecked()) {
				delete(_separator);
				delete(_btnAceptar);
				delete(_btnCancelar);
				add(_grid);
				add(_separator);
				add(_btnAceptar);
				add(_btnCancelar);
			} else {
				delete(_grid);
			}
		}
	};

	public void setAlarma(boolean alarma) {
		_cbAlarma.setChecked(alarma);
	}

	public void alert(String string) {
		Dialog.alert(string);
	}

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public Date getFecha() {
		return new Date(_dfFecha.getDate());
	}

	public boolean hasAlarma() {
		return _cbAlarma.getChecked();
	}

	public int getDuracion() {
		return Integer.parseInt(_txtTiempo.getText());
	}

	public String getIntervalo() {
		return (String) _ocTiempo.getChoice(_ocTiempo.getSelectedIndex());
	}
}
