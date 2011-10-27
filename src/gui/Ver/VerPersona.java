package gui.Ver;

import gui.Util;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Persona;
import core.PhoneManager;
import core.Preferencias;

public class VerPersona {
	private VerPersonaScreen _screen;
	private Persona _persona;

	public VerPersona(Persona persona) {
		_persona = persona;
		_screen = new VerPersonaScreen();
		_screen.setNombre(_persona.getNombre());
		_screen.setCedula(_persona.getId());
		_screen.setTelefono(_persona.getTelefono());
		_screen.setDireccion(_persona.getDireccion());
		_screen.setCorreo(_persona.getCorreo());
		_screen.setNotas(_persona.getNotas());
		_screen.setChangeListener(listener);
		if(Preferencias.isMostrarTitulosPantallas()) {
			if(_persona.getTipo() == 1) {
				_screen.setTitle("Demandante");
			} else {
				_screen.setTitle("Demandado");
			}
		}
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				actualizarPersona();
			} else if (context == Util.ELIMINAR) {
				eliminarPersona();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.LLAMAR) {
				llamar();
			}
		}
	};

	private void llamar() {
		if (!_screen.getTelefono().equals("")) {
			PhoneManager.call(_screen.getTelefono());
		} else {
			Util.alert("Esta persona no tiene teléfono registrado");
		}
	}

	public VerPersonaScreen getScreen() {
		return _screen;
	}

	public Persona getPersona() {
		return _persona;
	}

	private void actualizarPersona() {
		if (_screen.getNombre().length() == 0) {
			_screen.alert("El campo Nombre es obligatorio");
		} else if (_screen.isDirty()) {
			_persona.setId(_screen.getCedula());
			_persona.setNombre(_screen.getNombre());
			_persona.setTelefono(_screen.getTelefono());
			_persona.setDireccion(_screen.getDireccion());
			_persona.setCorreo(_screen.getCorreo());
			_persona.setNotas(_screen.getNotas());
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					try {
						new Persistence().actualizarPersona(_persona);
					} catch (DatabaseException e) {
						if (e.getMessage().equalsIgnoreCase(
								": constraint failed")) {
							Util.alert("Esta persona ya existe");
							_persona = Util.consultarPersona(
									_persona.getTipo(),
									_persona.getId_persona());
						}
					} catch (NullPointerException e) {
						Util.noSd();
					} catch (Exception e) {
						Util.alert(e.toString());
					} finally {
						Util.popScreen(_screen);
					}
				}
			});
		}
	}

	private void eliminarPersona() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDPersona(), 1);
		if (sel == 0) {
			try {
				new Persistence().borrarPersona(_persona);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			_persona = null;
			Util.popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		if (_screen.isDirty()) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarPersona();
			} else if (sel == 1) {
				Util.popScreen(_screen);
			}
		} else {
			Util.popScreen(_screen);
		}
	}
}
