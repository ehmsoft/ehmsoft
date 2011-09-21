package gui.Nuevos;

import gui.Util;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Screen;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Categoria;

public class NuevaCategoria {

	private Categoria _categoria;
	private NuevaCategoriaInterface _screen;

	public NuevaCategoria(boolean popup) {
		if (popup) {
			_screen = new NuevaCategoriaPopUp();
		} else {
			_screen = new NuevaCategoriaScreen();
		}
		((Screen) _screen).setChangeListener(listener);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				guardarCategoria();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	public NuevaCategoria() {
		this(false);
	}

	public Screen getScreen() {
		return (Screen) _screen;
	}

	public Categoria getCategoria() {
		return _categoria;
	}

	public void guardarCategoria() {
		String descripcion = _screen.getDescripcion();

		if (descripcion.length() == 0) {
			_screen.alert("No puede crearse una categoría vacía");
		} else {
			_categoria = new Categoria(descripcion);
			Util.pushWaitScreen();
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					try {
						new Persistence().guardarCategoria(_categoria);
						UiApplication.getUiApplication().popScreen(getScreen());
					} catch (NullPointerException e) {
						_screen.alert(Util.noSDString());
						System.exit(0);
					} catch (Exception e) {
						_screen.alert(e.toString());
					}
					Util.popWaitScreen();
				}
			});
		}
	}

	private void cerrarPantalla() {
		if (_screen.getDescripcion().length() != 0) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				guardarCategoria();
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		} else {
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}
}
