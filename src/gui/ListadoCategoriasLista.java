package gui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import core.Categoria;

public class ListadoCategoriasLista extends ListaListas {

	public ListadoCategoriasLista() {
		super(0);
	}

	public void drawListRowDefault(ListField list, Graphics g, int index,
			int y, int w) {
		Categoria objeto = (Categoria) this.get(this, index);
		String descripcion = objeto.getDescripcion();

		g.drawText(descripcion, 0, y);
	}

	public String getParam(Object objeto, String parametro) {
		String ret = "Error";
		if (parametro == "descripcion") {
			ret = ((Categoria) objeto).getDescripcion();
		}
		return ret;
	}
}