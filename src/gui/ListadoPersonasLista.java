package gui;

import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import core.Persona;

public class ListadoPersonasLista extends ListaListas {
	public static final int SHOW_ID = 1;
	public static final int SHOW_NOMBRE = 2;
	public static final int SHOW_TELEFONO = 4;
	public static final int SHOW_DIRECCION = 8;
	public static final int SHOW_CORREO = 16;
	public static final int SHOW_NOTAS = 32;
	public static final int SHOW_ALL = 1 | 2 | 4 | 8 | 16 | 32;
	
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
		byte[] dibujados = {0,0,0,0,0,0};
		boolean d = false;

		String text = "Ninguno";
		while (y <= temp + this.getRowHeight()) {
			if ((_style & SHOW_NOMBRE) == SHOW_NOMBRE && !d) {
				if (dibujados[0] != 1) {
					text = p.getNombre();
					dibujados[0] = 1;
					d = true;
				}
			}
			if ((_style & SHOW_ID) == SHOW_ID && !d) {
				if (dibujados[1] != 1) {
					text = p.getId();
					dibujados[1] = 1;
					d = true;
				}
			}
			if ((_style & SHOW_TELEFONO) == SHOW_TELEFONO && !d) {
				if (dibujados[2] != 1) {
					text = p.getTelefono();
					dibujados[2] = 1;
					d = true;
				}
			}
			if ((_style & SHOW_DIRECCION) == SHOW_DIRECCION && !d) {
				if (dibujados[3] != 1) {
					text = p.getDireccion();
					dibujados[3] = 1;
					d = true;
				}
			}
			if ((_style & SHOW_CORREO) == SHOW_CORREO && !d) {
				if (dibujados[4] != 1) {
					text = p.getCorreo();
					dibujados[4] = 1;
					d = true;
				}
			}
			if ((_style & SHOW_NOTAS) == SHOW_NOTAS && !d) {
				if (dibujados[5] != 1) {
					text = p.getNotas();
					dibujados[5] = 1;
					d = true;
				}
			}
			if (y == temp) {
				Font f = getFont();
				g.setFont(getFont().derive(Font.BOLD));
				g.drawText(text, 0, y);
				g.setFont(f);
			} else {
				g.drawText(text, 30, y);
			}
			y += getFont().getHeight();
			d = false;
		}
	}
}
