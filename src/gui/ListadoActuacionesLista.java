package gui;

import java.util.Vector;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import core.Actuacion;

public class ListadoActuacionesLista extends ListaListas {

	public ListadoActuacionesLista() {
		super();
	}

	public ListadoActuacionesLista(Vector vector) {
		super(vector);
	}

	public void drawListRowDefault(ListField list, Graphics g, int index,
			int y, int w) {
		Actuacion objeto = (Actuacion) this.get(this, index);
		String descripcion = objeto.getDescripcion();

		g.drawText(descripcion, 0, y);
	}

	public String getParam(Object objeto, String parametro) {
		String ret = "Error";
		if (parametro == "juzgado") {
			ret = ((Actuacion) objeto).getJuzgado().getNombre();
		}
		if (parametro == "fecha") {
			ret = ((Actuacion) objeto).getFecha().toString();
		}
		if (parametro == "fechaProxima") {
			ret = ((Actuacion) objeto).getFechaProxima().toString();
		}
		if (parametro == "descripcion") {
			ret = ((Actuacion) objeto).getDescripcion();
		}
		return ret;
	}
}
