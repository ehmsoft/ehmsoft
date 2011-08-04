package gui;

import net.rim.device.api.collection.util.UnsortedReadableList;
import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.ui.component.KeywordProvider;

abstract class ListaListas extends KeywordFilterField {
	Unsorted _u;

	public ListaListas() {
		super();
		_u = new Unsorted();
	}

	public void setSourceList(KeywordProvider helper) {
		setSourceList(_u, helper);
	}

	public void insert(int index, Object element) {
		_u.insert(index, element);
		updateList();
	}

	public void insert(Object element) {
		_u.insert(element);
		updateList();
	}

	public void update(Object old, Object nw) {
		_u.update(old, nw);
		updateList();
	}

	public void setText(String text) {
		getKeywordField().setText(text);
		updateList();
		invalidate();
	}

	public int getIndex(Object element) {
		return _u.getIndex(element);
	}

	public void remove(int index) {
		_u.delete(index);
		updateList();
	}

	public void remove(Object element) {
		_u.delete(element);
		updateList();
	}
}

class Unsorted extends UnsortedReadableList {
	public void insert(int index, Object element) {
		insertAt(index, element);
	}

	public void insert(Object element) {
		doAdd(element);
	}

	public void delete(int index) {
		doRemove(getAt(index));
	}

	public void delete(Object element) {
		doRemove(element);
	}

	public void update(Object old, Object nw) {
		doUpdate(old, nw);
	}
}