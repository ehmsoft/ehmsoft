package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.CampoPersonalizado;

public class ListadoCamposPersonalizadosController {

	private Persistence _persistencia;
	private Vector _vectorCampos;
	private ListadoCamposPersonalizados _screen;

	public ListadoCamposPersonalizadosController() {
		try {
			_persistencia = new Persistence();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		_screen = new ListadoCamposPersonalizados();
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
		NuevoCampoPersonalizadoController nuevoCampo = new NuevoCampoPersonalizadoController();
		if (String.class.isInstance(_screen.getSelected())) {
			UiApplication.getUiApplication().pushModalScreen(
					nuevoCampo.getScreen());
			nuevoCampo.guardarCampo();
			_screen.addCampo(nuevoCampo.getCampo());
			return nuevoCampo.getCampo();
		} else
			return (CampoPersonalizado) _screen.getSelected();
	}

	public ListadoCamposPersonalizados getScreen() {
		return _screen;
	}
}