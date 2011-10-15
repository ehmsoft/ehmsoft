package gui.Ver;

import gui.Util;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Juzgado;
import core.PhoneManager;

public class VerJuzgado {
	private VerJuzgadoScreen _screen;
	private Juzgado _juzgado;

	public VerJuzgado(Juzgado juzgado) {
		_juzgado = juzgado;
		_screen = new VerJuzgadoScreen();
		_screen.setNombre(_juzgado.getNombre());
		_screen.setCiudad(_juzgado.getCiudad());
		_screen.setDireccion(_juzgado.getDireccion());
		_screen.setTelefono(_juzgado.getTelefono());
		_screen.setTipo(_juzgado.getTipo());
		_screen.setChangeListener(listener);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				actualizarJuzgado();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.ELIMINAR) {
				eliminarJuzgado();
			} else if (context == Util.LLAMAR) {
				llamar();
			}
		}
	};

	private void llamar() {
		if (!_screen.getTelefono().equals("")) {
			PhoneManager.call(_screen.getTelefono());
		} else {
			Util.alert("Este juzgado no tiene teléfono registrado");
		}
	}

	public VerJuzgadoScreen getScreen() {
		return _screen;
	}

	public Juzgado getJuzgado() {
		return _juzgado;
	}

	private void actualizarJuzgado() {
		if (_screen.getNombre().length() == 0) {
			_screen.alert("El campo Nombre es obligatorio");
		} else if (_screen.isDirty()) {
			_juzgado.setNombre(_screen.getNombre());
			_juzgado.setCiudad(_screen.getCiudad());
			_juzgado.setDireccion(_screen.getDireccion());
			_juzgado.setTelefono(_screen.getTelefono());
			_juzgado.setTipo(_screen.getTipo());
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					try {
						new Persistence().actualizarJuzgado(_juzgado);
					} catch (NullPointerException e) {
						Util.noSd();
					} catch (DatabaseException e) {
						if (e.getMessage().equalsIgnoreCase(
								": constraint failed")) {
							Util.alert("Este juzgado ya existe");
							_juzgado = Util.consultarJuzgado(_juzgado
									.getId_juzgado());
						}
					} catch (Exception e) {
						Util.alert(e.toString());
					} finally {
						Util.popScreen(_screen);
					}
				}
			});
		}
	}

	private void eliminarJuzgado() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDJuzgado(), 1);
		if (sel == 0) {
			try {
				new Persistence().borrarJuzgado(_juzgado);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			_juzgado = null;
			Util.popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		Juzgado juzgado = new Juzgado(_screen.getNombre(), _screen.getCiudad(),
				_screen.getDireccion(), _screen.getTelefono(),
				_screen.getTipo(), _juzgado.getId_juzgado());
		if (!juzgado.equals(_juzgado)) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarJuzgado();
			} else if (sel == 1) {
				Util.popScreen(_screen);
			}
		} else {
			Util.popScreen(_screen);
		}
	}
}
