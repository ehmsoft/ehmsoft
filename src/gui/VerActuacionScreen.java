package gui;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.container.MainScreen;
import core.Actuacion;
import core.Juzgado;

public class VerActuacionScreen extends MainScreen {

	private EditableTextField _txtJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private EditableTextField _txtDescripcion;
	private Actuacion _actuacion;
	private Juzgado _juzgado;

	public VerActuacionScreen(Actuacion actuacion) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

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

		_txtDescripcion = new EditableTextField("Descripció: ",
				_actuacion.getDescripcion());

		add(_txtJuzgado);
		add(_dfFecha);
		add(_dfFechaProxima);
		add(_txtDescripcion);
		addMenuItem(menuGuardar);
		addMenuItem(menuEditar);
		addMenuItem(menuCambiar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	private final MenuItem menuEditar = new MenuItem("Editar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtJuzgado)) {
				VerJuzgado verJuzgado = new VerJuzgado(
						_juzgado);
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

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_txtJuzgado)) {
				ListadoJuzgados juzgados = new ListadoJuzgados();
				UiApplication.getUiApplication().pushModalScreen(
						juzgados.getScreen());
				_juzgado = juzgados.getSelected();
				_txtJuzgado.setText(_juzgado.getNombre());
				_txtJuzgado.setFocus();
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
}