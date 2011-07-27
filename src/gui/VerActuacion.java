package gui;

import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;

public class VerActuacion {
	private VerActuacionScreen _screen;
	private Actuacion _actuacion;

	public VerActuacion(Actuacion actuacion) {
		_screen = new VerActuacionScreen(actuacion);
		_actuacion = actuacion;
		if(_actuacion.getUid() != null) {
			_screen.setCita();
		}
	}

	public VerActuacionScreen getScreen() {
		return _screen;
	}

	public void actualizarActuacion() throws Exception {
		if (_screen.isGuardado()) {
			try {
				Persistence persistence = new Persistence();

				if (_screen.isCambiado()) {
					_actuacion = new Actuacion(_screen.getJuzgado(),
							_screen.getFecha(), _screen.getFechaProxima(),
							_screen.getDescripcion(),
							_actuacion.getId_actuacion(), _screen.getUid());
					persistence.actualizarActuacion(_actuacion);
				}
			} catch (Exception e) {
				Dialog.alert("actualizarActuacion -> " + e.toString());
			}
		}
		else if (_screen.isEliminado()) {
			Persistence p;
			try {
				p = new Persistence();
				p.borrarActuacion(_actuacion);
			} catch (Exception e) {
				Dialog.alert(e.toString());
			}
			throw new Exception("Se está eliminando la actuación con id: "
					+ _actuacion.getId_actuacion());
		} else if(_actuacion.getUid() != null && _screen.getUid() == null) {
			Persistence p;
			try {
				p = new Persistence();
				_actuacion.setUid(null);
				p.actualizarActuacion(_actuacion);
			} catch(Exception e) {
				Dialog.alert(e.toString());
			}
		}
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}
}