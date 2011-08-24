package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Juzgado;

public class ListadoJuzgados {

	private Vector _vectorJuzgados;
	private ListadosInterface _screen;
	private long _style;
	private Juzgado _selected;
	
	public static final int SEARCH = 1;
	public static final int ON_CLICK_VER = 2;
	public static final int ON_CLICK_SELECT = 4;
	public static final int NO_NUEVO = 8;
	
	public ListadoJuzgados() {
		this(false, 0);
	}
	
	public ListadoJuzgados(boolean popup) {
		this(popup, 0);
	}
	
	public ListadoJuzgados(long style) {
		this(false, style);
	}

	public ListadoJuzgados(boolean popup, long style) {
		_style = style;
		if(popup) {
			_screen = new ListadoJuzgadosPopUp();
		}
		else {
			_screen = new ListadoJuzgadosScreen();
		}
		
		if((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
		if((_style & SEARCH) != NO_NUEVO) {
			_screen.addElement("Crear nuevo juzgado", 0);
		}
		
		try {
			_vectorJuzgados = new Persistence().consultarJuzgados();
		} catch(NullPointerException e) {
			_screen.alert(Util.noSD());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		
		addJuzgados();
		((Screen)_screen).setChangeListener(listener);
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.CLICK) {
				onClick();
			} else if(context == Util.VER_ELEMENTO) {
				verJuzgado();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	private void addJuzgados() {
		if(_vectorJuzgados != null) {
			_screen.loadFrom(_vectorJuzgados);
		}
	}

	public void setVectorJuzgados(Vector juzgados) {
		_vectorJuzgados = juzgados;
		addJuzgados();
	}

	public Juzgado getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen)_screen;

	}
	
	public void onClick() {
		if(String.class.isInstance(_screen.getSelected())) {
			nuevoJuzgado();
		} else {
			if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verJuzgado();
			} else {
				_selected = (Juzgado)_screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen)_screen);
			}
		}
	}
	
	private void nuevoJuzgado() {
		NuevoJuzgado n = new NuevoJuzgado();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Juzgado juzgado = n.getJuzgado();
		if (juzgado != null) {
			if ((_style & NO_NUEVO) != NO_NUEVO) {
				_screen.addElement(juzgado, 0);
			} else {
				_screen.addElement(juzgado, 1);
			}
		}
		n = null;
	}
	
	private void verJuzgado() {
		Juzgado selected = (Juzgado)_screen.getSelected();
		VerJuzgado v = new VerJuzgado(selected);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		Juzgado juzgado = v.getJuzgado();
		if(juzgado != null) {
			_screen.replace(selected, juzgado);
		} else {
			eliminarJuzgado();
		}
	}
	
	private void eliminarJuzgado() {
		Juzgado selected = (Juzgado)_screen.getSelected();
		try {
			new Persistence().borrarJuzgado(selected);
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
			_selected = (Juzgado)_screen.getSelected();
		}
		UiApplication.getUiApplication().popScreen((Screen)_screen);
	}
}
