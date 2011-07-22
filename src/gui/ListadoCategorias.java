package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Categoria;

public class ListadoCategorias {

	private Persistence _persistencia;
	private Vector _vectorCategorias;
	private ListadoCategoriasScreen _screen;
	private ListadoCategoriasPopUp _screenPp;

	public ListadoCategorias(boolean popup) {
		try {
			_persistencia = new Persistence();
			_vectorCategorias = _persistencia.consultarCategorias();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		
		if(popup) {
			_screenPp = new ListadoCategoriasPopUp();
		} else {
			_screen = new ListadoCategoriasScreen();
		}
		addCategorias();
	}
	
	public ListadoCategorias() {
		new ListadoCategorias(false);
	}

	public void setVectorCategorias(Vector categorias) {
		_vectorCategorias = categorias;
		addCategorias();
	}
	
	public void setTitle(String title) {
		if(_screen != null) {
			_screen.setTitle(title);
		} else {
			_screenPp.setTitle(title);
		}
	}

	private void addCategorias() {
		Enumeration index;
		try {
			index = _vectorCategorias.elements();
			while (index.hasMoreElements())
				if(_screen != null) {
					_screen.addCategoria((Categoria) index.nextElement());
				} else {
					_screenPp.addCategoria((Categoria) index.nextElement());
				}
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public Categoria getSelected() {
		if(_screen != null) {
			return _screen.getSelected();
		} else {
			return _screenPp.getSelected();
		}
	}

	public Screen getScreen() {
		if(_screen != null) {
			return _screen;
		} else {
			return _screenPp;
		}
	}
}