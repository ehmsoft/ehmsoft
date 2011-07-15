package gui;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
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
	
	private boolean _guardar = false;
	private boolean _eliminar = false;

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
		addMenuItem(menuEditarTodo);
		addMenuItem(menuCambiar);
		addMenuItem(menuEliminar);
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
				} catch(NullPointerException e) {
					
				}				
			}
		}
	};
	
	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No"};
			int sel = Dialog.ask("¿Desea eliminar la actuación?", ask, 1);
			if(sel == 0) {
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