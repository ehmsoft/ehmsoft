package gui.Ver;

import gui.Cita;
import gui.Util;
import gui.Nuevos.NuevaCita;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Actuacion;
import core.Juzgado;

public class VerActuacion {
	private VerActuacionScreen _screen;
	private Actuacion _actuacion;
	private Cita _cita;
	private Juzgado _juzgado;
	private Juzgado _juzgadoVacio;

	public VerActuacion(Actuacion actuacion) {
		_actuacion = actuacion;
		_juzgado = _actuacion.getJuzgado();
		_cita = new Cita(_actuacion.getUid());
		_screen = new VerActuacionScreen();
		_screen.setChangeListener(listener);
		_screen.setJuzgado(_juzgado.getNombre());
		_screen.setDescripcion(_actuacion.getDescripcion());
		_screen.setFecha(_actuacion.getFecha().getTime());
		_screen.setFechaProxima(_actuacion.getFechaProxima().getTime());
		if (_cita.exist()) {
			_screen.setClock();
			if (_cita.hasAlarma()) {
				_screen.setBell();
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
			_screen.alert("El campo Descripci�n es obligatorio");
		} else if (_juzgado == null || _juzgado.getId_juzgado().equals("1")) {
			Util.alert("El juzgado es obligatorio");
		} else if (_screen.isDirty()) {
			_cita.actualizarCita();
			_actuacion.setJuzgado(_juzgado);
			_actuacion.setFecha(_screen.getFecha());
			_actuacion.setFechaProxima(_screen.getFechaProxima());
			_actuacion.setDescripcion(_screen.getDescripcion());
			_actuacion.setUid(_cita.getUid());
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					try {
						new Persistence().actualizarActuacion(_actuacion);
						borrarCitaActuacion();
					} catch (NullPointerException e) {
						Util.noSd();
					} catch (DatabaseException e) {
						if (e.getMessage().equalsIgnoreCase(
								": constraint failed")) {
							Util.alert("Esta actuaci�n ya existe");
							_actuacion = Util.consultarActuacion(_actuacion
									.getId_actuacion());
						}
					} catch (Exception e) {
						Util.alert(e.toString());
					} finally {
						Util.popScreen(_screen);
					}
				}
			});
		} else {
			Util.popScreen(_screen);
		}
	}

	private void borrarCitaActuacion() {
		if (!_cita.exist() && _actuacion.getUid() != null) {
			_actuacion.setUid(_cita.getUid());
			try {
				new Persistence().actualizarActuacion(_actuacion);
			} catch (NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
		}
	}

	private void verCita() {
		VerCita v = new VerCita(_cita);
		Util.pushModalScreen(v.getScreen());
		if (v.isDirty()) {
			if (_cita.hasAlarma()) {
				_screen.setBell();
			} else {
				_screen.removeBell();
			}
			_screen.setDirty(true);
		}
	}

	private void verJuzgado() {
		if (!_juzgado.getId_juzgado().equals("1") && _juzgado != null) {
			Juzgado juzgado = Util.verJuzgado(_juzgado);
			if (juzgado != null) {
				if (!_juzgado.equals(juzgado)) {
					_juzgado = juzgado;
					_screen.setJuzgado(_juzgado.getNombre());
					_screen.setDirty(true);
				}
			} else {
				if (_juzgadoVacio == null) {
					_juzgadoVacio = Util.consultarJuzgadoVacio();
				}
				_juzgado = null;
				_screen.setJuzgado(_juzgadoVacio.getNombre());
				_screen.setDirty(true);
			}
		} else {
			addJuzgado();
		}
	}

	private void addCita() {
		if(_cita.isEliminada()) {
			_cita.actualizarCita();
		}
		NuevaCita n = new NuevaCita(_screen.getDescripcion(), _screen
				.getFechaProxima().getTime());
		Util.pushModalScreen(n.getScreen());
		Cita cita = n.getCita();
		if (cita != null && cita.getDescripcion().length() != 0) {
			_cita = cita;
			_screen.setClock();
			if (_cita.hasAlarma()) {
				_screen.setBell();
			}
			_screen.setDirty(true);
		}
	}

	private void addJuzgado() {
		Juzgado juzgado = Util.listadoJuzgados(true, 0);
		if (juzgado != null) {
			_juzgado = juzgado;
			_screen.setJuzgado(_juzgado.getNombre());
			_screen.setDirty(true);
		}
	}

	private void eliminarActuacion() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDActuacion(), 1);
		if (sel == 0) {
			try {
				new Persistence().borrarActuacion(_actuacion);
			} catch (NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
			_actuacion = null;
			_cita.eliminarCita();
			Util.popScreen(_screen);
		}
	}

	private void eliminarCita() {
		Object[] ask = { "S�", "No" };
		int sel = _screen.ask(ask, "�Desea eliminar la cita?", 1);
		if (sel == 0) {
			_cita.markEliminar();
			_screen.removeClock();
			_screen.setDirty(true);
		}
	}

	private void cerrarPantalla() {
		if (_screen.isDirty()) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarActuacion();
			} else if (sel == 1) {
				borrarCitaActuacion();
				if (_cita.exist() && _actuacion.getUid() == null) {
					_cita.eliminarCita();
				}
				Util.popScreen(_screen);
			}
		} else {
			borrarCitaActuacion();
			Util.popScreen(_screen);
		}
	}
}