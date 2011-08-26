package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.CampoPersonalizado;

public class ListadoCampos {

	private Vector _vectorCampos;
	private ListadosInterface _screen;
	private long _style;
	private CampoPersonalizado _selected;
	
	public static final int SEARCH = 1;
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
		if(popup) {
			_screen = new ListadoCamposPopUp();
		}
		else {
			_screen = new ListadoCamposScreen();
		}
		
		try {
			_vectorCampos = new Persistence().consultarAtributos();
		} catch(NullPointerException e) {
			_screen.alert(Util.noSDString());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		
		addCampos();
		((Screen)_screen).setChangeListener(listener);
		
		if((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
		if((_style & NO_NUEVO) != NO_NUEVO) {
			_screen.addElement("Crear nuevo campo", 0);
		}
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.CLICK) {
				onClick();
			} else if(context == Util.VER_ELEMENTO) {
				verCampo();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	private void addCampos() {
		if(_vectorCampos != null) {
			_screen.loadFrom(_vectorCampos);
		}
	}

	public void setVectorCampos(Vector campos) {
		_vectorCampos = campos;
		addCampos();
	}

	public CampoPersonalizado getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen)_screen;

	}
	
	public void onClick() {
		if(String.class.isInstance(_screen.getSelected())) {
			nuevoCampo();
		} else {
			if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verCampo();
			} else {
				_selected = (CampoPersonalizado)_screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen)_screen);
			}
		}
	}
	
	private void nuevoCampo() {
		NuevoCampo n = new NuevoCampo();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		CampoPersonalizado campo = n.getCampo();
		if (campo != null) {
			if ((_style & NO_NUEVO) == NO_NUEVO) {
				_screen.addElement(campo, 0);
			} else {
				_screen.addElement(campo, 1);
			}
		}
		n = null;
	}
	
	private void verCampo() {
		CampoPersonalizado selected = (CampoPersonalizado)_screen.getSelected();
		VerCampo v = new VerCampo(selected);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		CampoPersonalizado campo = v.getCampo();
		if(campo != null) {
			_screen.replace(selected, campo);
		} else {
			eliminarCampo();
		}
	}
	
	private void eliminarCampo() {
		CampoPersonalizado selected = (CampoPersonalizado)_screen.getSelected();
		try {
			new Persistence().borrarAtributo(selected);
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
			_selected = (CampoPersonalizado)_screen.getSelected();
		}
		UiApplication.getUiApplication().popScreen((Screen)_screen);
	}
}
