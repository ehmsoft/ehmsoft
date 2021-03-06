package gui.Ver;

import gui.EditableTextField;
import gui.FondoNormal;
import gui.Util;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.NumericChoiceField;

public class VerPlantillaScreen extends FondoNormal {

	private EditableTextField _txtNombre;
	private EditableTextField _lblDemandante;
	private EditableTextField _lblDemandado;
	private EditableTextField _lblJuzgado;
	private EditableTextField _txtRadicado;
	private EditableTextField _txtRadicadoUnico;
	private EditableTextField _txtEstado;
	private EditableTextField _lblCategoria;
	private EditableTextField _txtTipo;
	private EditableTextField _txtNotas;
	private NumericChoiceField _nfPrioridad;

	private Vector _txtCampos;

	private Field _focused;

	public VerPlantillaScreen() {
		_txtCampos = new Vector();
		_txtNombre = new EditableTextField("Nombre: ");
		add(_txtNombre);

		_lblDemandante = new EditableTextField("Demandante: ");
		add(_lblDemandante);

		_lblDemandado = new EditableTextField("Demandado: ");
		add(_lblDemandado);

		_lblJuzgado = new EditableTextField("Juzgado: ");
		add(_lblJuzgado);

		_txtRadicado = new EditableTextField("Radicado: ");
		add(_txtRadicado);

		_txtRadicadoUnico = new EditableTextField("Radicado �nico: ");
		add(_txtRadicadoUnico);

		_txtEstado = new EditableTextField("Estado: ");
		add(_txtEstado);

		_lblCategoria = new EditableTextField("Categor�a: ");
		add(_lblCategoria);

		_txtTipo = new EditableTextField("Tipo: ");
		add(_txtTipo);

		_txtNotas = new EditableTextField("Notas: ");
		add(_txtNotas);

		_nfPrioridad = new NumericChoiceField("Prioridad: ", 0, 10, 1);
		_nfPrioridad.setEditable(false);
		add(_nfPrioridad);
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

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public void addCampo(Object cookie, String nombre, String valor, int lonMax) {
		nombre = nombre + ": ";
		EditableTextField txt = new EditableTextField(nombre);
		txt.setText(valor);
		txt.setCookie(cookie);
		if(lonMax != 0) {
			txt.setMaxSize(lonMax);
		}
		_txtCampos.addElement(txt);
		add(txt);
	}

	public void eliminarCampo() {
		delete(_focused);
		_txtCampos.removeElement(_focused);
	}

	public void modificarCampo(Object cookie, String text, int lonMax) {
		_focused.setCookie(cookie);
		if(lonMax != 0) {
			((EditableTextField) _focused).setMaxSize(lonMax);
		}
		((EditableTextField) _focused).setLabel(text + ": ");
	}

	public Object[][] getCampos() {
		Object[][] campos = new Object[2][_txtCampos.size()];
		for (int i = 0; i < _txtCampos.size(); i++) {
			EditableTextField temp = (EditableTextField) _txtCampos
					.elementAt(i);
			campos[0][i] = temp.getCookie();
			campos[1][i] = temp.getText();
		}
		return campos;
	}

	public void setNombre(String text) {
		_txtNombre.setText(text);
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

	public void setRadicado(String text) {
		_txtRadicado.setText(text);
	}

	public void setRadicadoUnico(String text) {
		_txtRadicadoUnico.setText(text);
	}

	public void setEstado(String text) {
		_txtEstado.setText(text);
	}

	public void setCategoria(String text) {
		_lblCategoria.setText(text);
	}

	public void setTipo(String text) {
		_txtTipo.setText(text);
	}

	public void setNotas(String text) {
		_txtNotas.setText(text);
	}

	public void setPrioridad(int element) {
		_nfPrioridad.setSelectedIndex(String.valueOf(element));
	}

	public String getNombre() {
		return _txtNombre.getText();
	}

	public String getEstado() {
		return _txtEstado.getText();
	}

	public Object getCookieOfFocused() {
		return _focused.getCookie();
	}

	public String getNotas() {
		return _txtNotas.getText();
	}

	public int getPrioridad() {
		return _nfPrioridad.getSelectedValue();
	}

	public String getRadicado() {
		return _txtRadicado.getText();
	}

	public String getRadicadoUnico() {
		return _txtRadicadoUnico.getText();
	}

	public String getTipo() {
		return _txtTipo.getText();
	}

	protected void makeMenu(Menu menu, int instance) {
		Field f = getFieldWithFocus();

		menu.add(menuAddCampo);
		menu.add(menuCerrar);
		if (f.equals(_lblCategoria)) {
			menu.add(menuCambiar);
		} else if (f.equals(_lblDemandante) || f.equals(_lblDemandado)
				|| f.equals(_lblJuzgado)) {
			menu.add(menuCambiar);
			menu.add(menuEliminar);
		}

		menu.add(menuEditar);
		menu.add(menuEditarTodo);

		if (EditableTextField.class.isInstance(f)) {
			if (f.getCookie() != null) {
				_focused = f;
				menuModificarCampo.setText("Modificar " + f.getCookie().toString());
				menu.add(menuModificarCampo);
				menuEliminarCampo.setText("Eliminar " + f.getCookie().toString());
				menu.add(menuEliminarCampo);
			}
		}
		menu.add(menuGuardar);
		menu.add(menuNuevo);
	}

	private final MenuItem menuModificarCampo = new MenuItem("Modificar",
			393216, 8) {

		public void run() {
			fieldChangeNotify(Util.VER_CAMPO);
		}
	};

	private final MenuItem menuAddCampo = new MenuItem(
			"Agregar campo personalizado", 393216, 7) {

		public void run() {
			fieldChangeNotify(Util.ADD_CAMPO);
		}
	};

	private final MenuItem menuEliminar = new MenuItem(
			"Eliminar de la plantilla", 131075, 2) {

		public void run() {
			EditableTextField f = (EditableTextField) getFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.ELIMINAR_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.ELIMINAR_DEMANDADO);
			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.ELIMINAR_JUZGADO);
			}
		}
	};

	private final MenuItem menuEliminarCampo = new MenuItem(
			"Eliminar del proceso", 393216, 7) {
		public void run() {
			fieldChangeNotify(Util.ELIMINAR_CAMPO);
		}
	};

	private final MenuItem menuGuardar = new MenuItem("Guardar", 65537, 1) {

		public void run() {
			fieldChangeNotify(Util.GUARDAR);
		}
	};
	private final MenuItem menuNuevo = new MenuItem(
			"Crear proceso a partir de plantilla", 65537, 1) {

		public void run() {
			fieldChangeNotify(Util.NEW_PROCESO);
		}
	};
	private final MenuItem menuEditar = new MenuItem("Editar", 262147, 3) {

		public void run() {
			Field f = getFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.VER_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.VER_DEMANDADO);
			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.VER_JUZGADO);
			} else if (f.equals(_lblCategoria)) {
				fieldChangeNotify(Util.VER_CATEGORIA);
			} else {
				f.setEditable(true);
			}
		}
	};

	private final MenuItem menuEditarTodo = new MenuItem("Editar todo", 262147,
			4) {

		public void run() {
			_txtNombre.setEditable();
			_txtRadicado.setEditable();
			_txtRadicadoUnico.setEditable();
			_txtEstado.setEditable();
			_txtTipo.setEditable();
			_txtNotas.setEditable();
			_nfPrioridad.setEditable(true);
			Enumeration e = _txtCampos.elements();
			while (e.hasMoreElements()) {
				((EditableTextField) e.nextElement()).setEditable();
			}
		}
	};

	private final MenuItem menuCambiar = new MenuItem("Cambiar", 131075, 0) {

		public void run() {
			EditableTextField f = (EditableTextField) getFieldWithFocus();
			if (f.equals(_lblDemandante)) {
				fieldChangeNotify(Util.ADD_DEMANDANTE);
			} else if (f.equals(_lblDemandado)) {
				fieldChangeNotify(Util.ADD_DEMANDADO);
			} else if (f.equals(_lblJuzgado)) {
				fieldChangeNotify(Util.ADD_JUZGADO);
			} else if (f.equals(_lblCategoria)) {
				fieldChangeNotify(Util.ADD_CATEGORIA);
			}
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

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}