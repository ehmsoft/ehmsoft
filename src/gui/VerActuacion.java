package gui;

import java.util.Calendar;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;

public class VerActuacion {
	private VerActuacionScreen _screen;
	private Actuacion _actuacion;

	public VerActuacion(Actuacion actuacion) {
		_screen = new VerActuacionScreen(actuacion);
		_actuacion = actuacion;
	}

	public VerActuacionScreen getScreen() {
		return _screen;
	}

	public void actualizarActuacion() {
		if (_screen.isGuardado()) {
			try {
				Persistence persistence = new Persistence();
				boolean cambio = false;

				Calendar f1 = _actuacion.getFecha();
				Calendar f2 = _screen.getFecha();

				Calendar fP1 = _actuacion.getFechaProxima();
				Calendar fP2 = _screen.getFechaProxima();

				if (!_actuacion.getJuzgado().getId_juzgado()
						.equals(_screen.getJuzgado().getId_juzgado()))
					cambio = true;
				if ((f1.get(Calendar.YEAR) != f2.get(Calendar.YEAR))
						|| (f1.get(Calendar.MONTH) != f2.get(Calendar.MONTH))
						|| (f1.get(Calendar.DAY_OF_MONTH) != f2
								.get(Calendar.DAY_OF_MONTH)))
					cambio = true;
				if ((fP1.get(Calendar.YEAR) != fP2.get(Calendar.YEAR))
						|| (fP1.get(Calendar.MONTH) != fP2.get(Calendar.MONTH))
						|| (fP1.get(Calendar.DAY_OF_MONTH) != fP2
								.get(Calendar.DAY_OF_MONTH)))
					cambio = true;
				if (!_actuacion.getDescripcion().equals(
						_screen.getDescripcion()))
					cambio = true;

				if (cambio)
					_actuacion = new Actuacion(_screen.getJuzgado(),
							_screen.getFecha(), _screen.getFechaProxima(),
							_screen.getDescripcion(),
							_actuacion.getId_actuacion()) {
						public String toString() {
							return this.getDescripcion();
						}
					};
				persistence.actualizarActuacion(_actuacion);
			} catch (Exception e) {
				Dialog.alert("actualizarActuacion -> " + e.toString());
			}
		}
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}
}