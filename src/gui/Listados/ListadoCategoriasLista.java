package gui.Listados;

import gui.ListaListas;
import net.rim.device.api.ui.component.KeywordProvider;
import core.Categoria;

public class ListadoCategoriasLista extends ListaListas implements
		KeywordProvider {

	public ListadoCategoriasLista() {
		super();
		setSourceList(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			String[] s = new String[1];
			s[0] = ((Categoria) element).getDescripcion();
			return s;
		}
	}
}