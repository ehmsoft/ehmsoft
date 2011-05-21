package gui;

import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import ehmsoft.Main;

public class VerProceso extends MainScreen {

	/**
	 * 
	 */
	Main theApp;
	public VerProceso(Main theApp, Proceso proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Proceso");
		this.theApp = theApp;
		
		String string = getFont()+""+getFont().getHeight()+"";
		
		Dialog.alert(string);
		
		BasicEditField txtDemandante = new BasicEditField();
		txtDemandante.setLabel("Demandante: ");
		txtDemandante.setText(proceso.getDemandante());
		
		BasicEditField txtDemandado = new BasicEditField();
		txtDemandado.setLabel("Demandado: ");
		txtDemandado.setText(proceso.getDemandado());
		
		add(txtDemandante);
		add(new SeparatorField());
		add(txtDemandado);
	}

}
