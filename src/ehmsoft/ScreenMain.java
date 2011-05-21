/*package ehmsoft;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.MainScreen;

*//**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 *//*
public final class screenMain extends MainScreen
{
    *//**
     * Creates a new screenMain object
     *//*
	Main theApp;
	ButtonField btnListadoProcesos;
	
    public screenMain(Main theApp)
    {
    	this.theApp = theApp;
        // Set the displayed title of the screen       
        setTitle("Software Abogados v 0.1.0");
        *//**
         * Esta parte es tentativa, solamente la uso para tener acceso directo a las pantallas
         *//*
        btnListadoProcesos = new ButtonField("Listado de procesos");
        btnListadoProcesos.setChangeListener(listenerListadoProcesos);
        add(btnListadoProcesos);
    }
    
    private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		theApp.controller.lanzarListadoProcesos();
    		//theApp.pushScreen(new gui.ListadoProcesos(theApp));
    		
    	}
    };
}
*/

package ehmsoft;

import core.Persona;
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
    }
    
    private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		theApp.lanzarListadoProcesos();
    		//theApp.pushScreen(new gui.ListadoProcesos(theApp));
    		
    	}
    };
    
    private FieldChangeListener listenerNuevoProceso = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		NuevoProcesoController proceso = new NuevoProcesoController();
    		UiApplication.getUiApplication().pushModalScreen(proceso.getScreen());
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
    
    public boolean onClose(){
    	System.exit(0);
    	return true;
    }
}
