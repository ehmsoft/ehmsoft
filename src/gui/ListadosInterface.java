package gui;

import java.util.Vector;

public interface ListadosInterface {
	public void addElement(Object element, int index);

	public void addElement(Object element);

	public void loadFrom(Vector collection);

	public void remove(Object element);

	public void remove(int index);

	public void replace(Object old, Object nw);

	public Object getSelected();

	public void alert(String alert);

	public int ask(Object[] options, String string, int index);

	public void setSearchField();
}
