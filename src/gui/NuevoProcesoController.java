package gui;
import core.Proceso;
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
		try {
			guardado.guardarProceso(_proceso);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
	