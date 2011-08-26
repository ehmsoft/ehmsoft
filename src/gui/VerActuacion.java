package gui;

import persistence.Persistence;
import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import core.Actuacion;
import core.CalendarManager;
import core.Juzgado;

public class VerActuacion {
	private VerActuacionScreen _screen;
	private Actuacion _actuacion;
	private String _uid;
	private Juzgado _juzgado;
	private Juzgado _emptyJuzgado;

	public VerActuacion(Actuacion actuacion) {
		_actuacion = actuacion;
		_uid = _actuacion.getUid();
		_juzgado = _actuacion.getJuzgado();
		_screen = new VerActuacionScreen();
		_screen.setChangeListener(listener);
		_screen.setJuzgado(_juzgado.getNombre());
		_screen.setDescripcion(_actuacion.getDescripcion());
		_screen.setFecha(_actuacion.getFecha().getTime());
		_screen.setFechaProxima(_actuacion.getFechaProxima().getTime());
		try {
			_emptyJuzgado = new Persistence().consultarJuzgado("1");
		} catch (NullPointerException e) {
			_screen.alert(Util.noSDString());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		if (_actuacion.getUid() != null) {
			try {
				BlackBerryEvent e = CalendarManager.consultarCita(_actuacion
						.getUid());
				if (e == null) {
					eliminarCita();
				} else {
					_screen.setClock();
					if (e.countValues(BlackBerryEvent.ALARM) > 0) {
						_screen.setBell();
					}
				}
			} catch (Exception e) {
				eliminarCita();
			}
		}
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				actualizarActuacion();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.VER_CITA) {
				verCita();
			} else if (context == Util.ADD_CITA) {
				addCita();
			} else if (context == Util.ELIMINAR_CITA) {
				eliminarCita();
			} else if (context == Util.VER_JUZGADO) {
				verJuzgado();
			} else if (context == Util.ADD_JUZGADO) {
				addJuzgado();
			} else if (context == Util.ELIMINAR) {
				eliminarActuacion();
			}
		}
	};

	public VerActuacionScreen getScreen() {
		return _screen;
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}

	private void actualizarActuacion() {
		if (_screen.getDescripcion().length() == 0) {
			_screen.alert("El campo Descripción es obligatorio");
		} else {
			Actuacion actuacion = new Actuacion(_juzgado, _screen.getFecha(),
					_screen.getFechaProxima(), _screen.getDescripcion(),
					_actuacion.getId_actuacion(), _uid);
			if (!_actuacion.equals(actuacion)) {
				try {
					new Persistence().actualizarActuacion(actuacion);
				} catch (NullPointerException e) {
					_screen.alert(Util.noSDString());
					System.exit(0);
				} catch (Exception e) {
					_screen.alert(e.toString());
				}
				_actuacion = actuacion;
			}
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}

	private void verCita() {
		if(_uid != null) {
			try {
				VerCita v = new VerCita(_uid);
				UiApplication.getUiApplication().pushModalScreen(v.getScreen());
			} catch (Exception e) {
				eliminarCita();
			}
		}
	}

	private void verJuzgado() {
		if (!_juzgado.getId_juzgado().equals("1")) {
			Juzgado juzgado = Util.verJuzgado(_juzgado);
			if (juzgado != null) {
				_juzgado = juzgado;
				_screen.setJuzgado(_juzgado.getNombre());
			} else {
				_juzgado = _emptyJuzgado;
				_screen.setJuzgado(_juzgado.getNombre());
			}
		} else {
			addJuzgado();
		}
	}

	private void addCita() {
		NuevaCita n = new NuevaCita(_screen.getDescripcion(), _screen
				.getFechaProxima().getTime());
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		_uid = n.getUid();
		if (_uid != null) {
			_screen.setClock();
			if (n.hasAlarma()) {
				_screen.setBell();
			}
		}
	}

	private void addJuzgado() {
		Juzgado juzgado = Util.listadoJuzgados(true);
		if (juzgado != null) {
			_juzgado = juzgado;
			_screen.setJuzgado(_juzgado.getNombre());
		}
	}

	private void eliminarActuacion() {
		Object[] ask = {"Aceptar", "Cancelar"};
		int sel = _screen.ask(ask, "¿Esta seguro que desea eliminar la actuación?", 1);
		if(sel == 0) {
			try {
				new Persistence().borrarActuacion(_actuacion);
			} catch(NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
			_actuacion = null;
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}

	private void eliminarCita() {
		if(_uid != null) {
			try {
				CalendarManager.borrarCita(_uid);
			} catch (Exception e) {				
			}
		}
		_uid = null;
	}

	private void cerrarPantalla() {
		Actuacion actuacion = new Actuacion(_juzgado, _screen.getFecha(),
				_screen.getFechaProxima(), _screen.getDescripcion(),
				_actuacion.getId_actuacion(), _uid);
		if (!actuacion.equals(_actuacion)) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarActuacion();
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(_screen);
			}
		} else {
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
}