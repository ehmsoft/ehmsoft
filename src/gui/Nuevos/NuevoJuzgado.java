package gui.Nuevos;

import gui.Util;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.Juzgado;
import core.Preferencias;

public class NuevoJuzgado {
	private Juzgado _juzgado;
	private NuevoJuzgadoScreen _screen;

	public NuevoJuzgado() {
		_screen = new NuevoJuzgadoScreen();
		_screen.setChangeListener(listener);
		if(Preferencias.isMostrarTitulosPantallas()) {
			_screen.setTitle("Crear juzgado");
		}
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == _screen.GUARDAR) {
				guardarJuzgado();
			} else if (context == _screen.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	/**
	 * @return La pantalla asociada al objeto
	 */
	public NuevoJuzgadoScreen getScreen() {
		return _screen;
	}

	/**
	 * @return El nuevo Juzgado, s� este no ha sido creado y aguardado con
	 *         guardarJuzgado(); se llama dicho m�todo
	 */
	public Juzgado getJuzgado() {
		return _juzgado;
	}

	/**
	 * Crea el nuevo Juzgado en base a los datos capturados desde la pantalla y
	 * guardandolo en la base de datos
	 */
	private void guardarJuzgado() {

		if (_screen.getNombre().equals("")) {
			_screen.showAlert("El campo Nombre es obligatorio");
		} else if (_screen.getTelefono().equals("")) {
			Object[] ask = { "Guardar", "Cancelar" };
			int sel = _screen.ask(ask,
					"El campo Tel�fono se considera importante", 1);
			if (sel == 0) {
				guardar();
			}
		} else {
			guardar();
		}
	}

	private void guardar() {
		_screen.setStatus(Util.getWaitLabel());
		UiApplication.getUiApplication().invokeLater(new Runnable() {

			public void run() {
				_juzgado = new Juzgado(_screen.getNombre(),
						_screen.getCiudad(), _screen.getDireccion(), _screen
								.getTelefono(), _screen.getTipo());
				try {
					new Persistence().guardarJuzgado(_juzgado);
				} catch (NullPointerException e) {
					Util.noSd();
				} catch (DatabaseException e) {
					if (e.getMessage().equalsIgnoreCase(": constraint failed")) {
						Util.alert("Este juzgado ya existe");
						_juzgado = null;
					}
				} catch (Exception e) {
					Util.alert(e.toString());
				} finally {
					Util.popScreen(_screen);
				}
			}
		});
	}

	private void cerrarPantalla() {
		if (_screen.isDirty()) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				guardarJuzgado();
			} else if (sel == 1) {
				Util.popScreen(_screen);
			}
		} else {
			Util.popScreen(_screen);
		}
	}
}
