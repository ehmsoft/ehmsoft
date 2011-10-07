package gui.Nuevos;

import gui.Cita;
import gui.Util;
import gui.Listados.ListadoJuzgados;
import gui.Ver.VerCita;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Actuacion;
import core.Juzgado;
import core.Proceso;

public class NuevaActuacion {
	private Actuacion _actuacion;
	private Cita _cita;
	private Juzgado _juzgado;
	private Juzgado _juzgadoVacio;
	private NuevaActuacionScreen _screen;
	private Proceso _proceso;

	public NuevaActuacion(Proceso proceso) {
		_juzgadoVacio = Util.consultarJuzgadoVacio();
		_cita = new Cita();
		_proceso = proceso;
		_screen = new NuevaActuacionScreen();
		_screen.setJuzgado(_juzgadoVacio.getNombre());
		_screen.setChangeListener(listener);
	}

	public NuevaActuacion() {
		this(null);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				guardarActuacion();
			} else if (context == Util.ADD_JUZGADO) {
				addJuzgado();
			} else if (context == Util.ADD_CITA) {
				addCita();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.VER_CITA) {
				verCita();
			} else if (context == Util.ADD_CITA) {
				addCita();
			} else if (context == Util.ELIMINAR_CITA) {
				eliminarCita();
			}
		}
	};

	/**
	 * @return La pantalla asociada al objeto NuevaActuacion
	 */
	public NuevaActuacionScreen getScreen() {
		return _screen;
	}

	public Actuacion getActuacion() {
		return _actuacion;
	}

	private void guardarActuacion() {
		if (_screen.getDescripcion().length() == 0) {
			_screen.alert("El campo Descripción es obligatorio");
		} else if (_juzgado == null) {
			Util.alert("El juzgado es obligatorio");
		} else {
			final Actuacion actuacion = new Actuacion(_juzgado, _screen.getFecha(),
					_screen.getFechaProxima(), _screen.getDescripcion(), null,
					_cita.getUid());
			if (_proceso != null) {
				_screen.setStatus(Util.getWaitStatus());
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					public void run() {
						try {
							new Persistence().guardarActuacion(actuacion,
									_proceso.getId_proceso());
							_actuacion = actuacion;
						} catch (NullPointerException e) {
							Util.noSd();
						} catch (DatabaseException e) {
							if(e.getMessage().equalsIgnoreCase("constraint failed")) {
								Util.alert("Esta actuación ya existe");
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
			borrarCitaActuacion();
		}
	}

	private void addJuzgado() {
		ListadoJuzgados l = new ListadoJuzgados(true);
		UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		Juzgado juzgado = l.getSelected();
		if (juzgado != null) {
			_juzgado = juzgado;
			_screen.setJuzgado(juzgado.getNombre());
		}
	}

	private void borrarCitaActuacion() {
		if (_actuacion != null) {
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
	}

	private void addCita() {
		NuevaCita n = new NuevaCita(_screen.getDescripcion(), _screen
				.getFechaProxima().getTime());
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		_cita = n.getCita();
		if (_cita.exist()) {
			_screen.setClock();
			if (_cita.hasAlarma()) {
				_screen.setBell();
			}
		}
	}

	private void verCita() {
		if (_cita.exist()) {
			VerCita v = new VerCita(_cita);
			UiApplication.getUiApplication().pushModalScreen(v.getScreen());
			if (_cita.hasAlarma()) {
				_screen.setBell();
			} else {
				_screen.removeBell();
			}
		}
	}

	private void eliminarCita() {
		_cita.eliminarCita();
		_screen.removeClock();
	}

	private void cerrarPantalla() {
		if (_screen.getDescripcion().length() != 0) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				guardarActuacion();
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