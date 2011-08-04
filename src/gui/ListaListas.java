package gui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;

class ListaListas extends KeywordFilterField implements ListFieldCallback {
	

	public ListaListas(int ancho) {
		super();
		setRowHeight(getFont().getHeight() * ancho);
		this.setCallback(this);
	}

	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		if (this.get(list, index) != null) {
			if (String.class.isInstance(this.get(list, index))) {
				Font f = g.getFont();
				g.setFont(getFont().derive(Font.BOLD));
				int posY = getRowHeight() / 2 - getFont().getHeight() / 2;
				g.drawText((String) this.get(list, index), 0, posY);
				g.setFont(f);
			} else {
				Object objeto = this.get(list, index);
				drawObject(objeto, g, y, w);
			}
		}
	}
	
	protected void drawObject(Object object, Graphics g, int y, int w) {
		g.drawText("Sobrecargar método", 0, 0);
	}
	
	public Object get(ListField listField, int index) {
		return getElementAt(index);
	}

	public int getPreferredWidth(ListField listField) {
		return listField.getPreferredWidth();
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		return ((KeywordFilterField)listField).indexOfList(prefix, start);
	}
}