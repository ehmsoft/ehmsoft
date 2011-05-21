package gui;

import core.Persona;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import ehmsoft.NuevoProcesoController;;

public class NuevoProceso extends MainScreen {

	/**
	 * 
	 */
	ObjectChoiceField estado;
	ObjectChoiceField categoria;
	NumericChoiceField prioridad;
	DateField fecha;
	BasicEditField notas;
	BasicEditField txtRadicado;
	BasicEditField txtRadicadoUnico;
	ButtonField btnDemandante;
	ButtonField btnDemandado;
	ButtonField btnJuzgado;
	NuevoProcesoController controller;
	
	public NuevoProceso(NuevoProcesoController controller) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		this.controller = controller;
		setTitle("Crear Proceso");
		
		HorizontalFieldManager fldChoiceElements = new HorizontalFieldManager();
		VerticalFieldManager fldRight = new VerticalFieldManager(USE_ALL_WIDTH);
		VerticalFieldManager fldLeft = new VerticalFieldManager();
		
		btnDemandante = new ButtonField("Seleccionar",FIELD_RIGHT);
		btnDemandante.setChangeListener(listenerBtnDemandante);
		fldLeft.add(new LabelField("Demandante: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(btnDemandante);
		
		btnDemandado = new ButtonField("Seleccionar",FIELD_RIGHT);
		fldLeft.add(new LabelField("Demandado: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(btnDemandado);
		
		ButtonField btnJuzgado = new ButtonField("Seleccionar",FIELD_RIGHT);
		fldLeft.add(new LabelField("Juzgado: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(btnJuzgado);
		
		fldChoiceElements.add(fldLeft);
		fldChoiceElements.add(fldRight);
		add(fldChoiceElements);
		
		txtRadicado = new BasicEditField(BasicEditField.NO_NEWLINE);
		txtRadicado.setLabel("Radicado: ");
		add(txtRadicado);
		
		txtRadicadoUnico = new BasicEditField(BasicEditField.NO_NEWLINE);
		txtRadicadoUnico.setLabel("Radicado unico: ");
		add(txtRadicadoUnico);
		
		estado = new ObjectChoiceField();
		estado.setLabel("Estado:");
		Object[] initialChoiceEstado = {"Nuevo"};
		estado.setChoices(initialChoiceEstado);
		add(estado);
		
		categoria = new ObjectChoiceField();
		categoria.setLabel("Categoria:");
		Object[] initialChoiceCategoria = {"Nueva"};
		categoria.setChoices(initialChoiceCategoria);
		add(categoria);
		
		prioridad = new NumericChoiceField("Prioridad", 0, 10, 1);
		add(prioridad);
		
		fecha = new DateField("Fecha de creación: ", System.currentTimeMillis(),DateField.DATE);
		fecha.setEditable(true);
		add(fecha);
		
		notas = new BasicEditField();
		notas.setLabel("Notas: ");
		add(notas);
	}
	
    private FieldChangeListener listenerBtnDemandante = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		Persona dem = controller.selectDemandante();
    		if(dem == null)
    			Dialog.alert("La cagó");
    		else
    			Dialog.alert(dem.getNombre());
    	}
    };
    
    private FieldChangeListener listenerBtnDemandado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    	}
    };
    
    private FieldChangeListener listenerBtnJuzgado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    	}
    };
}