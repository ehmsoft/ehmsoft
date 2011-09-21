package gui;

import java.util.Vector;

import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public abstract class ListaPopUp extends PopupScreen implements
		ListadosInterface {
	protected ListaListas _lista;
	private HorizontalFieldManager _searchField = new HorizontalFieldManager(
			USE_ALL_WIDTH);
	private VerticalFieldManager _title;

	public ListaPopUp() {
		super(new VerticalFieldManager(VERTICAL_SCROLL | VERTICAL_SCROLLBAR));
		_title = new VerticalFieldManager();
		add(_title);
		add(_searchField);
	}
	
	public void setTitle(String text) {
		_title.add(new LabelField(text, FIELD_HCENTER));
		_title.add(new SeparatorField());
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

	protected boolean navigationClick(int status, int time) {
		fieldChangeNotify(Util.CLICK);
		return true;
	}

	public void addElement(Object juzgado) {
		_lista.insert(juzgado);
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
