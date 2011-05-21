/*package ehmsoft;

import net.rim.device.api.ui.UiApplication;

*//**
 * This class extends the UiApplication class, providing a
 * graphical user interface.
 *//*
public class Main extends UiApplication
{
    *//**
     * Entry point for application
     * @param args Command line arguments (not used)
     *//*
	Controller controller;
    public static void main(String[] args)
    {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        Main theApp = new Main();       
        theApp.enterEventDispatcher();
    }
    

    *//**
     * Creates a new Main object
     *//*
    public Main()
    {        
        // Push a screen onto the UI stack for rendering.
    	controller = new Controller();
        pushScreen(new screenMain());
    }    
}*/

package ehmsoft;

import gui.Proceso;
import net.rim.device.api.ui.UiApplication;

/**
 * This class extends the UiApplication class, providing a
 * graphical user interface.
 */
public class Main extends UiApplication
{
    /**
     * Entry point for application
     * @param args Command line arguments (not used)
     */ 
    public static void main(String[] args)
    {
        // Create a new instance of the application and make the currently
        // running thread the application's event dispatch thread.
        Main theApp = new Main();       
        theApp.enterEventDispatcher();
    }
    

    /**
     * Creates a new Main object
     */
    public Main()
    {        
        // Push a screen onto the UI stack for rendering.
        pushScreen(new ScreenMain(this));
    }
    
    public void lanzarListadoProcesos()
    {
		Proceso proceso1 = new Proceso("Harold","Mario","001","Juzgado 1");
		Proceso proceso2 = new Proceso("Esteban","Santa","002","Juzgado 2");
		
		Object[] objetos = new Object[]{proceso1,proceso2};
    	pushScreen(new gui.ListadoProcesos(this,objetos));
    }
    
    public void lanzarVerProceso(Proceso proceso)
    {
    	pushScreen(new gui.VerProceso(this,proceso));
    }
    
    public void lanzarNuevoProceso()
    {
    	new NuevoProcesoController(this);
    }
}
