package gui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;
import core.Persona;

public class ListadoPersonasLista extends ObjectListField {
	private int fuente1;
	private int fuente2;
	private int height;
	private int colorLinea;
	private int colorFuente2;
	
	public ListadoPersonasLista(){
		super();
		colorLinea = 0x00ECECEC;
		colorFuente2 = 0x009C9C9C;
		fuente1 = getFont().getHeight();
		fuente2 = fuente1 - 5;
		height = fuente1 + (fuente2)*2;
		setRowHeight(height);
	}

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		if (this.get(this, index).getClass() == "Hola".getClass()) {
			int posX = getWidth()/2 - getContentWidth()/4;
			int posY = getRowHeight()/2 - getFont().getHeight()/2;
			g.drawText((String) this.get(this, index), posX, posY);
		} else {
			Persona objeto = (Persona) this.get(this, index);
			String nombre = objeto.getNombre();
			String id = objeto.getId();
			String notas = objeto.getNotas();
			
			g.setFont(getFont().derive(Font.SANS_SERIF_STYLE));
			g.drawText(nombre, 0, y);
			g.setFont(getFont().derive(Font.SANS_SERIF_STYLE,fuente2));
			g.setColor(colorFuente2);
			g.drawText(id, 0, y + fuente1);
			g.drawText(notas, 0, y + fuente1 + fuente2);
		}
		g.setColor(colorLinea);
		g.drawLine(0,y+height-1,getWidth(),y+height-1);
	}
}
