package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Persona;

public class ListadoPersonas {

	private Vector _vectorPersonas;
	private ListadosInterface _screen;
	private long _style;
	private Persona _selected;
	private int _tipo;
	
	public static final int SEARCH = 1;
	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;
	
	public ListadoPersonas(int tipo) {
		this(tipo, false, 0);
	}
	
	public ListadoPersonas(int tipo, boolean popup) {
		this(tipo, popup, 0);
	}
	
	public ListadoPersonas(int tipo, long style) {
		this(tipo, false, style);
	}

	public ListadoPersonas(int tipo, boolean popup, long style) {
		_style = style;
		_tipo = tipo;
		if(popup) {
			_screen = new ListadoPersonasPopUp();
		}
		else {
			_screen = new ListadoPersonasScreen();
		}
		
		try {
			if(_tipo == 1) {
				_vectorPersonas = new Persistence().consultarDemandantes();
			} else {
				_vectorPersonas = new Persistence().consultarDemandados();
			}
		} catch(NullPointerException e) {
			_screen.alert(Util.noSDString());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		
		addPersonas();
		((Screen)_screen).setChangeListener(listener);
		
		if((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
		if((_style & NO_NUEVO) != NO_NUEVO) {
			if(_tipo == 1) {
				_screen.addElement("Crear nuevo demandante", 0);
			} else {
				_screen.addElement("Crear nuevo demandado", 0);
			}
		}
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.CLICK) {
				onClick();
			} else if(context == Util.VER_ELEMENTO) {
				verPersona();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	private void addPersonas() {
		if(_vectorPersonas != null) {
			_screen.loadFrom(_vectorPersonas);
		}
	}

	public void setVectorPersonas(Vector juzgados) {
		_vectorPersonas = juzgados;
		addPersonas();
	}

	public Persona getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen)_screen;

	}
	
	public void onClick() {
		if(String.class.isInstance(_screen.getSelected())) {
			nuevaPersona();
		} else {
			if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verPersona();
			} else {
				_selected = (Persona)_screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen)_screen);
			}
		}
	}
	
	private void nuevaPersona() {
		NuevaPersona n = new NuevaPersona(_tipo);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Persona juzgado = n.getPersona();
		if (juzgado != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(juzgado, 0);
			} else {
				_screen.addElement(juzgado, 1);
			}
		}
		n = null;
	}
	
	private void verPersona() {
		Persona selected = (Persona)_screen.getSelected();
		VerPersona v = new VerPersona(selected);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		Persona juzgado = v.getPersona();
		if(juzgado != null) {
			_screen.replace(selected, juzgado);
		} else {
			eliminarPersona();
		}
	}
	
	private void eliminarPersona() {
		Persona selected = (Persona)_screen.getSelected();
		try {
			new Persistence().borrarPersona(selected);
		} catch (NullPointerException e) {
			_screen.alert(Util.noSDString());
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
			_selected = (Persona)_screen.getSelected();
		}
		UiApplication.getUiApplication().popScreen((Screen)_screen);
	}
}