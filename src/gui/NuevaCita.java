package gui;

import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import core.CalendarManager;

public class NuevaCita {

	private NuevaCitaPopUp _screen;
	private String _uid;

	public NuevaCita(String descripcion, Date fecha) {
		_screen = new NuevaCitaPopUp();
		_screen.setDescripcion(descripcion);
		_screen.setFecha(fecha);
		_screen.setChangeListener(listener);
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

	public NuevaCitaPopUp getScreen() {
		return _screen;
	}

	public String getUid() {
		return _uid;
	}

	public boolean hasAlarma() {
		return _screen.hasAlarma();
	}

	private void guardarCita() {
		if (_screen.hasAlarma()) {
			int alarma = _screen.getDuracion();
			String intervalo = _screen.getIntervalo();
			if (intervalo.equalsIgnoreCase("días")) {
				alarma *= 86400;
			} else if (intervalo.equalsIgnoreCase("horas")) {
				alarma *= 3600;
			} else if (intervalo.equalsIgnoreCase("minutos")) {
				alarma *= 60;
			}
			try {
				_uid = CalendarManager.agregarCita(_screen.getFecha(),
						_screen.getDescripcion(), alarma);
			} catch (Exception e) {
				_screen.setAlarma(false);
				_screen.alert("No se pudo guardar la cita en el calendario");
			}
			UiApplication.getUiApplication().popScreen(_screen);
		} else {
			try {
				_uid = CalendarManager.agregarCita(_screen.getFecha(),
						_screen.getDescripcion());
			} catch (Exception e) {
				_screen.alert("No se pudo guardar la cita en el calendario");

			}
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		UiApplication.getUiApplication().popScreen(_screen);
	}
}
