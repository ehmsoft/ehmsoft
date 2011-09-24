package gui.Nuevos;

import gui.Cita;
import gui.Util;

import java.util.Date;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class NuevaCita {

	private NuevaCitaPopUp _screen;
	private Cita _cita;

	public NuevaCita(String descripcion, Date fecha) {
		_screen = new NuevaCitaPopUp();
		_screen.setDescripcion(descripcion);
		_screen.setFecha(fecha);
		_screen.setChangeListener(listener);
		_cita = new Cita();
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

	public Cita getCita() {
		return _cita;
	}

	private void guardarCita() {
		if (_screen.getDescripcion().equals("")) {
			Util.alert("La descripción está vacía!. Por favor ingrese algo");
		} else {
			if (_screen.hasAlarma()) {
				_cita.setAlarma(_screen.getAlarma());
			} else {
				_cita.setAlarma(0);
			}
			_cita.setDescripcion(_screen.getDescripcion());
			_cita.setFecha(_screen.getFecha());
			_cita.guardarCita();
			Util.popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		Util.popScreen(_screen);
	}
}
