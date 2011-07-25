package gui;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
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

	private boolean _guardar = false;

	private final String _descripcion;
	private final Date _date;
	private final int _alarma;

	public VerCitaPopUp(String descripcion, Date date, int alarma) {
		super(new VerticalFieldManager());

		_descripcion = descripcion;
		_date = date;
		_alarma = alarma;

		LabelField labelField = new LabelField("Ver cita", Field.FIELD_HCENTER);
		add(labelField);

		_g = new GridFieldManager(1, 2, 8);
		_g.setColumnProperty(0, GridFieldManager.FIXED_SIZE, 200);
		_g.setColumnProperty(1, GridFieldManager.PREFERRED_SIZE, 20);

		_txtDescripcion = new BasicEditField("Descripción: ", descripcion);

		_dfFecha = new DateField("Fecha: ", date.getTime(), DateField.DATE_TIME);

		Object[] choices = { "Minutos", "Horas", "Días" };
		_nfTiempo = new ObjectChoiceField(null, choices, 0, FIELD_LEFT);

		if (alarma < 3600) {
			_nfTiempo.setSelectedIndex("Minutos");
			alarma = alarma / 60;
		} else if (alarma < 86400) {
			_nfTiempo.setSelectedIndex("Horas");
			alarma = alarma / 3600;
		} else if (alarma >= 86400) {
			_nfTiempo.setSelectedIndex("Días");
			alarma = alarma / 86400;
		}

		_txtTiempo = new BasicEditField("Anticipación: ", null, 3,
				BasicEditField.FILTER_INTEGER);
		if (alarma > 0) {
			_txtTiempo.setText(alarma + "");
		} else {
			_txtTiempo.setText("5");
		}

		_g.add(_txtTiempo);
		_g.add(_nfTiempo);

		_cbAlarma = new CheckboxField("Alarma", false);
		_cbAlarma.setChangeListener(listenerTiempo);
		
		_btnAceptar = new ButtonField("Aceptar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnAceptar.setMinimalWidth(100);
		_btnAceptar.setChangeListener(listenerAceptar);
		
		_btnCancelar = new ButtonField("Cancelar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnCancelar.setChangeListener(listenerCancelar);
		
		_s = new SeparatorField();
		
		add(_txtDescripcion);
		add(_dfFecha);		
		
		add(_cbAlarma);

		add(_s);
		
		add(_btnAceptar);
		add(_btnCancelar);

		if (alarma > 0) {
			_cbAlarma.setChecked(true);
		} else {
			_cbAlarma.setChecked(false);
		}
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
			if (isCambiado()) {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};

	private FieldChangeListener listenerCancelar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
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
		}
	};

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public Date getFecha() {
		return new Date(_dfFecha.getDate());
	}

	public boolean isGuardado() {
		return _guardar;
	}

	public boolean isCambiado() {
		boolean cambio = false;
		Calendar f1 = Calendar.getInstance();
		f1.setTime(_date);
		Calendar f2 = Calendar.getInstance();
		f2.setTime(getFecha());
		int duracion = getDuracion();

		if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
				|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
				|| (f1.get(Calendar.DAY_OF_MONTH) != f2
						.get(Calendar.DAY_OF_MONTH))
				|| (f1.get(Calendar.HOUR_OF_DAY) != f2
						.get(Calendar.HOUR_OF_DAY))) {
			cambio = true;
		} else if (!_descripcion.equals(getDescripcion())) {
			cambio = true;
		} else if (_alarma != duracion) {
			cambio = true;
		}
		return cambio;
	}

	public int getDuracion() {
		int duracion = Integer.parseInt(_txtTiempo.getText());
		if (getIntervalo().equals("Días")) {
			duracion = duracion * 86400;
		} else if (getIntervalo().equals("Horas")) {
			duracion = duracion * 3600;
		} else if (getIntervalo().equals("Minutos")) {
			duracion = duracion * 60;
		}

		return duracion;
	}

	private String getIntervalo() {
		return (String) _nfTiempo.getChoice(_nfTiempo.getSelectedIndex());
	}
	
	public boolean hasAlarma() {
		return _cbAlarma.getChecked();
	}

	public class ListenerKey implements KeyListener {
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
	}
}
