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
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import core.Actuacion;
import core.CalendarManager;
import core.Juzgado;

public class VerActuacionScreen extends FondoNormal {

	private EditableTextField _txtJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private EditableTextField _txtDescripcion;
	private Actuacion _actuacion;
	private Juzgado _juzgado;

	private boolean _guardar = false;
	private boolean _eliminar = false;
	private String _uid;
	private BitmapField _cita;
	private BitmapField _alarm;
	private Bitmap _bell = Bitmap.getBitmapResource("bell.png");
	private Bitmap _clock = Bitmap.getBitmapResource("clock.png");

	public VerActuacionScreen(Actuacion actuacion) {
		HorizontalFieldManager h = new HorizontalFieldManager();
		VerticalFieldManager r = new VerticalFieldManager(Field.USE_ALL_WIDTH);
		VerticalFieldManager l = new VerticalFieldManager();
		HorizontalFieldManager r1 = new HorizontalFieldManager(
				Field.FIELD_RIGHT);
		_cita = new BitmapField(null);
		_alarm = new BitmapField(null);

		r1.add(_cita);
		r1.add(_alarm);

		l.add(new LabelField("Actuacion"));
		r.add(r1);

		h.add(l);
		h.add(r);
		setTitle(h);

		_actuacion = actuacion;
		_uid = _actuacion.getUid();
		_juzgado = actuacion.getJuzgado();

		_txtJuzgado = new EditableTextField("Juzgado: ", _actuacion
				.getJuzgado().getNombre());

		_dfFecha = new DateField("Fecha: ", _actuacion.getFecha().getTime()
				.getTime(), DateField.DATE_TIME);
		_dfFecha.setEditable(false);

		_dfFechaProxima = new DateField("Fecha próxima: ", _actuacion
				.getFechaProxima().getTime().getTime(), DateField.DATE_TIME);
		_dfFechaProxima.setEditable(false);

		_txtDescripcion = new EditableTextField("Descripción: ",
				_actuacion.getDescripcion());

		add(_txtJuzgado);
		add(_dfFecha);
		add(_dfFechaProxima);
		add(_txtDescripcion);
	}

	public void setCita(boolean cita, boolean alarm) {
		if (cita) {
			_cita.setBitmap(_clock);
		} else {
			_cita.setBitmap(null);
		}
		if (alarm) {
			_alarm.setBitmap(_bell);
		} else {
			_alarm.setBitmap(null);
		}
	}

	protected void makeMenu(Menu menu, int instance) {
		Field focus = UiApplication.getUiApplication().getActiveScreen()
				.getFieldWithFocus();
		if (focus.equals(_txtJuzgado)) {
			menu.add(menuCambiar);
			menu.addSeparator();
		}
		if (_uid != null) {
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
			try {
				VerCita v = new VerCita(_uid);
				UiApplication.getUiApplication().pushModalScreen(v.getScreen());
				v.guardarCita();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
		}
	};

	private final MenuItem menuAddCita = new MenuItem("Agregar cita", 0, 0) {

		public void run() {
			NuevaCita n = new NuevaCita(getDescripcion(), getFechaProxima()
					.getTime());
			UiApplication.getUiApplication().pushModalScreen(n.getScreen());
			_uid = n.getUid();
			if (_uid != null) {
				setCita(true, false);
				if (n.hasAlarma()) {
					setCita(true, true);
				}
			}
		}
	};

	private final MenuItem menuEliminarCita = new MenuItem("Eliminar cita", 0,
			0) {

		public void run() {
			Object[] ask = { "Aceptar", "Cancelar" };
			int sel = Dialog.ask("¿Desea eliminar la cita del calendario?",
					ask, 1);
			if (sel == 0) {
				try {
					CalendarManager.borrarCita(_uid);
					_uid = null;
					setCita(false, false);
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			_guardar = true;
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtJuzgado)) {
				VerJuzgado v = new VerJuzgado(_juzgado);
				UiApplication.getUiApplication().pushModalScreen(v.getScreen());
				v.actualizarJuzgado();
				_juzgado = v.getJuzgado();
				_txtJuzgado.setText(_juzgado.getNombre());
				_txtJuzgado.setFocus();
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
			_txtJuzgado.setEditable();
			_dfFecha.setEditable(true);
			_dfFechaProxima.setEditable(true);
			_txtDescripcion.setEditable();
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtJuzgado)) {
				ListadoJuzgados juzgados = new ListadoJuzgados();
				UiApplication.getUiApplication().pushModalScreen(
						juzgados.getScreen());
				try {
					_juzgado = juzgados.getSelected();
					_txtJuzgado.setText(_juzgado.getNombre());
					_txtJuzgado.setFocus();
				} catch (NullPointerException e) {

				}
			}
		}
	};

	private final MenuItem menuEliminarActuacion = new MenuItem(
			"Eliminar actuación", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desea eliminar la actuación?", ask, 1);
			if (sel == 0) {
				if (_actuacion.getUid() != null) {
					sel = Dialog.ask("¿Desea eliminar la cita del calendario?",
							ask, 1);
					if (sel == 0) {
						try {
							CalendarManager.borrarCita(_uid);
							_uid = null;
						} catch (Exception e) {
							Dialog.alert(e.toString());
						}
					}
				}
				_eliminar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};

	public Juzgado getJuzgado() {
		return _juzgado;
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

	public String getUid() {
		return _uid;
	}

	public boolean isGuardado() {
		return _guardar;
	}

	public boolean isEliminado() {
		return _eliminar;
	}

	public boolean isCambiado() {
		boolean cambio = false;

		Calendar f1 = _actuacion.getFecha();
		Calendar f2 = getFecha();

		Calendar fP1 = _actuacion.getFechaProxima();
		Calendar fP2 = getFechaProxima();

		if (!_actuacion.getJuzgado().getId_juzgado()
				.equals(getJuzgado().getId_juzgado()))
			cambio = true;
		else if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
				|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
				|| (f1.get(Calendar.DAY_OF_MONTH) != f2
						.get(Calendar.DAY_OF_MONTH))
				|| (f1.get(Calendar.HOUR_OF_DAY) != f2
						.get(Calendar.HOUR_OF_DAY))
				|| (f1.get(Calendar.MINUTE) != f2.get(Calendar.MINUTE)))
			cambio = true;
		else if ((fP1.get(Calendar.YEAR) != fP2.get(Calendar.YEAR))
				|| (fP1.get(Calendar.MONTH) != fP2.get(Calendar.MONTH))
				|| (fP1.get(Calendar.DAY_OF_MONTH) != fP2
						.get(Calendar.DAY_OF_MONTH))
				|| (fP1.get(Calendar.HOUR_OF_DAY) != fP2
						.get(Calendar.HOUR_OF_DAY))
				|| (fP1.get(Calendar.MINUTE) != fP2.get(Calendar.MINUTE)))
			cambio = true;
		else if (!_actuacion.getDescripcion().equals(getDescripcion()))
			cambio = true;
		else if (_actuacion.getUid() != _uid) {
			cambio = true;
		}
		return cambio;
	}

	public boolean onClose() {
		if (!isCambiado()) {
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