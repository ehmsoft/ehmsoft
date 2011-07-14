package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.component.Dialog;
import core.CampoPersonalizado;

public class ListadoCamposPersonalizados {

	private Vector _vectorCampos;
	private ListadoCamposPersonalizadosScreen _screen;

	public ListadoCamposPersonalizados() {
		_screen = new ListadoCamposPersonalizadosScreen();
		addCampos();
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
				_screen.addCampo(index.nextElement());
		} catch (NullPointerException e) {

		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public CampoPersonalizado getSelected() {
		return (CampoPersonalizado) _screen.getSelected();
	}

	public ListadoCamposPersonalizadosScreen getScreen() {
		return _screen;
	}
}