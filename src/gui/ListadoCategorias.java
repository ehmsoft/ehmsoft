package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Categoria;

public class ListadoCategorias {

	private Vector _vectorCategorias;
	private ListadosInterface _screen;
	private long _style;
	private Categoria _selected;
	
	public static final int SEARCH = 1;
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
		if(popup) {
			_screen = new ListadoCategoriasPopUp();
		}
		else {
			_screen = new ListadoCategoriasScreen();
		}
		
		if((_style & SEARCH) == SEARCH) {
			_screen.setSearchField();
		}
		if((_style & SEARCH) != NO_NUEVO) {
			_screen.addElement("Crear nuevo categoria", 0);
		}
		
		try {
			_vectorCategorias = new Persistence().consultarCategorias();
		} catch(NullPointerException e) {
			_screen.alert(Util.noSD());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		
		addCategorias();
		((Screen)_screen).setChangeListener(listener);
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == Util.CLICK) {
				onClick();
			} else if(context == Util.VER_ELEMENTO) {
				verCategoria();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	private void addCategorias() {
		if(_vectorCategorias != null) {
			_screen.loadFrom(_vectorCategorias);
		}
	}

	public void setVectorCategorias(Vector categorias) {
		_vectorCategorias = categorias;
		addCategorias();
	}

	public Categoria getSelected() {
		return _selected;
	}

	public Screen getScreen() {
		return (Screen)_screen;

	}
	
	public void onClick() {
		if(String.class.isInstance(_screen.getSelected())) {
			nuevoCategoria();
		} else {
			if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
				verCategoria();
			} else {
				_selected = (Categoria)_screen.getSelected();
				UiApplication.getUiApplication().popScreen((Screen)_screen);
			}
		}
	}
	
	private void nuevoCategoria() {
		NuevaCategoria n = new NuevaCategoria();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		Categoria categoria = n.getCategoria();
		if (categoria != null) {
			if ((_style & NO_NUEVO) != NO_NUEVO) {
				_screen.addElement(categoria, 0);
			} else {
				_screen.addElement(categoria, 1);
			}
		}
		n = null;
	}
	
	private void verCategoria() {
		Categoria selected = (Categoria)_screen.getSelected();
		VerCategoria v = new VerCategoria(selected);
		UiApplication.getUiApplication().pushModalScreen(v.getScreen());
		Categoria categoria = v.getCategoria();
		if(categoria != null) {
			_screen.replace(selected, categoria);
		} else {
			eliminarCategoria();
		}
	}
	
	private void eliminarCategoria() {
		Categoria selected = (Categoria)_screen.getSelected();
		try {
			new Persistence().borrarCategoria(selected);
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
			_selected = (Categoria)_screen.getSelected();
		}
		UiApplication.getUiApplication().popScreen((Screen)_screen);
	}
}