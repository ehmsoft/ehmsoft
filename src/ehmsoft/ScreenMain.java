package ehmsoft;

import core.CampoPersonalizado;
import gui.ListadoJuzgadosController;
import gui.ListadoPersonasController;
import gui.ListadoProcesosController;
import gui.NuevaPersonaController;
import gui.NuevoCampoPersonalizadoController;
import gui.NuevoProcesoController;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.MainScreen;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public final class ScreenMain extends MainScreen
{
    /**
     * Creates a new screenMain object
     */
	Main theApp;
	ButtonField btnListadoProcesos;
	ButtonField btnNuevoProceso;
	ButtonField btnNuevoDemandante;
	ButtonField btnNuevoDemandado;
	ButtonField btnListadoDemandantes;
	ButtonField btnListadoJuzgados;
	ButtonField btnNuevoCampoPersonalizado;
	
    public ScreenMain(Main theApp)
    {       
    	this.theApp = theApp;
        // Set the displayed title of the screen       
        setTitle("Software abogados v0.1.0");
        btnListadoProcesos = new ButtonField("Listado de procesos");
        btnListadoProcesos.setChangeListener(listenerListadoProcesos);
        add(btnListadoProcesos);
        
        btnNuevoProceso = new ButtonField("Nuevo Proceso");
        btnNuevoProceso.setChangeListener(listenerNuevoProceso);
        add(btnNuevoProceso);
        
        btnNuevoDemandante = new ButtonField("Nuevo Demandante");
        btnNuevoDemandante.setChangeListener(listenerNuevoDemandante);
        add(btnNuevoDemandante);
        
        btnNuevoDemandado = new ButtonField("Nuevo Demandado");
        btnNuevoDemandado.setChangeListener(listenerNuevoDemandado);
        add(btnNuevoDemandado);
        
        btnListadoDemandantes = new ButtonField("Listado Demandantes");
        btnListadoDemandantes.setChangeListener(listenerListadoDemandantes);
        add(btnListadoDemandantes);
        
        btnListadoJuzgados = new ButtonField("Listado Juzgados");
        btnListadoJuzgados.setChangeListener(listenerListadoJuzgados);
        add(btnListadoJuzgados);
        
        btnNuevoCampoPersonalizado = new ButtonField("Nuevo campo personalizado");
        btnNuevoCampoPersonalizado.setChangeListener(listenerNuevoCampoPersonalizado);
        add(btnNuevoCampoPersonalizado);
    }
    
    private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoProcesosController listado= new ListadoProcesosController();
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getDemandante().getNombre());
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getDemandante().getNombre());
    	}
    };
    
    private FieldChangeListener listenerNuevoProceso = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		NuevoProcesoController proceso = new NuevoProcesoController();
    		UiApplication.getUiApplication().pushModalScreen(proceso.getScreen());
    		proceso.guardarProceso();
    		Dialog.alert(((CampoPersonalizado) proceso.getProceso().getCampos().elementAt(1)).getValor());
    		proceso = null;
    	}
    };
    
    private FieldChangeListener listenerNuevoDemandante = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		NuevaPersonaController demandante= new NuevaPersonaController(1);
    		UiApplication.getUiApplication().pushModalScreen(demandante.getScreen());
    		demandante.guardarPersona();
    		Dialog.alert(demandante.getPersona().getNombre());
    		demandante = null;
    	}
    };
    
    private FieldChangeListener listenerNuevoDemandado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		NuevaPersonaController demandado= new NuevaPersonaController(2);
    		UiApplication.getUiApplication().pushModalScreen(demandado.getScreen());
    		demandado.guardarPersona();
    		Dialog.alert(demandado.getPersona().getNombre());
    		demandado = null;
    	}
    };
    
    private FieldChangeListener listenerListadoDemandantes = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoPersonasController listado = new ListadoPersonasController(1);
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getNombre());
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getNombre());
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getNombre());    		
    	}
    };
    
    private FieldChangeListener listenerListadoJuzgados = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		ListadoJuzgadosController listado = new ListadoJuzgadosController();
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getNombre());
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getNombre());
    		UiApplication.getUiApplication().pushModalScreen(listado.getScreen());
    		Dialog.alert(listado.getSelected().getNombre());    		
    	}
    };
    
    private FieldChangeListener listenerNuevoCampoPersonalizado = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		NuevoCampoPersonalizadoController controller = new NuevoCampoPersonalizadoController();
    		UiApplication.getUiApplication().pushModalScreen(controller.getScreen());
    		controller.guardarCampo();
    		Dialog.alert(controller.getCampo().getNombre());
    	}
    };
    
    public boolean onClose(){
    	System.exit(0);
    	return true;
    }
}
