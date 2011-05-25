package gui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;
import core.Proceso;

public class ListaListadoProcesos extends ObjectListField {
	
	public ListaListadoProcesos()
	{
		super();
	}

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		if (String.class.isInstance(this.get(this, index))){
			g.drawText((String) this.get(this, index), 0, y);
		}
		else {
			setRowHeight(getFont().getHeight()*4);
			Proceso objeto = (Proceso) this.get(this, index);
			String demandante = objeto.getDemandante().getNombre();
			String demandado = objeto.getDemandado().getNombre();
			String radicado = objeto.getRadicado();
			String juzgado = objeto.getJuzgado().getNombre();

			g.setFont(getFont().derive(Font.BOLD));
			g.drawText("Radicado: " + radicado, 0, y);
			g.setFont(getFont().derive(Font.SANS_SERIF_STYLE));
			g.drawText("Demandante: " + demandante, 20, y
					+ getFont().getHeight());
			g.drawText("Demandado: " + demandado, 20, y + getFont().getHeight()
					* 2);
			g.drawText("Juzgado: " + juzgado, 20, y + getFont().getHeight() * 3);
		}
	}
}