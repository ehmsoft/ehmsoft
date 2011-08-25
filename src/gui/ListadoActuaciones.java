package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Actuacion;
import core.Proceso;

public class ListadoActuaciones {

	private Vector _vectorActuaciones;
	private ListadosInterface _screen;
	private long _style;
	private Actuacion _selected;
	private Proceso _proceso;
	
	public static final int SEARCH = 1;
	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;
	
	public ListadoActuaciones(Proceso proceso) {
		this(proceso ,false, 0);
	}
	
	public ListadoActuaciones(Proceso proceso, boolean popup) {
		this(proceso ,popup, 0);
	}
	
	public ListadoActuaciones(Proceso proceso, long style) {
		this(proceso, false, style);
	}

	public ListadoActuaciones(Proceso proceso, boolean popup, long style) {
		_style = style;
		_proceso = proceso;
		if(popup) {
			_screen = new ListadoActuacionesPopUp();
		}
		else {
			_screen = new ListadoActuacionesScreen();
		}
		
		try {
			_vectorActuaciones = new Persistence().consultarActuaciones(_proceso);
		} catch(NullPointerException e) {
			_screen.alert(Util.noSD());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		
		addActuaciones();
		((Screen)_screen).setChangeListener(listener);
		
		if((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
		if((_style & SEARCH) != NO_NUEVO) {
			_screen.addElement("Crear nueva actuación", 0);
		}
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.CLICK) {
				onClick();
			} else if(context == Util.VER_ELEMENTO) {
				verActuacion();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	private void addActuaciones() {
		if(_vectorActuaciones != null) {
			_screen.loadFrom(_vectorActuaciones);
		}
	}

	public void setVectorActuaciones(Vector actuaciones) {
		_vectorActuaciones = actuaciones;
		addActuaciones();
	}

	public Actuacion getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen)_screen;

	}
	
	public void onClick() {
		if(String.class.isInstance(_screen.getSelected())) {
			nuevaActuacion();
		} else {
			if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verActuacion();
			} else {
				_selected = (Actuacion)_screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen)_screen);
			}
		}
	}
	
	private void nuevaActuacion() {
		NuevaActuacion n = new NuevaActuacion(_proceso);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Actuacion actuacion = n.getActuacion();
		if (actuacion != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(actuacion, 0);
			} else {
				_screen.addElement(actuacion, 1);
			}
		}
		n = null;
	}
	
	private void verActuacion() {
		Actuacion selected = (Actuacion)_screen.getSelected();
		VerActuacion v = new VerActuacion(selected);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		Actuacion actuacion = v.getActuacion();
		if(actuacion != null) {
			_screen.replace(selected, actuacion);
		} else {
			eliminarActuacion();
		}
	}
	
	private void eliminarActuacion() {
		Actuacion selected = (Actuacion)_screen.getSelected();
		try {
			new Persistence().borrarActuacion(selected);
		} catch (NullPointerException e) {
			_screen.alert(Util.noSD());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		_screen.remove(selected);
	}
	
	private void cerrarPantalla() {
		if(String.class.isInstance(_screen.getSelected())) {
			_selected = null;
		} else {
			_selected = (Actuacion)_screen.getSelected();
		}
		UiApplication.getUiApplication().popScreen((Screen)_screen);
	}
}
