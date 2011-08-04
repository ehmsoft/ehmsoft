package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Persona;

public class ListadoPersonas {

	private Persistence _persistencia;
	private Vector _vectorPersonas;
	private ListadoPersonasScreen _screen;
	private ListadoPersonasPopUp _screenPp;
	
	/**
	 * @param tipo El tipo de persona
	 * @param popup true si se desea crear una pantalla tipo PopUp
	 */
	public ListadoPersonas(int tipo, boolean popup) {
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
		else {
			try {
				_vectorPersonas = _persistencia.consultarDemandados();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(popup) {
			_screenPp = new ListadoPersonasPopUp(tipo);
		}
		else {
			_screen = new ListadoPersonasScreen(tipo);
		}
		addPersonas();
	}

	public ListadoPersonas(int tipo) {
		this(tipo, false);
	}
	
	public ListadoPersonas(int tipo, long style) {
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
			}
		else {
			try {
				_vectorPersonas = _persistencia.consultarDemandados();
			} catch (Exception e) {
			}
		}
		
		_screen = new ListadoPersonasScreen(tipo, style);
		addPersonas();
	}
	
	public ListadoPersonas(int tipo, boolean popup, long style) {
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
		else {
			try {
				_vectorPersonas = _persistencia.consultarDemandados();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(popup) {
			_screenPp = new ListadoPersonasPopUp(tipo);
		}
		else {
			_screen = new ListadoPersonasScreen(tipo,style);
		}
		addPersonas();
	}
	
	public void setTitle(String title) {
		if(_screen != null) {
			_screen.setTitle(title);
		} else {
			//_screenPp.setTitle(title);
		}
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
				if (_screen != null) {
					_screen.addPersona(index.nextElement());
				} else {
					//_screenPp.addPersona(index.nextElement());
				}
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public Persona getSelected() {
		if (_screen != null) {
			return (Persona) _screen.getSelected();
		} else {
			//return (Persona) _screenPp.getSelected();
			return null;
		}
	}

	public Screen getScreen() {
		if (_screen != null) {
			return _screen;
		} else {
			return _screenPp;
		}
	}
}
