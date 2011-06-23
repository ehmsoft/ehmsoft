package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class ListadoPersonasController {

	private int _tipo;
	private Persistence _persistencia;
	private Vector _vectorPersonas;
	private ListadoPersonas _screen;

	public ListadoPersonasController(int tipo, Vector fuentes) {
		_tipo = tipo;
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
				Dialog.alert(e.toString());
			}
		else
			try {
				_vectorPersonas = _persistencia.consultarDemandados();
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}

		_screen = new ListadoPersonas(tipo, fuentes);
		addPersonas();
	}

	public ListadoPersonasController(int tipo) {
		_tipo = tipo;
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

		_screen = new ListadoPersonas(tipo);
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
		NuevaPersonaController nuevaPersona = new NuevaPersonaController(_tipo);
		if (String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(
					nuevaPersona.getScreen());
			nuevaPersona.guardarPersona();
			_screen.addPersona(nuevaPersona.getPersona());
			return nuevaPersona.getPersona();
		} else
			return (Persona) _screen.getSelected();
	}

	public ListadoPersonas getScreen() {
		return _screen;
	}
}
