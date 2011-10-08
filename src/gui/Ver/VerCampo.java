package gui.Ver;

import gui.Util;
import net.rim.device.api.database.DatabaseException;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import persistence.Persistence;
import core.CampoPersonalizado;

public class VerCampo {
	private VerCampoScreen _screen;
	private CampoPersonalizado _campo;

	public VerCampo(CampoPersonalizado campo) {
		_campo = campo;
		_screen = new VerCampoScreen();
		_screen.setNombre(_campo.getNombre());
		_screen.setObligatorio(_campo.isObligatorio().booleanValue());
		if (_campo.getLongitudMax() == 0) {
			_screen.setLongitudMax("");
		} else {
			_screen.setLongitudMax(String.valueOf(_campo.getLongitudMax()));
		}
		if (_campo.getLongitudMin() == 0) {

		} else {
			_screen.setLongitudMin(String.valueOf(_campo.getLongitudMin()));
		}
		_screen.setChangeListener(listener);
	}

	FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				actualizarCampo();
			} else if (context == Util.ELIMINAR) {
				eliminarCampo();
			} else if (context == Util.CERRAR) {
				cerrarPantalla();
			}
		}
	};

	public VerCampoScreen getScreen() {
		return _screen;
	}

	public CampoPersonalizado getCampo() {
		return _campo;
	}

	private void actualizarCampo() {
		if (_screen.getNombre().length() == 0) {
			_screen.alert("El campo Nombre es obligatorio");
		} else if (_screen.getLongitudMax() < _screen.getLongitudMin()
				&& _screen.getLongitudMax() != 0) {
			_screen.alert("La longitud máxima no puede ser menor que la loongitud minima");
		} else if (_screen.getLongitudMax() == _screen.getLongitudMin()
				&& _screen.getLongitudMax() != 0) {
			_screen.alert("La longitud máxima no puede ser igual que la loongitud minima");
		} else if (_screen.isDirty()) {
			_campo.setNombre(_screen.getNombre());
			_campo.setObligatorio(_screen.isObligatorio());
			_campo.setLongitudMax(_screen.getLongitudMax());
			_campo.setLongitudMin(_screen.getLongitudMin());
			_screen.setStatus(Util.getWaitLabel());
			UiApplication.getUiApplication().invokeLater(new Runnable() {

				public void run() {
					try {
						new Persistence().actualizarAtributo(_campo);
					} catch (NullPointerException e) {
						Util.noSd();
					} catch (DatabaseException e) {
						if (e.getMessage().equalsIgnoreCase(": constraint failed")) {
							//TODO Restaurar campo inicial de la BD
						}
					} catch (Exception e) {
						Util.alert(e.toString());
					} finally {
						UiApplication.getUiApplication().popScreen(_screen);
					}
				}
			});
		}
	}

	private void eliminarCampo() {
		Object[] ask = { "Aceptar", "Cancelar" };
		int sel = _screen.ask(ask, Util.delBDCampo(), 1);
		if (sel == 0) {
			try {
				new Persistence().borrarAtributo(_campo);
			} catch (NullPointerException e) {
				_screen.alert(Util.noSDString());
				System.exit(0);
			} catch (Exception e) {
				_screen.alert(e.toString());
			}
			_campo = null;
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}

	private void cerrarPantalla() {
		if (_screen.isDirty()) {
			Object[] ask = { "Guardar", "Descartar", "Cancelar" };
			int sel = _screen.ask(ask, "Se han detectado cambios", 2);
			if (sel == 0) {
				actualizarCampo();
			} else if (sel == 1) {
				UiApplication.getUiApplication().popScreen(_screen);
			}
		} else {
			UiApplication.getUiApplication().popScreen(_screen);
		}
	}
}
