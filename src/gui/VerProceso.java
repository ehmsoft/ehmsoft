package gui;

import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import core.Proceso;

public class VerProceso extends MainScreen {

	/**
	 * 
	 */
	BasicEditField _txtDemandante;
	BasicEditField _txtDemandado;
	DateField _dfFecha;
	BasicEditField _txtJuzgado;
	BasicEditField _radicado;
	BasicEditField _radicadoUnico;
	ObjectChoiceField _actuaciones;
	BasicEditField _estado;
	BasicEditField _categoria;
	BasicEditField _tipo;
	BasicEditField _notas;
	NumericChoiceField _prioridad;
	
	
	public VerProceso(Object proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Proceso");

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
