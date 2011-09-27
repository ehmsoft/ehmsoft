package gui;

import java.util.Vector;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public abstract class ListaScreen extends FondoNormal implements
		ListadosInterface {

	protected ListaListas _lista;
	private HorizontalFieldManager _searchField = new HorizontalFieldManager(
			USE_ALL_WIDTH);

	public ListaScreen() {
		super();
		add(_searchField, false);
	}

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuNuevo);
		menu.add(menuCerrar);
		if (!String.class.isInstance(_lista.getSelectedElement())) {
			menu.add(menuVer);
			menu.add(menuDelete);
		}
	}

	private MenuItem menuNuevo = new MenuItem("Nuevo", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.NEW);
		}
	};

	private MenuItem menuCerrar = new MenuItem("Salir de Aplicación",
			1000000000, 3) {

		public void run() {
			System.exit(0);
		}
	};
	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			fieldChangeNotify(Util.VER_ELEMENTO);
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 1, 1) {

		public void run() {
			fieldChangeNotify(Util.ELIMINAR);
		}
	};

	protected void click() {
		fieldChangeNotify(Util.CLICK);
	}

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
