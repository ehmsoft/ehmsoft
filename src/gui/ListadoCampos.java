package gui;

import java.util.Enumeration;
import java.util.Vector;

import persistence.Persistence;

import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.component.Dialog;
import core.CampoPersonalizado;

public class ListadoCampos {

	private Vector _vectorCampos;
	private ListadoCamposScreen _screen;
	private ListadoCamposPopUp _screenPp;

	public ListadoCampos(boolean popup, long style) {
		Persistence p;
		try {
			p = new Persistence();
			_vectorCampos = p.consultarAtributos();
		} catch(Exception e) {
			Dialog.alert(e.toString());
		}
		if (popup) {
			_screenPp = new ListadoCamposPopUp(style);
		} else {
			_screen = new ListadoCamposScreen(style);
		}
		addCampos();
	}

	public ListadoCampos() {
		this(false, 0);
	}
	
	public ListadoCampos(boolean popup) {
		this(popup, 0);
	}

	public void setVectorCampos(Vector campos) {
		_vectorCampos = campos;
		addCampos();
	}

	private void addCampos() {
		Enumeration index;
		try {
			index = _vectorCampos.elements();
			while (index.hasMoreElements())
				if (_screen != null) {
					_screen.addCampo(index.nextElement());
				} else {
					_screenPp
							.addCampo((CampoPersonalizado) index.nextElement());
				}
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public CampoPersonalizado getSelected() {
		if (_screen != null) {
			return (CampoPersonalizado) _screen.getSelected();
		} else {
			return _screenPp.getSelected();
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