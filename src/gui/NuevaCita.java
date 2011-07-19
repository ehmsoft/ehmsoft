package gui;

import java.util.Date;

import net.rim.device.api.ui.component.Dialog;

import core.CalendarManager;

public class NuevaCita {
	
	private NuevaCitaPopUp _screen;
	private String _uid;
	
	public NuevaCita(String descripcion, Date fecha) {
		_screen = new NuevaCitaPopUp(descripcion, fecha);
	}
	
	public NuevaCitaPopUp getScreen() {
		return _screen;
	}
	
	public boolean isGuardado() {
		return _screen.isGuardado();
	}
	
	public String getUid() {
		return _uid;
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
					_uid = CalendarManager.agregarCita(_screen.getFecha(), _screen.getDescripcion(), duracion);
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			} else {
				try {
					_uid = CalendarManager.agregarCita(_screen.getFecha(), _screen.getDescripcion());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
			}
		}
	}
}
