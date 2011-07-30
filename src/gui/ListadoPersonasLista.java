package gui;

import net.rim.device.api.ui.Graphics;
import core.Persona;

public class ListadoPersonasLista extends ListaListas {
	public static final int SHOW_ID = 1;
	public static final int SHOW_NOMBRE = 2;
	public static final int SHOW_TELEFONO = 4;
	public static final int SHOW_DIRECCION = 8;
	public static final int SHOW_CORREO = 16;
	public static final int SHOW_NOTAS = 32;
	
	private long _style;

	public ListadoPersonasLista() {
		super(1);
		_style = SHOW_NOMBRE;
	}

	public ListadoPersonasLista(long style) {
		super(getAncho(style));
		_style = style;
	}
	
	static int getAncho(long a) {
		int ancho = 0;
		if((a & SHOW_ID) == SHOW_ID) {
			ancho += 1;
		}
		if((a & SHOW_NOMBRE) == SHOW_NOMBRE) {
			ancho += 1;
		}
		if((a & SHOW_TELEFONO) == SHOW_TELEFONO) {
			ancho += 1;
		}
		if((a & SHOW_DIRECCION) == SHOW_DIRECCION) {
			ancho += 1;
		}
		if((a & SHOW_CORREO) == SHOW_CORREO) {
			ancho += 1;
		}
		if((a & SHOW_NOTAS) == SHOW_NOTAS) {
			ancho += 1;
		}
		return ancho;
	}
	
	protected void drawObject(Object object, Graphics g, int y, int w) {
		Persona p = (Persona) object;
		int temp = y;
		int cont = 0;

		String text = "Ninguno";
		while (y <= temp + this.getRowHeight()) {
			if ((_style & SHOW_NOMBRE) == SHOW_NOMBRE && cont == 0) {
				text = p.getNombre();
			} else if ((_style & SHOW_ID) == SHOW_ID && cont == 1) {
				text = p.getId();
			} else if ((_style & SHOW_TELEFONO) == SHOW_TELEFONO) {
				text = p.getTelefono();
			} else if ((_style & SHOW_DIRECCION) == SHOW_DIRECCION) {
				text = p.getDireccion();
			} else if ((_style & SHOW_CORREO) == SHOW_CORREO) {
				text = p.getCorreo();
			} else if ((_style & SHOW_NOTAS)  == SHOW_NOTAS) {
				text = p.getNotas();
			}
			if (y == temp) {
				g.drawText(text, 0, y);
			} else {
				g.drawText(text, 20, y);
			}
			y += getFont().getHeight();
		}
	}
}
