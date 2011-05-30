package gui;

import java.util.Vector;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.ListField;
import core.Persona;

public class ListadoPersonasLista extends ListaListas {

	public ListadoPersonasLista() {
		super();
	}
	
	public ListadoPersonasLista(Vector vector) {
		super(vector);
	}
	
	public void drawListRowDefault(ListField list, Graphics g, int index, int y, int w) {		
		Persona objeto = (Persona) this.get(this, index);
		String nombre = objeto.getNombre();

		g.drawText(nombre, 0, y);
	}
	
	public String getParam(Object objeto, String parametro) {
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
