package gui.Listados;

import gui.ListaListas;
import net.rim.device.api.ui.component.KeywordProvider;
import core.Persona;

public class ListadoPersonasLista extends ListaListas implements
		KeywordProvider {

	public ListadoPersonasLista() {
		super();
		setSourceList(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			String[] s = new String[3];
			s[0] = ((Persona) element).getNombre();
			s[1] = ((Persona) element).getTelefono();
			s[2] = ((Persona) element).getId();
			return s;
		}
	}
}