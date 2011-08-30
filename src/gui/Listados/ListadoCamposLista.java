package gui.Listados;

import gui.ListaListas;
import core.CampoPersonalizado;
import net.rim.device.api.ui.component.KeywordProvider;

public class ListadoCamposLista extends ListaListas implements
		KeywordProvider {

	public ListadoCamposLista() {
		super();
		setSourceList(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			String[] s = new String[1];
			s[0] = ((CampoPersonalizado) element).getNombre();
			return s;
		}
	}
}