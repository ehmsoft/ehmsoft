package ehmsoft;
import core.Proceso;
import core.Persona;
import core.Actuacion;
import core.CampoPersonalizado;
import core.Juzgado;
import gui.NuevoProceso;

public class NuevoProcesoController {
	
	private Persona demandante;
	private Persona demandado;
	private Actuacion actuacion;
	private CampoPersonalizado campo;
	private Juzgado juzgado;
	private NuevoProceso screen;
	public NuevoProcesoController()
	{
		this.screen = new NuevoProceso();
	}
	
	public NuevoProceso getScreen() {
		return screen;
	}
}
	