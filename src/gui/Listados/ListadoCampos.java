package gui.Listados;

import gui.ListadosInterface;
import gui.Util;
import gui.Nuevos.NuevoCampo;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.CampoPersonalizado;
import core.Preferencias;

public class ListadoCampos {

	private Vector _vectorCampos;
	private ListadosInterface _screen;
	private long _style;
	private CampoPersonalizado _selected;

	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;

	public ListadoCampos() {
		this(false, 0);
	}

	public ListadoCampos(boolean popup) {
		this(popup, 0);
	}

	public ListadoCampos(long style) {
		this(false, style);
	}

	public ListadoCampos(boolean popup, long style) {
		_style = style;
		if (popup) {
			_screen = new ListadoCamposPopUp();
		} else {
			_screen = new ListadoCamposScreen();
		}

		try {
			_vectorCampos = new Persistence().consultarAtributos();
		} catch (NullPointerException e) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert(e.toString());
		}

		addCampos();
		((Screen) _screen).setChangeListener(listener);

		if (Preferencias.isMostrarCampoBusqueda()) {
			_screen.setSearchField();
		}
		if ((_style & NO_NUEVO) != NO_NUEVO) {
			_screen.addElement("Crear nuevo campo", 0);
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
			_screen.setTitle("Campos personalizados");
		}
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.CLICK) {
				onClick();
			} else if (context == Util.VER_ELEMENTO) {
				verCampo();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.ELIMINAR) {
				eliminarCampo();
			} else if (context == Util.NEW) {
				nuevoCampo();
			}
		}
	};

	private void addCampos() {
		if (_vectorCampos != null) {
			_screen.loadFrom(_vectorCampos);
		}
	}

	public CampoPersonalizado getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen) _screen;

	}

	public void onClick() {
		try {
			if (String.class.isInstance(_screen.getSelected())) {
				nuevoCampo();
			} else {
				if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
					verCampo();
				} else {
					_selected = (CampoPersonalizado) _screen.getSelected();
					UiApplication.getUiApplication()
							.popScreen((Screen) _screen);
				}
			}
		} catch (Exception e) {
		}
	}

	private void nuevoCampo() {
		CampoPersonalizado campo = null;
		NuevoCampo n = new NuevoCampo(true);
		Util.pushModalScreen(n.getScreen());
		campo = n.getCampo();
		if (campo != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(campo, 0);
			} else {
				_screen.addElement(campo, 1);
			}
		}
	}

	private void verCampo() {
		CampoPersonalizado selected = (CampoPersonalizado) _screen
				.getSelected();
		CampoPersonalizado campo = Util.verCampo(selected);
		if (campo != null) {
			_screen.replace(selected, campo);
		} else {
			_screen.remove(selected);
		}
	}

	private void eliminarCampo() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDCampo(), 1);
		if (sel == 0) {
			CampoPersonalizado selected = (CampoPersonalizado) _screen
					.getSelected();
			try {
				new Persistence().borrarAtributo(selected);
			} catch (NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
			_screen.remove(selected);
		}
	}

	private void cerrarPantalla() {
		_selected = null;
		Util.popScreen((Screen) _screen);
	}

	public String toString() {
		return "Lista de campos personalizados";
	}
}
