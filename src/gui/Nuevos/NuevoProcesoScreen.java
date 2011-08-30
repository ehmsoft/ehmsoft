package gui.Nuevos;

import gui.FondoNormal;
import gui.Util;

import java.util.Calendar;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class NuevoProcesoScreen extends FondoNormal {

	private BasicEditField _txtEstado;
	private ObjectChoiceField _chCategoria;
	private ObjectChoiceField _chActuaciones;
	private NumericChoiceField _chPrioridad;
	private DateField _dtFecha;
	private LabelField _lblDemandante;
	private LabelField _lblDemandado;
	private LabelField _lblJuzgado;
	private BasicEditField _txtTipo;
	private BasicEditField _txtNotas;
	private BasicEditField _txtRadicado;
	private BasicEditField _txtRadicadoUnico;

	private Vector _txtCampos;

	/**
	 * Crea un NuevoProcesoScreen con los elementos para la captura de los datos
	 * para el nuevo Proceso
	 */
	public NuevoProcesoScreen() {
		setTitle("Nuevo Proceso");
		_txtCampos = new Vector();

		HorizontalFieldManager fldDemandante = new HorizontalFieldManager();
		_lblDemandante = new LabelField("*Ninguno*", Field.FOCUSABLE);
		fldDemandante.add(new LabelField("Demandante: "));
		fldDemandante.add(_lblDemandante);
		add(fldDemandante);

		HorizontalFieldManager fldDemandado = new HorizontalFieldManager();
		_lblDemandado = new LabelField("*Ninguno*", Field.FOCUSABLE);
		fldDemandado.add(new LabelField("Demandado: "));
		fldDemandado.add(_lblDemandado);
		add(fldDemandado);

		HorizontalFieldManager fldJuzgado = new HorizontalFieldManager();
		_lblJuzgado = new LabelField("*Ninguno*", Field.FOCUSABLE);
		fldJuzgado.add(new LabelField("Juzgado: "));
		fldJuzgado.add(_lblJuzgado);
		add(fldJuzgado);

		_txtRadicado = new BasicEditField(TextField.NO_NEWLINE);
		_txtRadicado.setLabel("Radicado: ");
		add(_txtRadicado);

		_txtRadicadoUnico = new BasicEditField(TextField.NO_NEWLINE);
		_txtRadicadoUnico.setLabel("Radicado unico: ");
		add(_txtRadicadoUnico);

		_txtTipo = new BasicEditField(TextField.NO_NEWLINE);
		_txtTipo.setLabel("Tipo: ");
		add(_txtTipo);

		_txtEstado = new BasicEditField(TextField.NO_NEWLINE);
		_txtEstado.setLabel("Estado:");
		add(_txtEstado);

		_chCategoria = new ObjectChoiceField();
		_chCategoria.setLabel("Categoria:");

		add(_chCategoria);

		_chActuaciones = new ObjectChoiceField();
		_chActuaciones.setLabel("Actuaciones:");
		add(_chActuaciones);

		_chPrioridad = new NumericChoiceField("Prioridad", 1, 10, 1);
		_chPrioridad.setSelectedIndex(4);
		add(_chPrioridad);

		_dtFecha = new DateField("Fecha de creación: ",
				System.currentTimeMillis(), DateField.DATE_TIME);
		_dtFecha.setEditable(true);
		add(_dtFecha);

		_txtNotas = new BasicEditField();
		_txtNotas.setLabel("Notas: ");
		add(_txtNotas);
	}

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public void addCampo(Object cookie, String nombre) {
		BasicEditField txt = new BasicEditField();
		txt.setLabel(nombre + ": ");
		txt.setCookie(cookie);
		_txtCampos.addElement(txt);
		add(txt);
	}

	public void eliminarCampo(int index) {
		_txtCampos.removeElementAt(index);
	}

	public Object[] getCampo(int index) {
		Object[] ret = new Object[2];
		ret[0] = ((BasicEditField) _txtCampos.elementAt(index)).getCookie();
		ret[1] = ((BasicEditField) _txtCampos.elementAt(index)).getText();
		return ret;
	}

	public void addCategorias(Object[] categorias) {
		_chCategoria.setChoices(categorias);
	}

	public void addActuaciones(Object[] actuaciones) {
		_chActuaciones.setChoices(actuaciones);
	}

	public void selectCategoria(int index) {
		_chCategoria.setSelectedIndex(index);
	}

	public void selectActuacion(int index) {
		_chActuaciones.setSelectedIndex(index);
	}

	protected void makeMenu(Menu menu, int instance) {
		Field f = UiApplication.getUiApplication().getActiveScreen()
				.getLeafFieldWithFocus();
		if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
				|| f.equals(_lblJuzgado)) {
			menu.add(menuCambiar);
			menu.addSeparator();
		} else if (f.equals(_chCategoria)) {
			menu.add(menuAddCategoria);
			menu.add(menuVerListadoCategorias);
			menu.addSeparator();
		} else if (f.equals(_chActuaciones)) {
			menu.add(menuAddActuacion);
			menu.addSeparator();
		} else if (BasicEditField.class.isInstance(f)) {
			if (f.getCookie() != null) {
				menu.add(menuEliminarCampo);
				menu.addSeparator();
			}
		}
		menu.add(menuCampo);
		menu.addSeparator();
		menu.add(menuGuardar);
	}

	private final MenuItem menuAgregar = new MenuItem("Agregar", 0, 0) {

		public void run() {
			Field f = UiApplication.getUiApplication().getActiveScreen()
					.getLeafFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.ADD_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.ADD_DEMANDADO);

			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.ADD_JUZGADO);
			}
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 0, 0) {

		public void run() {
			menuAgregar.run();
		}
	};

	private final MenuItem menuAddActuacion = new MenuItem("Nueva actuación",
			0, 0) {

		public void run() {
			fieldChangeNotify(Util.NEW_ACTUACION);
		}
	};

	private final MenuItem menuAddCategoria = new MenuItem("Nueva categoría",
			0, 0) {

		public void run() {
			fieldChangeNotify(Util.NEW_CATEGORIA);
		}
	};

	private final MenuItem menuVerListadoCategorias = new MenuItem(
			"Ver listado", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ADD_CATEGORIA);
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuCampo = new MenuItem(
			"Agregar campo personalizado", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ADD_CAMPO);
		}
	};

	private final MenuItem menuEliminarCampo = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR_CAMPO);
		}
	};

	public void setDemandante(String text) {
		_lblDemandante.setText(text);
	}

	public void setDemandado(String text) {
		_lblDemandado.setText(text);
	}

	public void setJuzgado(String text) {
		_lblJuzgado.setText(text);
	}

	/**
	 * @return La cadena con el radicado ingresado en la pantalla
	 */
	public String getRadicado() {
		return _txtRadicado.getText();
	}

	/**
	 * @return La cadena con el radicado unico ingresado en la pantalla
	 */
	public String getRadicadoUnico() {
		return _txtRadicadoUnico.getText();
	}

	/**
	 * @return La cadena con el estado ingresado en la pantalla
	 */
	public String getEstado() {
		return _txtEstado.getText();
	}

	/**
	 * @return La cadena con la categoria ingresada en la pantalla
	 */
	public Object getCategoria() {
		return _chCategoria.getChoice(_chCategoria.getSelectedIndex());
	}

	/**
	 * @return El numero que representa la prioridad ingresada en la pantalla
	 */
	public short getPrioridad() {
		return Short.parseShort((String) _chPrioridad.getChoice(_chPrioridad
				.getSelectedIndex()));
	}

	/**
	 * @return La cadena con las notas ingresadas en la pantalla
	 */
	public String getNotas() {
		return _txtNotas.getText();
	}

	/**
	 * @return La cadena con el tipo ingresado en la pantalla
	 */
	public String getTipo() {
		return _txtTipo.getText();
	}

	/**
	 * @return El Calendar con la fecha ingresada en la pantalla
	 */
	public Calendar getFecha() {
		Calendar fecha = Calendar.getInstance();

		fecha.setTime(fecha.getTime());
		return fecha;
	}

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}