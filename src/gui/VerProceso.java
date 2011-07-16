package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Proceso;

public class VerProceso {
	private VerProcesoScreen _screen;
	private Proceso _proceso;

	public VerProceso(Proceso proceso) {
		_screen = new VerProcesoScreen(proceso);
		_proceso = proceso;
	}

	public VerProcesoScreen getScreen() {
		return _screen;
	}

	public void actualizarProceso() {
		if (_screen.isGuardado()) {
			try {
				Persistence persistence = new Persistence();
				_proceso = new Proceso(_proceso.getId_proceso(),
						_screen.getDemandante(), _screen.getDemandado(),
						_screen.getFecha(), _screen.getJuzgado(),
						_screen.getRadicado(), _screen.getRadicadoUnico(),
						_screen.getActuaciones(), _screen.getEstado(),
						_screen.getCategoria(), _screen.getTipo(),
						_screen.getNotas(), _screen.getCampos(),
						_screen.getPrioridad());
				persistence.actualizarProceso(_proceso);
			} catch (Exception e) {
				Dialog.alert("actualizarProceso -> " + e.toString());
			}
		}
	}

	public Proceso getProceso() {
		return _proceso;
	}
}