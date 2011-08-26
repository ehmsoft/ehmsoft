package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Juzgado;

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
			if(context == _screen.GUARDAR) {
				actualizarJuzgado();
			} else if(context == _screen.CERRAR) {
				cerrarPantalla();
			} else if(context == _screen.ELIMINAR) {
				eliminarJuzgado();
			}
		}
	};

	public VerJuzgadoScreen getScreen() {
		return _screen;
	}
	
	public Juzgado getJuzgado() {
		return _juzgado;
	}

	private void actualizarJuzgado() {
		if (_screen.getNombre().length() == 0) {
			_screen.alert("El campo Nombre es obligatorio");
		} else {
			Juzgado juzgado = new Juzgado(_screen.getNombre(),
					_screen.getCiudad(), _screen.getDireccion(),
					_screen.getTelefono(), _screen.getTipo(),
					_juzgado.getId_juzgado());
			if (!_juzgado.equals(juzgado)) {
				try {
					new Persistence().actualizarJuzgado(juzgado);
				} catch (NullPointerException e) {
					_screen.alert(Util.noSDString());
					System.exit(0);
				} catch (Exception e) {
					_screen.alert(e.toString());
				}
				_juzgado = juzgado;
			}
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
	
	private void eliminarJuzgado() {
		Object[] ask = {"Aceptar", "Cancelar"};
		int sel = _screen.ask(ask, Util.delBDJuzgado(), 1);
		if(sel == 0) {
			try {
				new Persistence().borrarJuzgado(_juzgado);
			} catch(NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			_juzgado = null;
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
	
	private void cerrarPantalla() {
		Juzgado juzgado = new Juzgado(_screen.getNombre(),
				_screen.getCiudad(), _screen.getDireccion(),
				_screen.getTelefono(), _screen.getTipo(),
				_juzgado.getId_juzgado());
		if (!juzgado.equals(_juzgado)) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarJuzgado();
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(_screen);
			}
		} else {
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
}
