package gui;

import java.util.Calendar;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Proceso;

public class VerProcesoController {
	private VerProceso _screen;
	private Proceso _proceso;

	public VerProcesoController(Proceso proceso) {
		_screen = new VerProceso(proceso);
		_proceso = proceso;
	}

	public VerProceso getScreen() {
		return _screen;
	}

	public void actualizarProceso() {
		try {
			Persistence persistence = new Persistence();
			boolean cambio = false;

			Calendar f1 = _proceso.getFecha();
			Calendar f2 = _screen.getFecha();

			if (_proceso.getDemandante().getId_persona()
					.equals(_screen.getDemandante().getId_persona()))
				cambio = true;
			if (_proceso.getDemandado().getId_persona()
					.equals(_screen.getDemandado().getId_persona()))
				cambio = true;
			if (_proceso.getJuzgado().getId_juzgado()
					.equals(_screen.getJuzgado().getId_juzgado()))
				cambio = true;
			if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
					|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
					|| (f1.get(Calendar.DAY_OF_MONTH) != f2
							.get(Calendar.DAY_OF_MONTH)))
				cambio = true;
			if (_proceso.getRadicado().equals(_screen.getRadicado()))
				cambio = true;
			if (_proceso.getRadicadoUnico().equals(_screen.getRadicadoUnico()))
				cambio = true;
			if (_proceso.getActuaciones().equals(_screen.getActuaciones()))
				cambio = true;
			if (_proceso.getEstado().equals(_screen.getEstado()))
				cambio = true;
			if (_proceso.getCategoria().equals(_screen.getCategoria()))
				cambio = true;
			if (_proceso.getTipo().equals(_screen.getTipo()))
				cambio = true;
			if (_proceso.getNotas().equals(_screen.getNotas()))
				cambio = true;
			if (_proceso.getPrioridad() != _screen.getPrioridad())
				cambio = true;
			if (_proceso.getCampos().equals(_screen.getCampos()))
				cambio = true;

			if (cambio) {
				_proceso = new Proceso(_proceso.getId_proceso(),
						_screen.getDemandante(), _screen.getDemandado(),
						_screen.getFecha(), _screen.getJuzgado(),
						_screen.getRadicado(), _screen.getRadicadoUnico(),
						_screen.getActuaciones(), _screen.getEstado(),
						_screen.getCategoria(), _screen.getTipo(),
						_screen.getNotas(), _screen.getCampos(),
						_screen.getPrioridad());
				persistence.actualizarProceso(_proceso);
			}
		} catch (Exception e) {
			Dialog.alert("actualizarProceso -> " + e.toString());
		}
	}

	public Proceso getProceso() {
		return _proceso;
	}
}