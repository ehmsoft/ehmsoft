package gui.Nuevos;

import gui.Cita;

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
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevaCitaPopUp extends PopupScreen {

	private BasicEditField _txtDescripcion;
	private CheckboxField _cbAlarma;
	private BasicEditField _txtTiempo;
	private ObjectChoiceField _ocTiempo;
	private DateField _dfFecha;
	private VerticalFieldManager _bottom;

	public final int GUARDAR = 1;
	public final int CERRAR = 2;

	public NuevaCitaPopUp() {
		super(new VerticalFieldManager());

		LabelField labelField = new LabelField("Crear cita",
				Field.FIELD_HCENTER);
		add(labelField);
		add(new SeparatorField());

		_txtDescripcion = new BasicEditField("Descripción: ", "");		

		_dfFecha = new DateField("Fecha: ", System.currentTimeMillis(),
				DateField.DATE_TIME);

		_cbAlarma = new CheckboxField("Alarma", false);
		_cbAlarma.setChangeListener(listenerTiempo);

		_txtTiempo = new BasicEditField("Anticipación: ", null, 3,
				BasicEditField.FILTER_INTEGER);
		_txtTiempo.setText("5");

		Object[] choices = {Cita.MINUTOS, Cita.HORAS, Cita.DIAS};
		_ocTiempo = new ObjectChoiceField(null, choices, 0, USE_ALL_WIDTH | FIELD_LEFT);

		ButtonField btnAceptar = new ButtonField("Aceptar", Field.FIELD_HCENTER);
		ButtonField btnCancelar = new ButtonField("Cancelar", Field.FIELD_HCENTER);
		btnAceptar.setChangeListener(listenerAceptar);	
		btnCancelar.setChangeListener(listenerCancelar);
		
		_bottom = new VerticalFieldManager();
		_bottom.add(new SeparatorField());
		_bottom.add(btnAceptar);
		_bottom.add(btnCancelar);
		
		add(_txtDescripcion);
		add(_dfFecha);
		add(_cbAlarma);

		add(_bottom);
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
				delete(_bottom);
				add(_txtTiempo);
				add(_ocTiempo);
				add(_bottom);
			} else {
				delete(_txtTiempo);
				delete(_ocTiempo);
			}
		}
	};
	
	public void setDescripcion(String text) {
		_txtDescripcion.setText(text);
	}
	
	public void setFecha(Date date) {
		_dfFecha.setDate(date);
	}

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

	public Object[] getAlarma() {
		Object[] alarma = new Object[2];
		alarma[0] = new Integer(Integer.parseInt(_txtTiempo.getText()));
		alarma[1] = _ocTiempo.getChoice(_ocTiempo.getSelectedIndex());
		return alarma;
	}
	
	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}
