package gui;

import java.util.Calendar;
import java.util.Date;

import javax.microedition.pim.PIMItem;

import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.device.api.ui.component.Dialog;
import persistence.Persistence;
import core.Actuacion;
import core.CalendarManager;

public class VerActuacion {
	private VerActuacionScreen _screen;
	private Actuacion _actuacion;

	public VerActuacion(Actuacion actuacion) {
		_screen = new VerActuacionScreen(actuacion);
		_actuacion = actuacion;
		setCita();
	}
	
	private void setCita() {
		try {
			BlackBerryEvent event = CalendarManager.consultarCita(_actuacion.getUid());
			Date date = new Date(event.getDate(BlackBerryEvent.START, PIMItem.ATTR_NONE));
			int alarma = event.getInt(BlackBerryEvent.ALARM, PIMItem.ATTR_NONE);
			String desc = event.getString(BlackBerryEvent.SUMMARY, PIMItem.ATTR_NONE);
			_screen.setCita(desc ,date, alarma);
		} catch(Exception e) {
			Dialog.alert(e.toString());
		}
	}

	public VerActuacionScreen getScreen() {
		return _screen;
	}

	public void actualizarActuacion() throws Exception {
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
		}
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}
}