package gui.Listados;

import gui.ListaPopUp;
import gui.Util;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Keypad;

public class ListadoPersonasPopUp extends ListaPopUp {

	public ListadoPersonasPopUp() {
		super();
		_lista = new ListadoPersonasLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista);
		addKeyListener(listener);
	}

	private KeyListener listener = new KeyListener() {

		public boolean keyUp(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyDown(int keycode, int time) {
			if (Keypad.key(keycode) == Keypad.KEY_SEND) {
				fieldChangeNotify(Util.LLAMAR);
				return true;
			} else {
				return false;
			}
		}

		public boolean keyChar(char key, int status, int time) {
			return false;
		}
	};
}
