package gui.Listados;

import gui.ListaListas;
import net.rim.device.api.ui.component.KeywordProvider;
import core.Juzgado;

public class ListadoJuzgadosLista extends ListaListas implements
		KeywordProvider {

	public ListadoJuzgadosLista() {
		super();
		setSourceList(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			String[] s = new String[4];
			s[0] = ((Juzgado) element).getNombre();
			s[1] = ((Juzgado) element).getTelefono();
			s[2] = ((Juzgado) element).getCiudad();
			s[3] = ((Juzgado) element).getTipo();
			return s;
		}
	}
}