package gui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;
import core.Juzgado;

public class ListadoJuzgadosLista extends ObjectListField {

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		if (this.get(this, index).getClass() == "Hola".getClass()) {
			g.drawText((String) this.get(this, index), 0, y);
		} else {
			Juzgado objeto = (Juzgado) this.get(this, index);
			String nombre = objeto.getNombre();
			g.drawText(nombre, 0, y);
		}
	}
}
