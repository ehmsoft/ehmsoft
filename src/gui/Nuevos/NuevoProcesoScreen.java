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
import net.rim.device.api.ui.component.TextField;

public class NuevoProcesoScreen extends FondoNormal {

	private BasicEditField _txtEstado;
	private NumericChoiceField _chPrioridad;
	private DateField _dtFecha;
	private EditableTextField _lblDemandante;
	private EditableTextField _lblDemandado;
	private EditableTextField _lblJuzgado;
	private BasicEditField _txtTipo;
	private BasicEditField _txtNotas;
	private BasicEditField _txtRadicado;
	private BasicEditField _txtRadicadoUnico;
	private EditableTextField _lblCategoria;

	private Vector _txtCampos;

	/**
	 * Crea un NuevoProcesoScreen con los elementos para la captura de los datos
	 * para el nuevo Proceso
	 */
	public NuevoProcesoScreen() {
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
		_txtRadicadoUnico.setLabel("Radicado �nico: ");
		add(_txtRadicadoUnico);

		_txtTipo = new BasicEditField(TextField.NO_NEWLINE);
		_txtTipo.setLabel("Tipo: ");
		add(_txtTipo);

		_txtEstado = new BasicEditField(TextField.NO_NEWLINE);
		_txtEstado.setLabel("Estado:");
		add(_txtEstado);

		_lblCategoria = new EditableTextField("Categor�a: ");
		add(_lblCategoria);

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

	protected boolean navigationClick(int status, int time) {
		Field f = getFieldWithFocus();
		if (f.equals(_lblDemandante)) {
			fieldChangeNotify(Util.ADD_DEMANDANTE);
			return true;
		} else if (f.equals(_lblDemandado)) {
			fieldChangeNotify(Util.ADD_DEMANDADO);
			return true;
		} else if (f.equals(_lblJuzgado)) {
			fieldChangeNotify(Util.ADD_JUZGADO);
			return true;
		} else if (f.equals(_lblCategoria)) {
			fieldChangeNotify(Util.ADD_CATEGORIA);
			return true;
		} else {
			return false;
		}
	}

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public void addCampo(Object cookie, String nombre, int lonMax) {
		addCampo(cookie, nombre, "", lonMax);
	}

	public void addCampo(Object cookie, String nombre, String valor, int lonMax) {
		BasicEditField txt = new BasicEditField();
		txt.setLabel(nombre + ": ");
		txt.setText(valor);
		txt.setCookie(cookie);
		if(lonMax != 0) {
			txt.setMaxSize(lonMax);
		}
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

	public void setCategoria(String text) {
		_lblCategoria.setText(text);
	}

	protected void makeMenu(Menu menu, int instance) {
		Field f = getFieldWithFocus();
		menu.add(menuAddActuacion);
		menu.add(menuVerActuaciones);
		menu.addSeparator();
		if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
				|| f.equals(_lblJuzgado) || f.equals(_lblCategoria)) {
			menu.add(menuCambiar);
		} else {
			f = UiApplication.getUiApplication().getActiveScreen()
					.getLeafFieldWithFocus();
			if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
					|| f.equals(_lblJuzgado)) {
				menu.add(menuCambiar);
			} else if (BasicEditField.class.isInstance(f)) {
				if (f.getCookie() != null) {
					menuModificarCampo.setText("Modificar " + f.getCookie().toString());
					menu.add(menuModificarCampo);
					menuEliminarCampo.setText("Eliminar " + f.getCookie().toString());
					menu.add(menuEliminarCampo);
				}
			}
		}
		menu.add(menuCampo);
		menu.add(menuGuardar);
		menu.add(menuCerrar);
	}

	private final MenuItem menuAgregar = new MenuItem("Agregar", 131075, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.ADD_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.ADD_DEMANDADO);
			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.ADD_JUZGADO);
			} else  if(f.equals(_lblCategoria)) {
				fieldChangeNotify(Util.ADD_CATEGORIA);
			}
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 131075, 0) {

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

	private final MenuItem menuVerActuaciones = new MenuItem("Ver actuaciones",
			327682, 4) {

		public void run() {
			fieldChangeNotify(Util.VER_LISTADO_ACTUACIONES);
		}
	};


	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 1) {

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

	private final MenuItem menuModificarCampo = new MenuItem("Modificar campo",
			393216, 8) {

		public void run() {
			fieldChangeNotify(Util.VER_CAMPO);
		}
	};

	private final MenuItem menuEliminarCampo = new MenuItem("Eliminar", 393216,
			7) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR_CAMPO);
		}
	};

	private MenuItem menuCerrar = new MenuItem("Salir de Aplicaci�n",
			1000000000, 9) {

		public void run() {
			fieldChangeNotify(Util.CERRAR);

			UiApplication.getUiApplication().invokeLater(new Runnable() {
				
				public void run() {
					while (getScreen().isVisible());
					System.exit(0);
				}
			});
		}
	};

	public Object getCookieOfFocused() {
		return getLeafFieldWithFocus().getCookie();
	}

	public void modificarCampo(Object cookie, String text, int lonMax) {
		Field focus = getLeafFieldWithFocus();
		focus.setCookie(cookie);
		((BasicEditField) focus).setLabel(text + ": ");
		if(lonMax != 0) {
			((BasicEditField) focus).setMaxSize(lonMax);
		}
	}

	public void eliminarCampo() {
		Field focus = getLeafFieldWithFocus();
		delete(focus);
		_txtCampos.removeElement(focus);
	}

	public void setDemandante(String text) {
		_lblDemandante.setText(text);
	}

	public void setDemandado(String text) {
		_lblDemandado.setText(text);
	}

	public void setJuzgado(String text) {
		_lblJuzgado.setText(text);
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
