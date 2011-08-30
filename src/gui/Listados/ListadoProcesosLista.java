package gui.Listados;

import gui.ListaListas;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.KeywordProvider;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import core.Proceso;

public class ListadoProcesosLista extends ListaListas implements
		KeywordProvider, ListFieldCallback {

	public ListadoProcesosLista() {
		super();
		setRowHeight(getFont().getHeight() * 4);
		setSourceList(this);
		setCallback(this);
	}

	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			Proceso p = (Proceso) element;
			String[] s = new String[9];
			s[0] = p.getDemandante().getNombre();
			s[1] = p.getDemandado().getNombre();
			s[2] = p.getJuzgado().getNombre();
			s[3] = p.getRadicado();
			s[4] = p.getRadicadoUnico();
			s[5] = p.getEstado();
			s[6] = p.getCategoria().getDescripcion();
			s[7] = p.getTipo();
			s[8] = p.getNotas();
			return s;
		}
	}

	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int w) {
		ReadableList r = ((ListaListas) listField).getResultList();
		if (String.class.isInstance(r.getAt(index))) {
			graphics.setFont(graphics.getFont().derive(Font.BOLD));
			int color = graphics.getColor();
			graphics.setColor(Color.LIGHTGRAY);
			graphics.drawLine(5, listField.getRowHeight() + y - 1,
					listField.getWidth() - 5, listField.getRowHeight() + y - 1);
			graphics.setColor(color);
			graphics.drawText(r.getAt(index).toString(), 0, y);
		} else {
			Proceso objeto = (Proceso) r.getAt(index);
			String demandante = objeto.getDemandante().getNombre();
			String demandado = objeto.getDemandado().getNombre();
			String radicado = objeto.getRadicado();
			String juzgado = objeto.getJuzgado().getNombre();

			Font temp = getFont();
			graphics.setFont(getFont().derive(Font.BOLD));
			graphics.drawText("Radicado: " + radicado, 0, y);
			graphics.setFont(temp);
			graphics.drawText("Demandante: " + demandante, 20, y
					+ getFont().getHeight());
			graphics.drawText("Demandado: " + demandado, 20, y
					+ getFont().getHeight() * 2);
			graphics.drawText("Juzgado: " + juzgado, 20, y
					+ getFont().getHeight() * 3);
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