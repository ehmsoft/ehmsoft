package gui.Nuevos;

import java.util.Enumeration;

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
		if(Util.TEMP == null) {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				
				public void run() {
					Util.TEMP = Util.consultarCategorias();
				}
			});
		}
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
			if (!exist(_categoria)) {
				Util.pushWaitScreen();
				UiApplication.getUiApplication().invokeLater(new Runnable() {

					public void run() {
						try {
							new Persistence().guardarCategoria(_categoria);
						} catch (NullPointerException e) {
							Util.noSd();
							Util.TEMP = null;
						} catch (Exception e) {
							Util.alert(e.toString());
						} finally {
							Util.popScreen((Screen) _screen);
							Util.popWaitScreen();
						}
					}
				});
			}
			Util.TEMP = null;
		}
	}
	
	private boolean exist(Categoria element) {
		boolean exist = false;
		Enumeration e = Util.TEMP.elements();
		while(e.hasMoreElements()) {
			if(e.nextElement().equals(element)) {
				exist = true;
				break;
			}
		}
		return exist;
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
