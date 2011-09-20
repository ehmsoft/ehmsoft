package gui.Listados;

import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Keypad;
import gui.ListaPopUp;
import gui.Util;

public class ListadoJuzgadosPopUp extends ListaPopUp {

	public ListadoJuzgadosPopUp() {
		super();
		_lista = new ListadoJuzgadosLista();
		add(_lista);
		addKeyListener(listener);
	}

	private KeyListener listener = new KeyListener() {

		public boolean keyUp(int keycode, int time) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			// TODO Auto-generated method stub
			return false;
		}

		public boolean keyRepeat(int keycode, int time) {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			return false;
		}
	};
}
