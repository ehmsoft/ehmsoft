package gui;

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
import net.rim.device.api.ui.container.VerticalFieldManager;

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
		HorizontalFieldManager topContainer = new HorizontalFieldManager();
		VerticalFieldManager citaContainer = new VerticalFieldManager(Field.USE_ALL_WIDTH);
		VerticalFieldManager titleContainer = new VerticalFieldManager();
		HorizontalFieldManager iconContainer = new HorizontalFieldManager(
				Field.FIELD_RIGHT);
		_cita = new BitmapField(null);
		_alarm = new BitmapField(null);

		iconContainer.add(_cita);
		iconContainer.add(_alarm);

		titleContainer.add(new LabelField("Actuacion"));
		citaContainer.add(iconContainer);

		topContainer.add(titleContainer);
		topContainer.add(citaContainer);
		setTitle(topContainer);

		_txtJuzgado = new EditableTextField("Juzgado: ", "");

		_dfFecha = new DateField("Fecha: ", 0, DateField.DATE_TIME);
		_dfFecha.setEditable(false);

		_dfFechaProxima = new DateField("Fecha próxima: ", 0, DateField.DATE_TIME);
		_dfFechaProxima.setEditable(false);

		_txtDescripcion = new EditableTextField("Descripción: ", "");

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
			menu.addSeparator();
		}
		if (_hasAlarm) {
			menu.add(menuVerCita);
			menu.add(menuEliminarCita);
			menu.addSeparator();
		} else {
			menu.add(menuAddCita);
			menu.addSeparator();
		}
		menu.add(menuEditar);
		menu.add(menuEditarTodo);
		menu.addSeparator();
		menu.add(menuEliminarActuacion);
		menu.add(menuGuardar);
	}

	private final MenuItem menuVerCita = new MenuItem("Ver cita", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.VER_CITA);
		}
	};

	private final MenuItem menuAddCita = new MenuItem("Agregar cita", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ADD_CITA);
		}
	};

	private final MenuItem menuEliminarCita = new MenuItem("Eliminar cita", 0,
			0) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR_CITA);
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

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

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 0, 0) {

		public void run() {
			_dfFecha.setEditable(true);
			_dfFechaProxima.setEditable(true);
			_txtDescripcion.setEditable();
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ADD_JUZGADO);
		}
	};

	private final MenuItem menuEliminarActuacion = new MenuItem(
			"Eliminar actuación", 0, 0) {

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