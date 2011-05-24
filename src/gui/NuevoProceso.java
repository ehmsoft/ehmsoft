package gui;

import java.util.Calendar;

import core.Juzgado;
import core.Persona;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
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
import ehmsoft.ListadoJuzgadosController;
import ehmsoft.ListadoPersonasController;

public class NuevoProceso extends MainScreen {

	/**
	 * Pantalla para crear un nuevo proceso
	 */
	private ObjectChoiceField _chEstado;
	private ObjectChoiceField _chCategoria;
	private NumericChoiceField _chPrioridad;
	private DateField _dtFecha;
	private BasicEditField _txtNotas;
	private BasicEditField _txtRadicado;
	private BasicEditField _txtRadicadoUnico;
	private ButtonField _btnDemandante;
	private ButtonField _btnDemandado;
	private ButtonField _btnJuzgado;
	
	private Persona _demandante;
	private Persona _demandado;
	private Juzgado _juzgado;
	
	
	public NuevoProceso() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		setTitle("Nuevo Proceso");
		
		HorizontalFieldManager fldChoiceElements = new HorizontalFieldManager();
		VerticalFieldManager fldRight = new VerticalFieldManager(USE_ALL_WIDTH);
		VerticalFieldManager fldLeft = new VerticalFieldManager();
		
		_btnDemandante = new ButtonField("Seleccionar",FIELD_RIGHT);
		_btnDemandante.setChangeListener(listenerBtnDemandante);
		fldLeft.add(new LabelField("Demandante: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(_btnDemandante);
		
		_btnDemandado = new ButtonField("Seleccionar",FIELD_RIGHT);
		_btnDemandado.setChangeListener(listenerBtnDemandado);
		fldLeft.add(new LabelField("Demandado: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(_btnDemandado);
		
		_btnJuzgado = new ButtonField("Seleccionar",FIELD_RIGHT);
		_btnJuzgado.setChangeListener(listenerBtnJuzgado);
		fldLeft.add(new LabelField("Juzgado: "));
		fldLeft.add(new LabelField(""));
		fldRight.add(_btnJuzgado);
		
		fldChoiceElements.add(fldLeft);
		fldChoiceElements.add(fldRight);
		add(fldChoiceElements);
		
		_txtRadicado = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtRadicado.setLabel("Radicado: ");
		add(_txtRadicado);
		
		_txtRadicadoUnico = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtRadicadoUnico.setLabel("Radicado unico: ");
		add(_txtRadicadoUnico);
		
		_chEstado = new ObjectChoiceField();
		_chEstado.setLabel("Estado:");
		Object[] initialChoiceEstado = {"Nuevo"};
		_chEstado.setChoices(initialChoiceEstado);
		add(_chEstado);
		
		_chCategoria = new ObjectChoiceField();
		_chCategoria.setLabel("Categoria:");
		Object[] initialChoiceCategoria = {"Nueva"};
		_chCategoria.setChoices(initialChoiceCategoria);
		add(_chCategoria);
		
		_chPrioridad = new NumericChoiceField("Prioridad", 0, 10, 1);
		add(_chPrioridad);
		
		_dtFecha = new DateField("Fecha de creación: ", System.currentTimeMillis(),DateField.DATE);
		_dtFecha.setEditable(true);
		add(_dtFecha);
		
		_txtNotas = new BasicEditField();
		_txtNotas.setLabel("Notas: ");
		add(_txtNotas);
		
		addMenuItem(menuGuardar);
	}
	
	private final MenuItem menuGuardar = new MenuItem("Guardar", 0, 0) {

		public void run() {
			// TODO Auto-generated method stub
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	};
	
    private FieldChangeListener listenerBtnDemandante = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoPersonasController demandantes = new ListadoPersonasController(1);
    		UiApplication.getUiApplication().pushModalScreen(demandantes.getScreen());
    		try{
    			_demandante = demandantes.getSelected();
    			_btnDemandante.setLabel(_demandante.getNombre());
    		}catch(NullPointerException e){
    			if(_demandante == null)
    				Dialog.alert(_demandante.toString()+"Debe seleccionar un demandante");
    		}
    	}
    };
    
    private FieldChangeListener listenerBtnDemandado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoPersonasController demandados = new ListadoPersonasController(2);
    		UiApplication.getUiApplication().pushModalScreen(demandados.getScreen());
    		try{
    			_demandado = demandados.getSelected();
    			_btnDemandado.setLabel(_demandado.getNombre());
    		}catch(NullPointerException e){
    			if(_demandado == null)
    				Dialog.alert("Debe seleccionar un demandado");
    		}
    		finally{
    			demandados = null;
    		}
    	}
    };
    
    private FieldChangeListener listenerBtnJuzgado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoJuzgadosController juzgados = new ListadoJuzgadosController();
    		UiApplication.getUiApplication().pushModalScreen(juzgados.getScreen());
    		try{
    			_juzgado = juzgados.getSelected();
    			_btnJuzgado.setLabel(_juzgado.getNombre());
    		}catch(NullPointerException e){
    			if(_juzgado == null)
    				Dialog.alert("Debe seleccionar un juzgado");
    		}finally{
    			juzgados = null;
    		}
    	}
    };
    
    public Persona getDemandante() {
    	return _demandante;
    }
    
    public Persona getDemandado() {
    	return _demandado;
    }
    
    public Juzgado getJuzgado() {
    	return _juzgado;
    }
    
    public String getRadicado() {
    	return _txtRadicado.getText();
    }
    
    public String getRadicadoUnico() {
    	return _txtRadicadoUnico.getText();
    }
    
    public String getEstado() {
    	return (String)_chEstado.getChoice(_chEstado.getSelectedIndex());
    }
    
    public String getCategoria() {
    	return (String)_chEstado.getChoice(_chEstado.getSelectedIndex());
    }
    
    public short getPrioridad() {
    	return Short.parseShort((String) _chEstado.getChoice(_chEstado.getSelectedIndex()));
    }
    
    public String getNotas() {
    	return _txtNotas.getText();
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