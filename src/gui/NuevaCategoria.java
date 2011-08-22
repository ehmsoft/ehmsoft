package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Categoria;

public class NuevaCategoria {

	private Categoria _categoria;
	private NuevaCategoriaScreen _screen;
	private NuevaCategoriaPopUp _screenPp;

	public NuevaCategoria(boolean popup) {
		if(popup) {
			_screenPp = new NuevaCategoriaPopUp();
		} else {
			_screen = new NuevaCategoriaScreen();
		}
	}
	
	FieldChangeListener listener = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if(context == NuevaCategoriaScreen.GUARDAR) {
				guardarCategoria();
			} else if(context == NuevaCategoriaScreen.CERRAR) {
				cerrarPantalla();
			}
		}
	};
	
	public NuevaCategoria() {
		this(false);
	}

	public Screen getScreen() {
		if(_screen != null) {
			return _screen;
		} else {
			return _screenPp;
		}
	}

	public Categoria getCategoria() {
		return _categoria;
	}

	public void guardarCategoria() {
		String descripcion;
		if(_screen != null) {
			descripcion = _screen.getDescripcion();
		} else {
			descripcion = _screenPp.getDescripcion();
		}
		
		if (descripcion.length() == 0) {
			if (_screen != null) {
				_screen.alert("El campo Descripción es obligatorio");
			} else {
				_screenPp.alert("El campo Descripción es obligatorio");
			}
		} else {
			_categoria = new Categoria(descripcion);
			try {
				new Persistence().guardarCategoria(_categoria);
				if (_screen != null) {
					UiApplication.getUiApplication().popScreen(_screen);
				} else {
					UiApplication.getUiApplication().popScreen(_screenPp);
				}
			} catch (NullPointerException e) {
				if (_screen != null) {
					_screen.alert("Tarjeta SD no presente, la aplicación se cerrará, verifique e inicie nuevamente");
				} else {
					_screenPp
							.alert("Tarjeta SD no presente, la aplicación se cerrará, verifique e inicie nuevamente");
				}
				System.exit(0);
			} catch (Exception e) {
				if (_screen != null) {
					_screen.alert(e.toString());
				} else {
					_screenPp.alert(e.toString());
				}
			}
		}
	}
	
	private void cerrarPantalla() {
		if (_screen != null) {
			if (_screen.getDescripcion().length() != 0) {
				Object[] ask = { "Guardar", "Descartar", "Cancelar" };
				int sel = _screen.ask(ask, "Se han detectado cambios", 2);
				if (sel == 0) {
					guardarCategoria();
				} else if (sel == 1) {
					UiApplication.getUiApplication().popScreen(_screen);
				}
			}
		} else {
			if (_screenPp.getDescripcion().length() != 0) {
				Object[] ask = { "Guardar", "Descartar", "Cancelar" };
				int sel = _screen.ask(ask, "Se han detectado cambios", 2);
				if (sel == 0) {
					guardarCategoria();
				} else if (sel == 1) {
					UiApplication.getUiApplication().popScreen(_screenPp);
				}
			}
		}
	}
}
