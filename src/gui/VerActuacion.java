package gui;

import java.util.Calendar;
import java.util.Date;

import core.Actuacion;
import core.Juzgado;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.container.MainScreen;

public class VerActuacion extends MainScreen {

	private EditableTextField _txtJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private EditableTextField _txtDescripcion;
	private Actuacion _actuacion;
	private Juzgado _juzgado;

	public VerActuacion(Actuacion actuacion) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_actuacion = actuacion;
		_juzgado = _actuacion.getJuzgado();

		setTitle("Ver actuación");

		_txtJuzgado = new EditableTextField("Juzgado: ", _actuacion.getJuzgado().getNombre());

		_dfFecha = new DateField("Fecha: ", _actuacion.getFecha().getTime().getTime(), DateField.DATE);

		_dfFechaProxima = new DateField("Fecha próxima: ", _actuacion.getFechaProxima().getTime().getTime(), DateField.DATE);

		_txtDescripcion = new EditableTextField("Descripció: ", _actuacion.getDescripcion());

		add(_txtJuzgado);
		add(_dfFecha);
		add(_dfFechaProxima);
		add(_txtDescripcion);
		addMenuItem(menuGuardar);
	}

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			UiApplication.getUiApplication().popScreen(getScreen());
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