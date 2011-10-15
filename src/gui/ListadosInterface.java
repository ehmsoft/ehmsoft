package gui;

import java.util.Vector;

import net.rim.device.api.ui.Field;

public interface ListadosInterface {
	public void addElement(Object element, int index);

	public void loadFrom(Vector collection);

	public void remove(Object element);

	public void replace(Object old, Object nw);

	public Object getSelected();

	public void alert(String alert);

	public int ask(Object[] options, String string, int index);

	public void setSearchField();

	public void setTitle(String text);

	public void setStatus(Field field);
}
