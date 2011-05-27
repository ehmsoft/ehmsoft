package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ObjectListField;

public class ListaListas extends ObjectListField {
	protected final int _fuente;
	protected static int _colorLinea = 0x00D2D2D2;
	protected Vector _fuentes;
	
	public ListaListas() {
		super();
		_fuente = getFont().getHeight();
	}
	
	public ListaListas(Vector fuentes) {
		super();
		_fuente = getFont().getHeight();
		_fuentes = fuentes;
		int height = 0;
		Enumeration ancho = fuentes.elements();
		while(ancho.hasMoreElements())
			height += ((Fuente)ancho.nextElement()).getHeight() + _fuente;
		setRowHeight(height);		
	}
	
	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
		if (String.class.isInstance(this.get(this, index))) {
			int posX = getWidth() / 2 - getContentWidth() / 4;
			int posY = getRowHeight() / 2 - getFont().getHeight() / 2;
			g.drawText((String) this.get(this, index), posX, posY);
		} else if (_fuentes == null) {
			drawListRowDefault(list, g, index, y, w);
		} else {
			Object objeto = this.get(this, index);
			int i = y;
			Enumeration fuentes = _fuentes.elements();
			while (fuentes.hasMoreElements()) {
				Fuente fuente = (Fuente) fuentes.nextElement();
				g.setColor(fuente.getColor());
				g.setFont(getFont().derive(getFont().getStyle(),
						fuente.getHeight()+_fuente));
				g.drawText(getParam(objeto, fuente.getParametro()), 0, i);
				i += fuente.getHeight() + _fuente;
			}
		}
		g.setColor(_colorLinea);
		g.drawLine(0, y + getRowHeight() - 1, getWidth(), y + getRowHeight()
				- 1);
	}
	
	protected void drawListRowDefault(ListField list, Graphics g, int index, int y, int w) {
		super.drawListRow(list, g, index, y, w);
	}

	protected String getParam(Object objeto, String parametro) {
		String ret = "Error";
		return ret;
	}		
}

/*	public void drawListRow(ListField list, Graphics g, int index, int y, int w) {
if (String.class.isInstance(this.get(this, index))) {
	int posX = getWidth() / 2 - getContentWidth() / 4;
	int posY = getRowHeight() / 2 - getFont().getHeight() / 2;
	g.drawText((String) this.get(this, index), posX, posY);
} else {
	Persona objeto = (Persona) this.get(this, index);
	String nombre = objeto.getNombre();
	String id = objeto.getId();
	String notas = objeto.getNotas();

	g.setFont(getFont().derive(Font.SANS_SERIF_STYLE));
	g.drawText(nombre, 0, y);
	g.setFont(getFont().derive(Font.SANS_SERIF_STYLE, fuente2));
	g.setColor(colorFuente2);
	g.drawText(id, 0, y + fuente1);
	g.drawText(notas, 0, y + fuente1 + fuente2);
}
g.setColor(colorLinea);
g.drawLine(0, y + height - 1, getWidth(), y + height - 1);
}*/