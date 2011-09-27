package gui.Listados;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Menu;
import gui.ListaScreen;
import gui.Util;

public class ListadoPlantillasScreen extends ListaScreen {

	public ListadoPlantillasScreen() {
		super();
		_lista = new ListadoPlantillasLista() {
			protected boolean navigationClick(int status, int time) {
				click();
				return true;
			}
		};
		add(_lista, false);
	}

	protected void makeMenu(Menu menu, int instance) {
		super.makeMenu(menu, instance);
		if (!String.class.isInstance(_lista.getSelectedElement())) {
			menu.add(menuCrearProceso);
		}
	}

	private MenuItem menuCrearProceso = new MenuItem("Crear proceso", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.NEW_PROCESO);
		}
	};
}
