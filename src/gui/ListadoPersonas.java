package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class ListadoPersonas {

	private Persistence _persistencia;
	private Vector _vectorPersonas;
	private ListadoPersonasScreen _screen;

	public ListadoPersonas(int tipo) {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (tipo == 1)
			try {
				_vectorPersonas = _persistencia.consultarDemandantes();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else
			try {
				_vectorPersonas = _persistencia.consultarDemandados();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		_screen = new ListadoPersonasScreen(tipo);
		addPersonas();
	}

	public void setVectorPersonas(Vector personas) {
		_vectorPersonas = personas;
		addPersonas();
	}

	private void addPersonas() {
		Enumeration index;
		try {
			index = _vectorPersonas.elements();
			while (index.hasMoreElements())
				_screen.addPersona(index.nextElement());
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public Persona getSelected() {
		return (Persona) _screen.getSelected();
	}

	public ListadoPersonasScreen getScreen() {
		return _screen;
	}
}
