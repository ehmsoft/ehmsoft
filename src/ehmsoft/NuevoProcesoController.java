package ehmsoft;
import net.rim.device.api.ui.container.MainScreen;
import core.Proceso;
import core.Persona;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Juzgado;
import gui.NuevoProceso;

public class NuevoProcesoController extends MainScreen{
	
	public Main theApp;
	private Persona demandante;
	private Persona demandado;
	private Actuacion actuacion;
	private CampoPersonalizado campo;
	private Juzgado juzgado;
	private NuevoProceso screen;
	public NuevoProcesoController(Main theApp)
	{
		this.theApp = theApp;
		this.screen = new NuevoProceso(this);
		this.theApp.pushScreen(screen);		
	}
	
	public Persona selectDemandante()
	{
		NuevoProcesoController This = this;
		ListadoPersonasController listaDemandantes = new ListadoPersonasController(theApp,This,(short)1);
		Persona demandante = new Persona((short)1, "", "", "", "", "", "");
		synchronized (listaDemandantes) {
			try {
				This.wait();
				demandante = listaDemandantes.getSelected();
				listaDemandantes = null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return demandante;
	}
}
	