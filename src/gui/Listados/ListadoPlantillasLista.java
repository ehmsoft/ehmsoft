package gui.Listados;

import gui.ListaListas;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.KeywordProvider;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import core.Plantilla;

public class ListadoPlantillasLista extends ListaListas implements
		KeywordProvider, ListFieldCallback {

	public ListadoPlantillasLista() {
		super();
		setSourceList(this);
		setCallback(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			Plantilla p = (Plantilla) element;
			String[] s = new String[10];
			s[0] = p.getCategoria().getDescripcion();
			s[1] = p.getDemandado().getNombre();
			s[2] = p.getJuzgado().getNombre();
			s[3] = p.getRadicado();
			s[4] = p.getRadicadoUnico();
			s[5] = p.getEstado();
			s[6] = p.getDemandante().getNombre();
			s[7] = p.getTipo();
			s[8] = p.getNotas();
			s[9] = p.getNombre();
			return s;
		}
	}
	
	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int w) {
		try {
			super.drawListRow(listField, graphics, index, y, w);
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

	public Object get(ListField listField, int index) {
		return null;
	}

	public int getPreferredWidth(ListField listField) {
		return 0;
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		return 0;
	}
}
