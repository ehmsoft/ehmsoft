package gui;

import java.util.Calendar;

import core.Juzgado;
import core.Persona;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.DateField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.NumericChoiceField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import ehmsoft.ListadoPersonasController;

public class NuevoProceso extends MainScreen {

	/**
	 * 
	 */
	ObjectChoiceField estado;
	ObjectChoiceField categoria;
	NumericChoiceField prioridad;
	DateField fecha;
	BasicEditField txtNotas;
	BasicEditField txtRadicado;
	BasicEditField txtRadicadoUnico;
	ButtonField btnDemandante;
	ButtonField btnDemandado;
	ButtonField btnJuzgado;
	
	Persona demandante;
	Persona demandado;
	Juzgado juzgado;
	
	
	public NuevoProceso() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Nuevo Proceso");
		
		HorizontalFieldManager fldChoiceElements = new HorizontalFieldManager();
		VerticalFieldManager fldRight = new VerticalFieldManager(USE_ALL_WIDTH);
		VerticalFieldManager fldLeft = new VerticalFieldManager();
		
		btnDemandante = new ButtonField("Seleccionar",FIELD_RIGHT);
		btnDemandante.setChangeListener(listenerBtnDemandante);
		fldLeft.add(new LabelField("Demandante: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(btnDemandante);
		
		btnDemandado = new ButtonField("Seleccionar",FIELD_RIGHT);
		btnDemandado.setChangeListener(listenerBtnDemandado);
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
		
		txtNotas = new BasicEditField();
		txtNotas.setLabel("Notas: ");
		add(txtNotas);
	}
	
    private FieldChangeListener listenerBtnDemandante = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoPersonasController demandantes = new ListadoPersonasController(1);
    		UiApplication.getUiApplication().pushModalScreen(demandantes.getScreen());
    		demandante = demandantes.getSelected();
    		demandantes = null;
    		btnDemandante.setLabel(demandante.getNombre());
    	}
    };
    
    private FieldChangeListener listenerBtnDemandado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoPersonasController demandados = new ListadoPersonasController(2);
    		UiApplication.getUiApplication().pushModalScreen(demandados.getScreen());
    		demandado = demandados.getSelected();
    		demandados = null;
    		btnDemandado.setLabel(demandado.getNombre());
    	}
    };
    
    private FieldChangeListener listenerBtnJuzgado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    	}
    };
    
    public Persona getDemandante() {
    	return demandante;
    }
    
    public Persona getDemandado() {
    	return demandado;
    }
    
    public Juzgado getJuzgado() {
    	return juzgado;
    }
    
    public String getRadicado() {
    	return txtRadicado.getText();
    }
    
    public String getRadicadoUnico() {
    	return txtRadicadoUnico.getText();
    }
    
    public String getEstado() {
    	return (String)estado.getChoice(estado.getSelectedIndex());
    }
    
    public String getCategoria() {
    	return (String)estado.getChoice(estado.getSelectedIndex());
    }
    
    public String getPrioridad() {
    	return (String)estado.getChoice(estado.getSelectedIndex());
    }
    
    public String getNotas() {
    	return txtNotas.getText();
    }
    
    public Calendar getFecha() {
    	Calendar fecha = Calendar.getInstance();
		
		fecha.setTime(fecha.getTime());
		return fecha;
    }
    
    public boolean onClose() {
    	UiApplication.getUiApplication().popScreen(getScreen());
    	return true;
    }
}