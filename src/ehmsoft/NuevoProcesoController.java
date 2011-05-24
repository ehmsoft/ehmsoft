package ehmsoft;
import core.Proceso;
import gui.NuevoProceso;
import persistence.Persistence;

public class NuevoProcesoController {
	
	private Proceso _proceso;
	private NuevoProceso _screen;
	
	public NuevoProcesoController()
	{
		_screen = new NuevoProceso();
	}
	
	public Proceso getProceso() {
		return _proceso;
	}
	
	public NuevoProceso getScreen() {
		return _screen;
	}
	
	public void guardarProceso() {
		Persistence guardado = new Persistence();
		_proceso = new Proceso(_screen.getDemandante(),_screen.getDemandado(),_screen.getFecha(),_screen.getJuzgado(),
				_screen.getRadicado(),_screen.getRadicadoUnico(),null,_screen.getEstado(),_screen.getCategoria(),
				null,_screen.getNotas(),null,_screen.getPrioridad());
		guardado.guardarProceso(_proceso);
	}
}
	