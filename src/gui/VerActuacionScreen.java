package gui;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import core.Actuacion;
import core.Juzgado;

public class VerActuacionScreen extends FondoNormal {

	private EditableTextField _txtJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private EditableTextField _txtDescripcion;
	private Actuacion _actuacion;
	private Juzgado _juzgado;
	
	private EditableTextField _txtDescripcionCita;
	private DateField _dfFechaCita;
	private EditableTextField _txtTiempoCita;
	private ObjectChoiceField _chUnidades;
	

	private boolean _guardar = false;
	private boolean _eliminar = false;

	public VerActuacionScreen(Actuacion actuacion) {

		_actuacion = actuacion;
		_juzgado = actuacion.getJuzgado();

		setTitle("Ver actuación");

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
	
	public void setCita(String descripcion, Date date, int alarma) {
		add(new SeparatorField());
		LabelField title = new LabelField("Cita en calendario", FIELD_HCENTER);
		add(title);
		add(new SeparatorField());
		String unidades = null;
		
		if(alarma < 3600) {
			unidades = "Minutos";
			alarma = alarma / 60;
		}
		else if(alarma < 86400) {
			unidades = "Horas";
			alarma = alarma / 3600;
		}
		else if(alarma >= 86400) {
			unidades = "Días";
			alarma = alarma / 86400;
		}
		
		String[] choices = {"Minutos", "Horas", "Días"};
		
		_txtDescripcionCita = new EditableTextField("Descripción: ", descripcion);
		add(_txtDescripcionCita);
		_dfFechaCita = new DateField("Fecha: ", date.getTime(), DateField.DATE_TIME);
		add(_dfFechaCita);
		_txtTiempoCita = new EditableTextField("Anticipación: ", alarma+"", BasicEditField.FILTER_INTEGER);
		add(_txtTiempoCita);
		_chUnidades = new ObjectChoiceField(null, choices);
		_chUnidades.setSelectedIndex(unidades);
		add(_chUnidades);
	}
	
	protected void makeMenu(Menu menu, int instance) {
		Field focus = UiApplication.getUiApplication().getActiveScreen()
		.getFieldWithFocus();
		if(focus.equals(_txtJuzgado)) {
			menu.add(menuCambiar);
			menu.addSeparator();
		}
		menu.add(menuEditar);
		menu.add(menuEditarTodo);
		menu.addSeparator();
		menu.add(menuEliminar);
		menu.add(menuGuardar);
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
			if (f.equals(_txtJuzgado)) {
				VerJuzgado verJuzgado = new VerJuzgado(_juzgado);
				UiApplication.getUiApplication().pushModalScreen(
						verJuzgado.getScreen());
				_juzgado = verJuzgado.getJuzgado();
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

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desea eliminar la actuación?", ask, 1);
			if (sel == 0) {
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

	public Actuacion getActuacion() {
		return _actuacion;
	}

	public boolean isGuardado() {
		return _guardar;
	}

	public boolean isEliminado() {
		return _eliminar;
	}

	public boolean onClose() {
		boolean cambio = false;
		Calendar f1 = _actuacion.getFecha();
		Calendar f2 = this.getFecha();

		Calendar fP1 = _actuacion.getFechaProxima();
		Calendar fP2 = this.getFechaProxima();

		if (!_actuacion.getJuzgado().getId_juzgado()
				.equals(this.getJuzgado().getId_juzgado()))
			cambio = true;
		if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
				|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
				|| (f1.get(Calendar.DAY_OF_MONTH) != f2
						.get(Calendar.DAY_OF_MONTH)))
			cambio = true;
		if ((fP1.get(Calendar.YEAR) != fP2.get(Calendar.YEAR))
				|| (fP1.get(Calendar.MONTH) != fP2.get(Calendar.MONTH))
				|| (fP1.get(Calendar.DAY_OF_MONTH) != fP2
						.get(Calendar.DAY_OF_MONTH)))
			cambio = true;
		if (!_actuacion.getDescripcion().equals(this.getDescripcion()))
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