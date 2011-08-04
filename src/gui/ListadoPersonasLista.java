package gui;

import core.Persona;
import net.rim.device.api.collection.util.UnsortedReadableList;
import net.rim.device.api.ui.component.KeywordProvider;


public class ListadoPersonasLista extends UnsortedReadableList implements KeywordProvider{
	
	public void insert(int index, Object element) {
		insertAt(index, element);
	}
	
	public void delete(int index) {
		doRemove(getAt(index));
	}
	
	public void update(Object old, Object nw) {
		doUpdate(old, nw);
	}
	
	public int getSize() {
		return size();
	}

	public String[] getKeywords(Object element) {
		if(String.class.isInstance(element)) {
			return null;
		} else {
			String[] s = new String[3];
			s[0] = ((Persona)element).getNombre();
			s[1] = ((Persona)element).getTelefono();
			s[2] = ((Persona)element).getId();
			return s;
		}
	}
}

