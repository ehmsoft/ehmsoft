package gui;

import net.rim.device.api.system.DeviceInfo;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import persistence.Persistence;

public class Llaves {
	private String _llave;
	private LlavesScreen _screen;

	public Llaves() {
		_screen = new LlavesScreen();
		_screen.setChangeListener(listener);
		try {
			_llave = new Persistence().consultarPreferencia(998);
			_screen.setLlave(_llave);
			verificarLlaves();
		} catch (NullPointerException npe) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert("No se ha podido verificar la llave de licencia. Error Desconocido");
		}
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.GUARDAR) {
				guardarLlave();
				Util.popScreen(_screen);
			}
		}
	};

	public boolean verificarLlaves() {
		int pin = DeviceInfo.getDeviceId();
		pin += 0x65686d73;
		if (_llave.equals(Integer.toOctalString(pin))) {
			_screen.setEstadoActivacion("Activado");
			return true;
		} else {
			_screen.setEstadoActivacion("Desactivado");
			return false;
		}
	}

	public LlavesScreen getScreen() {
		return _screen;
	}

	private void guardarLlave() {
		try {
			_llave = _screen.getLlave();
			if (verificarLlaves()) {
				new Persistence().actualizarPreferencia(998, _llave);
			} else{
				Util.alert("Lo sentimos, su llave no coincide. Verifique que la digitó correctamente. Si el problema persiste póngase en contacto con soporte técnico: soporte@ehmsoft.com");
			}
		} catch (NullPointerException npe) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert("No se ha podido verificar la llave de licencia. Error Desconocido");
		}
	}
}
