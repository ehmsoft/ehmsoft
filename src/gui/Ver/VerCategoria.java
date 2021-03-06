package gui.Ver;

import gui.Util;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Categoria;
import core.Preferencias;

public class VerCategoria {
	private VerCategoriaScreen _screen;
	private Categoria _categoria;

	public VerCategoria(Categoria categoria) {
		_categoria = categoria;
		_screen = new VerCategoriaScreen();
		_screen.setDescripcion(_categoria.getDescripcion());
		_screen.setChangeListener(listener);
		if(Preferencias.isMostrarTitulosPantallas()) {
			_screen.setTitle("Categor�a");
		}
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				actualizarCategoria();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			} else if (context == Util.ELIMINAR) {
				eliminarCategoria();
			}
		}
	};

	public VerCategoriaScreen getScreen() {
		return _screen;
	}

	public Categoria getCategoria() {
		return _categoria;
	}

	private void actualizarCategoria() {
		if (_screen.getDescripcion().length() == 0) {
			_screen.alert("El campo Descripcion es obligatorio");
		} else if (_screen.isDirty()) {
			_categoria.setDescripcion(_screen.getDescripcion());
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					try {
						new Persistence().actualizarCategoria(_categoria);
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

	private void eliminarCategoria() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDCategoria(), 1);
		if (sel == 0) {
			try {
				new Persistence().borrarCategoria(_categoria);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			if(Preferencias.getUltimaCategoria() != null && Preferencias.getUltimaCategoria().getId_categoria().equals(_categoria.getId_categoria())) {
				Preferencias.setUltimaCategoria(null);
			}
			_categoria = null;
			Util.popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		Categoria categoria = new Categoria(_categoria.getId_categoria(),
				_screen.getDescripcion());
		if (!categoria.equals(_categoria)) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarCategoria();
			} else if (sel == 1) {
				Util.popScreen(_screen);
			}
		} else {
			Util.popScreen(_screen);
		}
	}
}