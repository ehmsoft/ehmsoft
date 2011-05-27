package gui;

import java.util.Vector;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import core.Persona;

public class ListadoPersonasLista extends ListaListas {
	private int fuente1;
	private int fuente2;
	private int _height;
	private int colorFuente2;

	public ListadoPersonasLista() {
		super();
		colorFuente2 = 0x009C9C9C;
		fuente1 = getFont().getHeight();
		fuente2 = fuente1 - 5;
		_height = fuente1 + (fuente2) * 2;
		setRowHeight(_height);
	}
	
	public ListadoPersonasLista(Vector vector) {
		super(vector);
	}
	
	protected void drawListRowDefault(ListField list, Graphics g, int index, int y, int w) {		
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
	
	protected String getParam(Object objeto, String parametro) {
		String ret = "Error";
		if(parametro == "nombre") {
			ret = ((Persona) objeto).getNombre();
		}
		if(parametro == "id") {
			ret = ((Persona) objeto).getId();
		}
		if(parametro == "telefono") {
			ret = ((Persona) objeto).getTelefono();
		}
		if(parametro == "direccion") {
			ret = ((Persona) objeto).getDireccion();
		}
		if(parametro == "correo") {
			ret = ((Persona) objeto).getCorreo();
		}
		if(parametro == "notas") {
			ret = ((Persona) objeto).getNotas();
		}
		return ret;
	}	
}
