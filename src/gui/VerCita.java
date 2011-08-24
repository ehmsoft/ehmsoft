package gui;

import java.util.Date;

import javax.microedition.pim.PIMItem;

import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import core.CalendarManager;

public class VerCita {
	private String _uid;
	private VerCitaPopUp _screen;

	public VerCita(String uid) throws Exception {
		_uid = uid;
		int alarma = 0;

		BlackBerryEvent event = CalendarManager.consultarCita(_uid);
		Date date = new Date(event.getDate(BlackBerryEvent.START,
				PIMItem.ATTR_NONE));
		String desc = event.getString(BlackBerryEvent.SUMMARY,
				PIMItem.ATTR_NONE);
		if (event.countValues(BlackBerryEvent.ALARM) > 0) {
			alarma = event.getInt(BlackBerryEvent.ALARM, PIMItem.ATTR_NONE);
		}
		Object[] choices = {"Minutos", "Horas" , "Días"};
		
		_screen = new VerCitaPopUp();
		_screen.setDescripcion(desc);
		_screen.setFecha(date);
		_screen.setChoices(choices);
		if(alarma > 0) {
			if(alarma >= 86400) {
				alarma /= 86400;
				_screen.setIntervalo(2);
			} else if(alarma >= 3600) {
				alarma /= 3600;
				_screen.setIntervalo(1);
			} else if(alarma >= 60) {
				alarma /= 60;
				_screen.setIntervalo(0);
			}
			_screen.setDuracion(String.valueOf(alarma));
		}
	}
	
	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == _screen.GUARDAR) {
				guardarCita();
			} else if (context == _screen.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	public VerCitaPopUp getScreen() {
		return _screen;
	}
	
	public String getUid() {
		return _uid;
	}

	private void guardarCita() {
		if (_screen.hasAlarma()) {
			int intervalo = _screen.getIntervalo();
			int duracion = _screen.getDuracion();
			if (intervalo == 2) {
				duracion *= 86400;
			} else if (intervalo == 1) {
				duracion *= 3600;
			} else if (intervalo == 0) {
				duracion *= 60;
			}
			try {
				CalendarManager.actualizarCita(_uid, _screen.getFecha(),
						_screen.getDescripcion(), duracion);
			} catch (Exception e) {
				try {
					_uid = CalendarManager.agregarCita(_screen.getFecha(),
							_screen.getDescripcion(), duracion);
				} catch (Exception e1) {
					_screen.alert(e.toString());
				}
			}
		} else {
			try {
				CalendarManager.actualizarCita(_uid, _screen.getFecha(), _screen.getDescripcion());
			} catch(Exception e) {
				try {
					_uid = CalendarManager.agregarCita(_screen.getFecha(),
							_screen.getDescripcion());
				} catch (Exception e1) {
					_screen.alert(e.toString());
				}
			}
		}
	}
	
	private void cerrarPantalla() {
		UiApplication.getUiApplication().popScreen(_screen);
	}
	
}
