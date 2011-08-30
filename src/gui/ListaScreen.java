package gui;

import java.util.Vector;

import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;

public abstract class ListaScreen extends MainScreen implements
		ListadosInterface {

	protected ListaListas _lista;
	private HorizontalFieldManager _searchField = new HorizontalFieldManager(
			USE_ALL_WIDTH);

	public ListaScreen() {
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		add(_searchField);
	}

	protected boolean navigationClick(int status, int time) {
		fieldChangeNotify(Util.CLICK);
		return true;
	}

	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.getSelectedElement())) {
			menu.add(menuVer);
			menu.add(menuDelete);
		}
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.VER_ELEMENTO);
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR);
		}
	};

	public void setSearchField() {
		_searchField.add(_lista.getKeywordField());
	}

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	public void addElement(Object element) {
		_lista.insert(element);
	}

	public void addElement(Object element, int index) {
		_lista.insert(index, element);
		_lista.setSelectedIndex(index);
	}

	public void loadFrom(Vector collection) {
		_lista.loadFrom(collection);
	}

	public void remove(Object element) {
		_lista.remove(element);
	}

	public void remove(int index) {
		_lista.remove(index);
	}

	public void replace(Object old, Object nw) {
		_lista.update(old, nw);
	}

	public Object getSelected() {
		return _lista.getSelectedElement();
	}

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}
