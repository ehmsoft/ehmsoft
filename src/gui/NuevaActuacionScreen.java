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

public class NuevaActuacionScreen extends FondoNuevos {

	private ButtonField _btnJuzgado;
	private DateField _dfFecha;
	private DateField _dfFechaProxima;
	private BasicEditField _txtDescripcion;

	private Juzgado _juzgado;

	/**
	 * Crea una NuevaActuacionScreen inicializando los componentes
	 * y agregandolos a la pantalla
	 */
	public NuevaActuacionScreen() {
		setTitle("Nueva actuaci�n");

		HorizontalFieldManager fldJuzgado = new HorizontalFieldManager();
		VerticalFieldManager fldLeftJuzgado = new VerticalFieldManager();
		VerticalFieldManager fldRightJuzgado = new VerticalFieldManager(
				USE_ALL_WIDTH);

		_btnJuzgado = new ButtonField("Seleccionar", FIELD_RIGHT);
		_btnJuzgado.setChangeListener(listenerJuzgado);
		fldLeftJuzgado.add(new LabelField("Juzgado: "));
		fldRightJuzgado.add(_btnJuzgado);
		fldJuzgado.add(fldLeftJuzgado);
		fldJuzgado.add(fldRightJuzgado);
		addElem(fldJuzgado);

		_dfFecha = new DateField("Fecha: ", System.currentTimeMillis(),
				DateField.DATE_TIME);
		addElem(_dfFecha);

		_dfFechaProxima = new DateField("Fecha pr�xima: ",
				System.currentTimeMillis(), DateField.DATE_TIME);
		addElem(_dfFechaProxima);

		_txtDescripcion = new BasicEditField();
		_txtDescripcion.setLabel("Descripci�n: ");
		addElem(_txtDescripcion);

		add(_vertical);
		addMenuItem(menuGuardar);
	}

	private FieldChangeListener listenerJuzgado = new FieldChangeListener() {
		public void fieldChanged(Field field, int context) {
			ListadoJuzgados juzgados = new ListadoJuzgados();
			UiApplication.getUiApplication().pushModalScreen(
					juzgados.getScreen());
			setJuzgado(juzgados.getSelected());
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			if (_txtDescripcion.getTextLength() == 0) {
				Object[] ask = { "Si", "No" };
				int sel = Dialog.ask(
						"�Desea agregar una descripci�n?", ask,
						1);
				if (sel == 1) {
					if(_juzgado != null)
						UiApplication.getUiApplication().popScreen(getScreen());
					else
						Dialog.alert("Debe seleccionar un juzgado");
				}
			}
			else if(_juzgado == null) {
				Dialog.alert("Debe seleccionar un juzgado");
			}
			else
				UiApplication.getUiApplication().popScreen(getScreen());
		}
	};

	/**
	 * @return el Juzgado asociado al objeto, en caso de no existir
	 * se retorna null
	 */
	public Juzgado getJuzgado() {
		return _juzgado;
	}

	/**
	 * @param juzgado Se asigna al Objeto un Juzgado
	 */
	public void setJuzgado(Juzgado juzgado) {
		_btnJuzgado.setLabel(juzgado.getNombre());
		_juzgado = juzgado;
	}

	/**
	 * @return La fecha ingresada en la pantalla, en caso de no exixtir
	 * se retorna null
	 */
	public Calendar getFecha() {
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(_dfFecha.getDate());
		fecha.setTime(date);
		return fecha;
	}

	/**
	 * @return La fecha proxima ingresada en la pantalla, en caso de no existir
	 * se retorna null
	 */
	public Calendar getFechaProxima() {
		Calendar fecha = Calendar.getInstance();
		Date date = new Date(_dfFechaProxima.getDate());
		fecha.setTime(date);
		return fecha;
	}

	/**
	 * @return La descripcion ingresada en la pantalla, en caso de no existir
	 * se retorna null
	 */
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