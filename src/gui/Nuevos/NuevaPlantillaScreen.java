package gui.Nuevos;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.TextField;

public class NuevaPlantillaScreen extends FondoNormal {

	private BasicEditField _txtNombre;
	private EditableTextField _lblDemandante;
	private EditableTextField _lblDemandado;
	private EditableTextField _lblJuzgado;
	private BasicEditField _txtRadicado;
	private BasicEditField _txtRadicadoUnico;
	private BasicEditField _txtEstado;
	private ObjectChoiceField _chCategoria;
	private BasicEditField _txtTipo;
	private BasicEditField _txtNotas;
	private NumericChoiceField _chPrioridad;

	private Vector _txtCampos;
	private Field _focused;

	/**
	 * Crea un NuevoProcesoScreen con los elementos para la captura de los datos
	 * para el nuevo Proceso
	 */
	public NuevaPlantillaScreen() {
		setTitle("Nueva Plantilla");
		_txtCampos = new Vector();

		_txtNombre = new BasicEditField(TextField.NO_NEWLINE);
		_txtNombre.setLabel("Nombre: ");
		add(_txtNombre);

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

		_chPrioridad = new NumericChoiceField("Prioridad", 1, 10, 1);
		_chPrioridad.setSelectedIndex(4);
		add(_chPrioridad);

		_txtNotas = new BasicEditField();
		_txtNotas.setLabel("Notas: ");
		add(_txtNotas);
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

	public void eliminarCampo() {
		_txtCampos.removeElement(_focused);
		delete(_focused);
	}

	public void modificarCampo(Object cookie, String text) {
		_focused.setCookie(cookie);
		((BasicEditField) _focused).setLabel(text + ": ");
	}

	public Object getFocused() {
		return _focused.getCookie();
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

	public void selectCategoria(int index) {
		_chCategoria.setSelectedIndex(index);
	}

	protected void makeMenu(Menu menu, int instance) {
		Field f = getFieldWithFocus();
		if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
				|| f.equals(_lblJuzgado)) {
			menu.add(menuCambiar);
			menu.add(menuEliminar);
			menu.addSeparator();
		} else if (f.equals(_chCategoria)) {
			menu.add(menuAddCategoria);
			menu.add(menuVerListadoCategorias);
			menu.addSeparator();
		} else if (BasicEditField.class.isInstance(f)) {
			if (f.getCookie() != null) {
				_focused = f;
				menu.add(menuModificarCampo);
				menu.add(menuEliminarCampo);
				menu.addSeparator();
			}
		}
		menu.add(menuCampo);
		menu.addSeparator();
		menu.add(menuGuardar);
	}

	private final MenuItem menuModificarCampo = new MenuItem("Modificar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.VER_CAMPO);
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 0, 0) {

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

	private final MenuItem menuEliminar = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.ELIMINAR_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.ELIMINAR_DEMANDADO);
			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.ELIMINAR_JUZGADO);
			}
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

	public String getNombre() {
		return _txtNombre.getText();
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

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}