package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Juzgado;

public class ListadoJuzgados {

	private Vector _vectorJuzgados;
	private ListadoJuzgadosScreen _screen;
	private ListadoJuzgadosPopUp _screenPp;

	public ListadoJuzgados(boolean popup) {
		Persistence p;
		try {
			p = new Persistence();
			_vectorJuzgados = p.consultarJuzgados();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		if(popup) {
			_screenPp = new ListadoJuzgadosPopUp();
		}
		else {
			_screen = new ListadoJuzgadosScreen();
		}
		addJuzgados();
	}
	
	public ListadoJuzgados() {
		new ListadoJuzgados(false);
	}
	
	public void setTitle(String title) {
		if(_screen != null) {
			_screen.setTitle(title);
		} else {
			_screenPp.setTitle(title);
		}
	}

	private void addJuzgados() {
		Enumeration index;
		try {
			index = _vectorJuzgados.elements();

			while (index.hasMoreElements())
				if(_screen != null) {
					_screen.addJuzgado(index.nextElement());
				} else {
					_screenPp.addJuzgado(index.nextElement());
				}
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public void setVectorJuzgados(Vector juzgados) {
		_vectorJuzgados = juzgados;
		addJuzgados();
	}

	public Juzgado getSelected() {
		if(_screen != null) {
			return (Juzgado) _screen.getSelected();
		} else {
			return (Juzgado) _screenPp.getSelected();
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
