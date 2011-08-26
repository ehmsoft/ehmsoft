package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Proceso;

public class ListadoProcesos {

	private Vector _vectorProcesos;
	private ListadosInterface _screen;
	private long _style;
	private Proceso _selected;
	
	public static final int SEARCH = 1;
	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;
	
	public ListadoProcesos() {
		this(false, 0);
	}
	
	public ListadoProcesos(boolean popup) {
		this(popup, 0);
	}
	
	public ListadoProcesos(long style) {
		this(false, style);
	}

	public ListadoProcesos(boolean popup, long style) {
		_style = style;
		if(popup) {
			_screen = new ListadoProcesosPopUp();
		}
		else {
			_screen = new ListadoProcesosScreen();
		}
		
		try {
			_vectorProcesos = new Persistence().consultarProcesos();
		} catch(NullPointerException e) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert(e.toString());
		}
		
		addProcesos();
		((Screen)_screen).setChangeListener(listener);
		
		if((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
		if((_style & NO_NUEVO) != NO_NUEVO) {
			_screen.addElement("Crear nuevo proceso", 0);
		}
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.CLICK) {
				onClick();
			} else if(context == Util.VER_ELEMENTO) {
				verProceso();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if(context == Util.ELIMINAR) {
				eliminarProceso();
			}
		}
	};

	private void addProcesos() {
		if(_vectorProcesos != null) {
			_screen.loadFrom(_vectorProcesos);
		}
	}

	public void setVectorProcesos(Vector procesos) {
		_vectorProcesos = procesos;
		addProcesos();
	}

	public Proceso getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen)_screen;

	}
	
	public void onClick() {
		if(String.class.isInstance(_screen.getSelected())) {
			nuevoProceso();
		} else {
			if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verProceso();
			} else {
				_selected = (Proceso)_screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen)_screen);
			}
		}
	}
	
	private void nuevoProceso() {
		NuevoProceso n = new NuevoProceso();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Proceso proceso = n.getProceso();
		if (proceso != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(proceso, 0);
			} else {
				_screen.addElement(proceso, 1);
			}
		}
		n = null;
	}
	
	private void verProceso() {
		Proceso selected = (Proceso)_screen.getSelected();
		Proceso proceso = Util.verProceso(selected);
		if(proceso != null) {
			_screen.replace(selected, proceso);
		} else {
			_screen.remove(selected);
		}
	}
	
	private void eliminarProceso() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDProceso(), 1);
		if (sel == 0) {
			Proceso selected = (Proceso) _screen.getSelected();
			try {
				new Persistence().borrarProceso(selected);
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
		if(String.class.isInstance(_screen.getSelected())) {
			_selected = null;
		} else {
			_selected = (Proceso)_screen.getSelected();
		}
		UiApplication.getUiApplication().popScreen((Screen)_screen);
	}
}
