package gui.Listados;

import gui.ListadosInterface;
import gui.Util;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Categoria;
import core.Preferencias;

public class ListadoCategorias {

	private Vector _vectorCategorias;
	private ListadosInterface _screen;
	private long _style;
	private Categoria _selected;

	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;

	public ListadoCategorias() {
		this(false, 0);
	}

	public ListadoCategorias(boolean popup) {
		this(popup, 0);
	}

	public ListadoCategorias(long style) {
		this(false, style);
	}

	public ListadoCategorias(boolean popup, long style) {
		_style = style;
		if (popup) {
			_screen = new ListadoCategoriasPopUp();
		} else {
			_screen = new ListadoCategoriasScreen();
		}

		try {
			_vectorCategorias = new Persistence().consultarCategorias();
		} catch (NullPointerException e) {
			Util.noSd();
			System.exit(0);
		} catch (Exception e) {
			Util.alert(e.toString());
		}

		addCategorias();
		((Screen) _screen).setChangeListener(listener);

		if (Preferencias.isMostrarCampoBusqueda()) {
			_screen.setSearchField();
		}
		if ((_style & NO_NUEVO) != NO_NUEVO) {
			_screen.addElement("Crear nueva categorķa", 0);
		}
		if ((_style & ON_CLICK_VER) != ON_CLICK_VER
				&& (_style & ON_CLICK_SELECT) != ON_CLICK_SELECT) {
			if (popup) {
				_style = _style | ON_CLICK_SELECT;
			} else {
				_style = _style | ON_CLICK_VER;
			}
		}
		if (Preferencias.isMostrarTitulosPantallas() && !popup) {
			_screen.setTitle("Categorķas");
		}
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.CLICK) {
				onClick();
			} else if (context == Util.VER_ELEMENTO) {
				verCategoria();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.ELIMINAR) {
				eliminarCategoria();
			} else if (context == Util.NEW) {
				nuevoCategoria();
			}
		}
	};

	private void addCategorias() {
		if (_vectorCategorias != null) {
			_screen.loadFrom(_vectorCategorias);
		}
	}

	public Categoria getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen) _screen;

	}

	public void onClick() {
		try {
			if (String.class.isInstance(_screen.getSelected())) {
				nuevoCategoria();
			} else {
				if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
					verCategoria();
				} else {
					_selected = (Categoria) _screen.getSelected();
					UiApplication.getUiApplication()
							.popScreen((Screen) _screen);
				}
			}
		} catch (Exception e) {
		}
	}

	private void nuevoCategoria() {
		Categoria categoria = Util.nuevaCategoria(true);
		if (categoria != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(categoria, 0);
			} else {
				_screen.addElement(categoria, 1);
			}
		}
	}

	private void verCategoria() {
		Categoria selected = (Categoria) _screen.getSelected();
		Categoria categoria = Util.verCategoria(selected);
		if (categoria != null) {
			_screen.replace(selected, categoria);
		} else {
			_screen.remove(selected);
		}
	}

	private void eliminarCategoria() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDCategoria(), 1);
		if (sel == 0) {
			Categoria selected = (Categoria) _screen.getSelected();
			try {
				new Persistence().borrarCategoria(selected);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			if(Preferencias.getUltimaCategoria() != null && Preferencias.getUltimaCategoria().getId_categoria().equals(selected.getId_categoria())) {
				Preferencias.setUltimaCategoria(null);
			}
			_screen.remove(selected);
		}
	}

	private void cerrarPantalla() {
		_selected = null;
		Util.popScreen((Screen) _screen);
	}

	public String toString() {
		return "Lista de categorķas";
	}
}
