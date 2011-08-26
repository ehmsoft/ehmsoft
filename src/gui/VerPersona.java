package gui;

import persistence.Persistence;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import core.Persona;

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
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == _screen.GUARDAR) {
				actualizarPersona();
			} else if(context == _screen.ELIMINAR) {
				eliminarPersona();
			} else if(context == _screen.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	public VerPersonaScreen getScreen() {
		return _screen;
	}
	
	public Persona getPersona() {
		return _persona;
	}

	private void actualizarPersona() {
		if (_screen.getNombre().length() == 0) {
			_screen.alert("El campo Nombre es obligatorio");
		} else {
			Persona persona = new Persona(_persona.getTipo(), _screen.getCedula(),
					_screen.getNombre(), _screen.getTelefono(),
					_screen.getDireccion(), _screen.getCorreo(),
					_screen.getNotas(), _persona.getId_persona());
			if (!persona.equals(_persona)) {
				try {
					new Persistence().actualizarPersona(persona);
				} catch (NullPointerException e) {
					_screen.alert(Util.noSDString());
					System.exit(0);
				} catch (Exception e) {
					_screen.alert(e.toString());
				}
				_persona = persona;
			}
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
	
	private void eliminarPersona() {
		Object[] ask = {"Aceptar", "Cancelar"};
		int sel = _screen.ask(ask, Util.delBDPersona(), 1);
		if(sel == 0) {
			try {
				new Persistence().borrarPersona(_persona);
			} catch(NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			_persona = null;
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		Persona persona = new Persona(_persona.getTipo(), _screen.getCedula(),
				_screen.getNombre(), _screen.getTelefono(),
				_screen.getDireccion(), _screen.getCorreo(),
				_screen.getNotas(), _persona.getId_persona());
		if (!persona.equals(_persona)) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarPersona();
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(_screen);
			}
		} else {
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
}
