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

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
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
        
    }
    
    private FieldChangeListener listenerListadoProcesos = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		theApp.lanzarListadoProcesos();
    		//theApp.pushScreen(new gui.ListadoProcesos(theApp));
    		
    	}
    };
    
    private FieldChangeListener listenerNuevoProceso = new FieldChangeListener() {
    	public void fieldChanged(Field field, int context) {
    		theApp.lanzarNuevoProceso();
    		//theApp.pushScreen(new gui.ListadoProcesos(theApp));
    		
    	}
    };
    
    public boolean onClose(){
    	System.exit(0);
    	return true;
    }
}
