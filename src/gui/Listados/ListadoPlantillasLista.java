package gui.Listados;

import gui.ListaListas;
import net.rim.device.api.ui.component.KeywordProvider;
import core.Plantilla;

public class ListadoPlantillasLista extends ListaListas implements
		KeywordProvider {

	public ListadoPlantillasLista() {
		super();
		setSourceList(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			Plantilla p = (Plantilla) element;
			String[] s = new String[10];
			s[0] = p.getDemandante().getNombre();
			s[1] = p.getDemandado().getNombre();
			s[2] = p.getJuzgado().getNombre();
			s[3] = p.getRadicado();
			s[4] = p.getRadicadoUnico();
			s[5] = p.getEstado();
			s[6] = p.getCategoria().getDescripcion();
			s[7] = p.getTipo();
			s[8] = p.getNotas();
			s[9] = p.getNombre();
			return s;
		}
	}

}
