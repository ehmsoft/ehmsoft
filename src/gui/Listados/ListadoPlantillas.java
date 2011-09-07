package gui.Listados;

import gui.ListadosInterface;
import gui.Util;
import gui.Nuevos.NuevaPlantilla;
import gui.Nuevos.NuevoProceso;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Plantilla;

public class ListadoPlantillas {

	private Vector _vectorPlantillas;
	private ListadosInterface _screen;
	private long _style;
	private Plantilla _selected;

	public static final int SEARCH = 1;
	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;

	public ListadoPlantillas() {
		this(false, 0);
	}

	public ListadoPlantillas(boolean popup) {
		this(popup, 0);
	}

	public ListadoPlantillas(long style) {
		this(false, style);
	}

	public ListadoPlantillas(boolean popup, long style) {
		_style = style;
		if (popup) {
			_screen = new ListadoPlantillasPopUp();
		} else {
			_screen = new ListadoPlantillasScreen();
		}

		Util.pushWaitScreen();

		UiApplication.getUiApplication().invokeLater(new Runnable() {

			public void run() {
				try {
					_vectorPlantillas = new Persistence().consultarPlantillas();
				} catch (NullPointerException e) {
					Util.noSd();
				} catch (Exception e) {
					Util.alert(e.toString());
				}
				addPlantillas();
				if ((_style & NO_NUEVO) != NO_NUEVO) {
					_screen.addElement("Crear nueva plantilla", 0);
				}
				Util.popWaitScreen();
			}
		});

		((Screen) _screen).setChangeListener(listener);

		if ((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.CLICK) {
				onClick();
			} else if (context == Util.VER_ELEMENTO) {
				verPlantilla();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.ELIMINAR) {
				eliminarPlantilla();
			} else if (context == Util.NEW_PROCESO) {
				nuevoProceso();
			}
		}
	};

	private void nuevoProceso() {
		NuevoProceso n = new NuevoProceso((Plantilla) _screen.getSelected());
		Util.popScreen((Screen) _screen);
		Util.pushModalScreen(n.getScreen());
	}

	private void addPlantillas() {
		if (_vectorPlantillas != null) {
			_screen.loadFrom(_vectorPlantillas);
		}
	}

	public void setVectorPlantillas(Vector plantillas) {
		_vectorPlantillas = plantillas;
		addPlantillas();
	}

	public Plantilla getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen) _screen;

	}

	public void onClick() {
		if (String.class.isInstance(_screen.getSelected())) {
			nuevaPlantilla();
		} else {
			if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verPlantilla();
			} else {
				_selected = (Plantilla) _screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen) _screen);
			}
		}
	}

	private void nuevaPlantilla() {
		NuevaPlantilla n = new NuevaPlantilla();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Plantilla proceso = n.getPlantilla();
		if (proceso != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(proceso, 0);
			} else {
				_screen.addElement(proceso, 1);
			}
		}
		n = null;
	}

	private void verPlantilla() {
		Plantilla selected = (Plantilla) _screen.getSelected();
		Plantilla plantilla = Util.verPlantilla(selected);
		if (plantilla != null) {
			_screen.replace(selected, plantilla);
		} else {
			_screen.remove(selected);
		}
	}

	private void eliminarPlantilla() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDPlantilla(), 1);
		if (sel == 0) {
			Plantilla selected = (Plantilla) _screen.getSelected();
			try {
				new Persistence().borrarPlantilla(selected);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			_screen.remove(selected);
		}
	}

	private void cerrarPantalla() {
		_selected = null;
		UiApplication.getUiApplication().popScreen((Screen) _screen);
	}
}