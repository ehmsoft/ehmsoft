package gui;

import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import core.Proceso;

public class VerProceso extends MainScreen {

	/**
	 * 
	 */
	public VerProceso(Object proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Proceso");

		String string = getFont() + "" + getFont().getHeight() + "";

		Dialog.alert(string);

		BasicEditField txtDemandante = new BasicEditField();
		txtDemandante.setLabel("Demandante: ");
		txtDemandante.setText(((Proceso) proceso).getDemandante().getNombre());

		BasicEditField txtDemandado = new BasicEditField();
		txtDemandado.setLabel("Demandado: ");
		txtDemandado.setText(((Proceso) proceso).getDemandado().getNombre());

		add(txtDemandante);
		add(new SeparatorField());
		add(txtDemandado);
	}

}
