package gui;

import java.util.Vector;

import core.Preferencias;

import net.rim.device.api.ui.Field;
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
	private HorizontalFieldManager _status;
	private VerticalFieldManager _central;

	public ListaPopUp() {
		super(new VerticalFieldManager());
		_title = new VerticalFieldManager();
		_status = new HorizontalFieldManager();
		_central = new VerticalFieldManager(VERTICAL_SCROLL | VERTICAL_SCROLLBAR);
		setFont(Preferencias.getTipoFuente());
		super.add(_status);
		super.add(_title);
		super.add(_searchField);
		super.add(new SeparatorField());
		super.add(_central);
	}
	
	public void add(Field field) {
		_central.add(field);
	}

	public void setStatus(Field field) {
		if (field != null) {
			field.setBackground(null);
			_status.add(field);
		} else {
			_status.deleteAll();
		}
	}

	public void setTitle(String text) {
		if (_title.getFieldCount() != 0) {
			if (_title.getField(0).getClass()
					.equals(HorizontalFieldManager.class)) {
				((LabelField) ((HorizontalFieldManager) _title.getField(0))
						.getField(0)).setText(text);
			}
		} else {
			_title.add(new LabelField(text, FIELD_HCENTER));
			_title.add(new SeparatorField());
		}
	}

	protected void click() {
		fieldChangeNotify(Util.CLICK);
	}

	public void setTitle(Field field) {
		if (_title.getFieldCount() != 0) {
			_title.deleteAll();
		}
		_title.add(field);
		_title.add(new SeparatorField());
	}

	public void setSearchField() {
		_lista.getKeywordField().setBackground(null);
		_searchField.add(_lista.getKeywordField());
	}

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
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
