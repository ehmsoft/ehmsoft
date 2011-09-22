package gui.Nuevos;

import gui.EditableTextField;
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
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.TextField;

public class NuevoProcesoScreen extends FondoNormal {

	private BasicEditField _txtEstado;
	private ObjectChoiceField _chCategoria;
	private ObjectChoiceField _chActuaciones;
	private NumericChoiceField _chPrioridad;
	private DateField _dtFecha;
	private EditableTextField _lblDemandante;
	private EditableTextField _lblDemandado;
	private EditableTextField _lblJuzgado;
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

		_lblDemandante = new EditableTextField("Demandante: ");
		add(_lblDemandante);

		_lblDemandado = new EditableTextField("Demandado: ");
		add(_lblDemandado);

		_lblJuzgado = new EditableTextField("Juzgado: ");
		add(_lblJuzgado);

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

		_dtFecha = new DateField("Fecha de creaci�n: ",
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
		addCampo(cookie, nombre, "");
	}

	public void addCampo(Object cookie, String nombre, String valor) {
		BasicEditField txt = new BasicEditField();
		txt.setLabel(nombre + ": ");
		txt.setText(valor);
		txt.setCookie(cookie);
		_txtCampos.addElement(txt);
		add(txt);
	}

	public Object[][] getCampos() {
		Object[][] campos = new Object[2][_txtCampos.size()];
		for (int i = 0; i < _txtCampos.size(); i++) {
			BasicEditField temp = (BasicEditField) _txtCampos.elementAt(i);
			campos[0][i] = temp.getCookie();
			campos[1][i] = temp.getText();
		}
		return campos;
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
		Field f = getFieldWithFocus();
		if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
				|| f.equals(_lblJuzgado)) {
			menu.add(menuCambiar);
		} else {
			f = UiApplication.getUiApplication().getActiveScreen()
					.getLeafFieldWithFocus();
			if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
					|| f.equals(_lblJuzgado)) {
				menu.add(menuCambiar);
			} else if (f.equals(_chCategoria)) {
				menu.add(menuAddCategoria);
				menu.add(menuVerListadoCategorias);
			} else if (f.equals(_chActuaciones)) {
				menu.add(menuAddActuacion);
			} else if (BasicEditField.class.isInstance(f)) {
				if (f.getCookie() != null) {
					menu.add(menuEliminarCampo);
				}
			}
		}
		menu.add(menuCampo);
		menu.add(menuGuardar);
		menu.add(menuCerrar);
	}

	private final MenuItem menuAgregar = new MenuItem("Agregar", 131075, 1) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.ADD_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.ADD_DEMANDADO);
			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.ADD_JUZGADO);
			}
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 131075, 1) {

		public void run() {
			menuAgregar.run();
		}
	};

	private final MenuItem menuAddActuacion = new MenuItem("Nueva actuaci�n",
			327682, 5) {

		public void run() {
			fieldChangeNotify(Util.NEW_ACTUACION);
		}
	};

	private final MenuItem menuAddCategoria = new MenuItem("Nueva categor�a",
			262147, 4) {

		public void run() {
			fieldChangeNotify(Util.NEW_CATEGORIA);
		}
	};

	private final MenuItem menuVerListadoCategorias = new MenuItem(
			"Ver listado", 262147, 3) {

		public void run() {
			fieldChangeNotify(Util.ADD_CATEGORIA);
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 0) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	private final MenuItem menuCampo = new MenuItem(
			"Agregar campo personalizado", 393216, 7) {

		public void run() {
			fieldChangeNotify(Util.ADD_CAMPO);
		}
	};

	private final MenuItem menuEliminarCampo = new MenuItem("Eliminar", 393216, 7) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR_CAMPO);
		}
	};
	
	private MenuItem menuCerrar = new MenuItem("Salir de Aplicaci�n",
			1000000000, 9) {

		public void run() {
			fieldChangeNotify(Util.CERRAR);
			if (!getScreen().isVisible()) {
				System.exit(0);
			}
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

	public void setCategoria(Object categoria) {
		_chCategoria.setSelectedIndex(categoria);
	}

	public void setPrioridad(int prioridad) {
		_chPrioridad.setSelectedValue(prioridad);
	}

	public void setEstado(String text) {
		_txtEstado.setText(text);
	}

	public void setNotas(String text) {
		_txtNotas.setText(text);
	}

	public void setRadicado(String text) {
		_txtRadicado.setText(text);
	}

	public void setRadicadoUnico(String text) {
		_txtRadicadoUnico.setText(text);
	}

	public void setTipo(String text) {
		_txtTipo.setText(text);
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