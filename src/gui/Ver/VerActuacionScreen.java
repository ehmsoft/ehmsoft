package gui.Ver;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class VerActuacionScreen extends FondoNormal {

	private EditableTextField _txtJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private EditableTextField _txtDescripcion;

	private BitmapField _cita;
	private BitmapField _alarm;
	private Bitmap _bell = Bitmap.getBitmapResource("bell.png");
	private Bitmap _clock = Bitmap.getBitmapResource("clock.png");
	private boolean _hasAlarm = false;

	public VerActuacionScreen() {
		HorizontalFieldManager titleContainer = new HorizontalFieldManager();
		titleContainer.add(new LabelField("Actuacion"));

		_cita = new BitmapField(null, Field.FIELD_VCENTER);
		_alarm = new BitmapField(null, Field.FIELD_VCENTER);

		titleContainer.add(_cita);
		titleContainer.add(_alarm);

		setTitle(titleContainer);

		_txtJuzgado = new EditableTextField("Juzgado: ", "");

		_dfFecha = new DateField("Fecha: ", 0, DateField.DATE_TIME);
		_dfFecha.setEditable(false);

		_dfFechaProxima = new DateField("Fecha pr�xima: ", 0,
				DateField.DATE_TIME);
		_dfFechaProxima.setEditable(false);

		_txtDescripcion = new EditableTextField("Descripci�n: ", "");

		add(_txtJuzgado);
		add(_dfFecha);
		add(_dfFechaProxima);
		add(_txtDescripcion);
	}

	public void setClock() {
		_cita.setBitmap(_clock);
		_hasAlarm = true;
	}

	public void setBell() {
		_alarm.setBitmap(_bell);
	}

	public void setDescripcion(String text) {
		_txtDescripcion.setText(text);
	}

	public void setFecha(Date date) {
		_dfFecha.setDate(date);
	}

	public void setFechaProxima(Date date) {
		_dfFechaProxima.setDate(date);
	}

	public void removeClock() {
		_cita.setBitmap(null);
		removeBell();
		_hasAlarm = false;
	}

	public void removeBell() {
		_alarm.setBitmap(null);
	}

	public void setJuzgado(String text) {
		_txtJuzgado.setText(text);
	}

	protected void makeMenu(Menu menu, int instance) {
		Field focus = UiApplication.getUiApplication().getActiveScreen()
				.getFieldWithFocus();
		if (focus.equals(_txtJuzgado)) {
			menu.add(menuCambiar);
		}
		if (_hasAlarm) {
			menu.add(menuVerCita);
			menu.add(menuEliminarCita);
		} else {
			menu.add(menuAddCita);
		}
		menu.add(menuEditar);
		menu.add(menuEditarTodo);
		menu.add(menuEliminarActuacion);
		menu.add(menuGuardar);
		menu.add(menuCerrar);
	}

	private final MenuItem menuVerCita = new MenuItem("Ver cita", 131075, 1) {

		public void run() {
			fieldChangeNotify(Util.VER_CITA);
		}
	};

	private final MenuItem menuAddCita = new MenuItem("Agregar cita", 131075, 1) {

		public void run() {
			fieldChangeNotify(Util.ADD_CITA);
		}
	};

	private final MenuItem menuEliminarCita = new MenuItem("Eliminar cita", 131075,
			2) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR_CITA);
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 262147, 4) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtJuzgado)) {
				fieldChangeNotify(Util.VER_JUZGADO);
			}
			if (f.equals(_dfFecha)) {
				_dfFecha.setEditable(true);
				_dfFecha.setFocus();
			}
			if (f.equals(_dfFechaProxima)) {
				_dfFechaProxima.setEditable(true);
				_dfFechaProxima.setFocus();
			}
			if (f.equals(_txtDescripcion)) {
				_txtDescripcion.setEditable();
				_txtDescripcion.setFocus();
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 262147, 5) {

		public void run() {
			_dfFecha.setEditable(true);
			_dfFechaProxima.setEditable(true);
			_txtDescripcion.setEditable();
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 262147, 3) {

		public void run() {
			fieldChangeNotify(Util.ADD_JUZGADO);
		}
	};

	private final MenuItem menuEliminarActuacion = new MenuItem(
			"Eliminar actuaci�n", 327682, 6) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR);
		}
	};
	
	private MenuItem menuCerrar = new MenuItem("Salir de Aplicaci�n",
			1000000000, 8) {

		public void run() {
			fieldChangeNotify(Util.CERRAR);
			if (!getScreen().isVisible()) {
				System.exit(0);
			}
		}
	};
	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public Calendar getFecha() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(_dfFecha.getDate()));
		return calendar;
	}

	public Calendar getFechaProxima() {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(_dfFechaProxima.getDate()));
		return calendar;
	}

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}