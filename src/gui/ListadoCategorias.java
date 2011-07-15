package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Categoria;

public class ListadoCategorias {

	private Persistence _persistencia;
	private Vector _vectorCategorias;
	private ListadoCategoriasScreen _screen;

	public ListadoCategorias() {
		try {
			_persistencia = new Persistence();
			_vectorCategorias = _persistencia.consultarCategorias();
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}

		_screen = new ListadoCategoriasScreen();
		addCategorias();
	}

	public void setVectorCategorias(Vector categorias) {
		_vectorCategorias = categorias;
		addCategorias();
	}

	private void addCategorias() {
		Enumeration index;
		try {
			index = _vectorCategorias.elements();
			while (index.hasMoreElements())
				_screen.addCategoria((Categoria) index.nextElement());
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public Categoria getSelected() {
		return _screen.getSelected();
	}

	public ListadoCategoriasScreen getScreen() {
		return _screen;
	}
}