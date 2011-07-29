package gui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

abstract class ListaListas extends ObjectListField {

	public ListaListas(int ancho) {
		super();
		setRowHeight(getFont().getHeight() * ancho);
	}

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		if (String.class.isInstance(this.get(this, index))) {
			Font f = g.getFont();
			g.setFont(getFont().derive(Font.BOLD));
			int posY = getRowHeight() / 2 - getFont().getHeight() / 2;
			g.drawText((String) this.get(this, index), 0, posY);
			g.setFont(f);
		} else {
			Object objeto = this.get(this, index);
			drawObject(objeto, g, y, w);
		}
	}
	
	protected void drawObject(Object object, Graphics g, int y, int w) {
		g.drawText("Implementar método", 0, 0);
	}
}