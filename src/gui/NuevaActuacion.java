package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Actuacion;
import core.CalendarManager;
import core.Juzgado;
import core.Proceso;

public class NuevaActuacion {
	private Actuacion _actuacion;
	private String _uid;
	private Juzgado _juzgado;
	private NuevaActuacionScreen _screen;
	private Proceso _proceso;

	public NuevaActuacion(Proceso proceso) {
		try {
			_juzgado = new Persistence().consultarJuzgado("1");
		} catch (NullPointerException e) {
			_screen.alert(Util.noSDString());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert(e.toString());
		}
		_proceso = proceso;
		_screen = new NuevaActuacionScreen();
		_screen.setJuzgado(_juzgado.getNombre());
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
			}
		}
	};

	public void setProceso(Proceso proceso) {
		_proceso = proceso;
	}

	/**
	 * @return La pantalla asociada al objeto NuevaActuacion
	 */
	public NuevaActuacionScreen getScreen() {
		return _screen;
	}

	/**
	 * @return La nueva actuacion creada, sí esta no ha sido guardada
	 *         previamente con guardarActuacion(); se invoca dicho metodo
	 */
	public Actuacion getActuacion() {
		return _actuacion;
	}

	private void guardarActuacion() {
		if (_screen.getDescripcion().length() == 0) {
			_screen.alert("El campo Descripción es obligatorio");
		} else {
			_actuacion = new Actuacion(_juzgado, _screen.getFecha(),
					_screen.getFechaProxima(), _screen.getDescripcion(), null,
					_uid);
			if (_proceso != null) {
				try {
					new Persistence().guardarActuacion(_actuacion,
							_proceso.getId_proceso());
				} catch (NullPointerException e) {
					_screen.alert(Util.noSDString());
					System.exit(0);
				} catch (Exception e) {
					_screen.alert(e.toString());
				}
				UiApplication.getUiApplication().popScreen(_screen);
			} else {
				UiApplication.getUiApplication().popScreen(_screen);
			}
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

	private void addCita() {
		if (_screen.hasAlarma()) {
			NuevaCita n = new NuevaCita(_screen.getDescripcion(), _screen
					.getFechaProxima().getTime());
			UiApplication.getUiApplication().pushModalScreen(n.getScreen());
			_uid = n.getUid();
			if (_uid == null) {
				_screen.setChecked(false);
			}
		} else {
			if (_uid != null) {
				try {
					CalendarManager.borrarCita(_uid);
				} catch (NullPointerException e) {
				} catch (Exception e) {
					_screen.alert(e.toString());
				}
			}
		}
	}

	private void cerrarPantalla() {
		if (_screen.getDescripcion().length() != 0) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				guardarActuacion();
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(_screen);
			}
		} else {
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
}