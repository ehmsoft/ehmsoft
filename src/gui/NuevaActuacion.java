package gui;

import java.util.Calendar;
import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import core.Juzgado;

public class NuevaActuacion extends FondoNuevos {

	/**
	 * 
	 */
	private ButtonField _btnJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private BasicEditField _txtDescripcion;

	private Juzgado _juzgado;

	public NuevaActuacion() {
		setTitle("Nueva actuación");

		HorizontalFieldManager fldJuzgado = new HorizontalFieldManager();
		VerticalFieldManager fldLeftJuzgado = new VerticalFieldManager();
		VerticalFieldManager fldRightJuzgado = new VerticalFieldManager(
				USE_ALL_WIDTH);

		_btnJuzgado = new ButtonField("Seleccionar", FIELD_RIGHT);
		_btnJuzgado.setChangeListener(listenerSeleccionar);
		fldLeftJuzgado.add(new LabelField("Juzgado: "));
		fldRightJuzgado.add(_btnJuzgado);
		fldJuzgado.add(fldLeftJuzgado);
		fldJuzgado.add(fldRightJuzgado);
		addElem(fldJuzgado);

		_dfFecha = new DateField("Fecha: ", System.currentTimeMillis(),
				DateField.DATE);
		addElem(_dfFecha);

		_dfFechaProxima = new DateField("Fecha próxima: ",
				System.currentTimeMillis(), DateField.DATE);
		addElem(_dfFechaProxima);

		_txtDescripcion = new BasicEditField();
		_txtDescripcion.setLabel("Descripción: ");
		addElem(_txtDescripcion);

		add(_vertical);
		addMenuItem(menuGuardar);
	}

	private FieldChangeListener listenerSeleccionar = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ListadoJuzgadosController juzgados = new ListadoJuzgadosController();
			UiApplication.getUiApplication().pushModalScreen(
					juzgados.getScreen());
			_btnJuzgado.setLabel(juzgados.getSelected().getNombre());
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			if (_txtDescripcion.getTextLength() == 0) {
				Object[] ask = { "Si", "No" };
				int sel = Dialog.ask(
						"¿Está seguro que no desea agregar descripción?", ask,
						1);
				if (sel == 0)
					UiApplication.getUiApplication().popScreen(getScreen());
			} else
				UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	public Juzgado getJuzgado() {
		return _juzgado;
	}

	public void setJuzgado(Juzgado juzgado) {
		_juzgado = juzgado;
	}

	public Calendar getFecha() {
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(_dfFecha.getDate());
		fecha.setTime(date);
		return fecha;
	}

	public Calendar getFechaProxima() {
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(_dfFechaProxima.getDate());
		fecha.setTime(date);
		return fecha;
	}

	public String getDescripcion() {
		String descripcion = null;
		try {
			descripcion = _txtDescripcion.getText();
		} catch (NullPointerException e) {
			return descripcion;
		}
		return descripcion;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().pushScreen(getScreen());
		return true;
	}
}
