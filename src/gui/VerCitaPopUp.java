package gui;

import java.util.Date;

import net.rim.device.api.i18n.DateFormat;
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

public class VerCitaPopUp extends PopupScreen {

	private BasicEditField _txtDescripcion;
	private CheckboxField _cbAlarma;
	private BasicEditField _txtTiempo;
	private ObjectChoiceField _nfTiempo;
	private GridFieldManager _g;
	private SeparatorField _s;
	private ButtonField _btnAceptar;
	private ButtonField _btnCancelar;
	private DateField _dfFecha;
	
	public final int GUARDAR = 1;
	public final int CERRAR = 3;

	public VerCitaPopUp() {
		super(new VerticalFieldManager());

		LabelField labelField = new LabelField("Ver cita", Field.FIELD_HCENTER);
		add(labelField);

		_g = new GridFieldManager(1, 2, 8);
		_g.setColumnProperty(0, GridFieldManager.FIXED_SIZE, 200);
		_g.setColumnProperty(1, GridFieldManager.PREFERRED_SIZE, 20);

		_txtDescripcion = new BasicEditField("Descripción: ", "");
		add(_txtDescripcion);

		_dfFecha = new DateField("Fecha: ", 0, DateFormat.DATE_FULL | DateFormat.TIME_LONG);
		add(_dfFecha);

		_nfTiempo = new ObjectChoiceField(null, null, 0, FIELD_LEFT);

		_txtTiempo = new BasicEditField("Anticipación: ", null, 3,
				BasicEditField.FILTER_INTEGER);

		_g.add(_txtTiempo);
		_g.add(_nfTiempo);

		_cbAlarma = new CheckboxField("Alarma", false);
		_cbAlarma.setChangeListener(listenerTiempo);
		add(_cbAlarma);
		
		_s = new SeparatorField();
		add(_s);
		
		_btnAceptar = new ButtonField("Aceptar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnAceptar.setMinimalWidth(100);
		_btnAceptar.setChangeListener(listenerAceptar);
		add(_btnAceptar);
		
		_btnCancelar = new ButtonField("Cancelar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnCancelar.setChangeListener(listenerCancelar);
		add(_btnCancelar);
	}

	private FieldChangeListener listenerTiempo = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (_cbAlarma.getChecked()) {
				delete(_s);
				delete(_btnAceptar);
				delete(_btnCancelar);
				add(_g);
				add(_s);
				add(_btnAceptar);
				add(_btnCancelar);
			} else {
				delete(_g);
			}
		}
	};

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
	
	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}
	
	public void setDescripcion(String text) {
		_txtDescripcion.setText(text);
	}
	
	public void setFecha(Date date) {
		_dfFecha.setDate(date);
	}
	
	public void setDuracion(String text) {
		_txtTiempo.setText(text);
	}
	
	public void setIntervalo(int index) {
		_nfTiempo.setSelectedIndex(index);
	}
	
	public void setChoices(Object[] choices) {
		_nfTiempo.setChoices(choices);
	}

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public Date getFecha() {
		return new Date(_dfFecha.getDate());
	}

	public int getDuracion() {
		return Integer.parseInt(_txtTiempo.getText());
	}

	public int getIntervalo() {
		return _nfTiempo.getSelectedIndex();
	}
	
	public boolean hasAlarma() {
		return _cbAlarma.getChecked();
	}

/*	public class ListenerKey implements KeyListener {
		// Implement methods in the KeyListener interface for handling keyboard
		// events:
		public boolean keyChar(char key, int status, int time) {
			return false;
		}

		public boolean keyDown(int keycode, int time) {
			if (keycode == 1769472) {
				if (isCambiado()) {
					Object[] ask = { "Guardar", "Descartar", "Cancelar" };
					int sel = Dialog.ask("Se han detectado cambios", ask, 1);
					if (sel == 0) {
						_guardar = true;
						UiApplication.getUiApplication().popScreen(getScreen());
					} else if (sel == 1) {
						UiApplication.getUiApplication().popScreen(getScreen());
					}
				} else {
					UiApplication.getUiApplication().popScreen(getScreen());
				}
				return true;
			} else {
				return false;
			}
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}

		public boolean keyUp(int keycode, int time) {
			return false;
		}
	}*/
	
	public boolean onClose() {
		fieldChangeNotify(CERRAR);
		return false;
	}
}
