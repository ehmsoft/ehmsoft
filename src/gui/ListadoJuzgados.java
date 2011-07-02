package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Juzgado;

public class ListadoJuzgados {

	private Persistence _persistencia;
	private Vector _vectorJuzgados;
	private ListadoJuzgadosScreen _screen;

	public ListadoJuzgados() {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			_vectorJuzgados = _persistencia.consultarJuzgados();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		_screen = new ListadoJuzgadosScreen();
		addJuzgados();
	}

	private void addJuzgados() {
		Enumeration index;
		try {
			index = _vectorJuzgados.elements();

			while (index.hasMoreElements())
				_screen.addJuzgado(index.nextElement());
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
		NuevoJuzgado nuevoJuzgado = new NuevoJuzgado();
		if (String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(
					nuevoJuzgado.getScreen());
			Juzgado juzgado = null;
			try {
				juzgado = nuevoJuzgado.getJuzgado();
			} catch(Exception e) {
				return null;
			}
			_screen.addJuzgado(juzgado);
			return juzgado;
		} else
			return (Juzgado) _screen.getSelected();
	}

	public ListadoJuzgadosScreen getScreen() {
		return _screen;
	}
}
