package gui;

import java.util.Date;

import net.rim.device.api.ui.component.Dialog;

import core.CalendarManager;

public class NuevaCita {
	
	private NuevaCitaScreen _screen;
	
	public NuevaCita(String descripcion, Date fecha) {
		_screen = new NuevaCitaScreen(descripcion, fecha);
	}
	
	public NuevaCitaScreen getScreen() {
		return _screen;
	}
	
	public void guardarCita() {
		if(_screen.isGuardado()) {
			if(_screen.isAlarma()) {
				int duracion = _screen.getDuracion();
				String intervalo = _screen.getIntervalo();
				if(intervalo.equals("Días")) {
					duracion = duracion * 86400;
				}
				else if(intervalo.equals("Horas")) {
					duracion = duracion * 3600;
				}
				else if(intervalo.equals("Minutos")) {
					duracion = duracion * 60;
				}
				
				try {
					CalendarManager.agregarCita(_screen.getFecha(), _screen.getDescripcion(), duracion);
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			} else {
				try {
					CalendarManager.agregarCita(_screen.getFecha(), _screen.getDescripcion());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}
	}
}
