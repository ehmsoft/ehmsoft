package gui.Nuevos;

import gui.Util;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.CampoPersonalizado;

public class NuevoCampo {
	private CampoPersonalizado _campo;
	private NuevoCampoScreen _screen;

	private boolean _saveInBd = true;

	public NuevoCampo(boolean saveInBd) {
		_saveInBd = saveInBd;
		_screen = new NuevoCampoScreen();
		_screen.setChangeListener(listener);
	}

	public NuevoCampo() {
		this(true);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == _screen.GUARDAR) {
				guardarCampo();
			} else if (context == _screen.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	public NuevoCampoScreen getScreen() {
		return _screen;
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}

	private void guardarCampo() {
		int lonMax = _screen.getLongMax();
		int lonMin = _screen.getLongMin();
		String nombre = _screen.getNombre();
		boolean isObligatorio = _screen.isObligatorio();

		if (nombre.length() == 0) {
			_screen.alert("El campo Nombre es obligatorio");
		} else if (lonMax < lonMin && lonMax != 0) {
			_screen.alert("La longitud máxima no puede ser menor que la longitud mínima");
		} else if (lonMax == lonMin && lonMax != 0) {
			_screen.alert("La longitud máxima no puede ser igual que la longitud mínima");
		} else {
			_campo = new CampoPersonalizado(nombre, null, new Boolean(
					isObligatorio), lonMax, lonMin);
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					if (_saveInBd) {
						try {
							new Persistence().guardarAtributo(_campo);
						} catch (NullPointerException e) {
							Util.noSd();
						} catch (DatabaseException e) {
							if(e.getMessage().equalsIgnoreCase(": constraint failed")) {
								Util.alert("El campo personalizado ya existe");
								_campo = null;
							}
						} catch (Exception e) {
							Util.alert(e.toString());
						} finally {
							Util.popScreen(_screen);
						}
					} else {
						Util.popScreen(_screen);
					}
				}
			});
		}
	}

	private void cerrarPantalla() {
		if (_screen.getNombre().length() != 0) {
			Object[] ask = { "Guardar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 1);
			if (sel == 0) {
				guardarCampo();
			} else {
				UiApplication.getUiApplication().popScreen(_screen);
			}
		} else {
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
}
