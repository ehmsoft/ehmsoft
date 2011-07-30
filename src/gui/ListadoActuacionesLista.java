package gui;

import java.util.Calendar;

import net.rim.device.api.ui.Graphics;
import core.Actuacion;

public class ListadoActuacionesLista extends ListaListas {
	
	public static final int SHOW_DESCRIPCION = 1;
	public static final int SHOW_JUZGADO = 2;
	public static final int SHOW_FECHA = 4;
	public static final int SHOW_FECHA_PROXIMA = 8;
	
	private long _style;
	
	public ListadoActuacionesLista() {
		super(1);
		_style = SHOW_DESCRIPCION;
	}

	public ListadoActuacionesLista(long style) {
		super(getAncho(style));
		_style = style;
	}
	
	private static int getAncho(long a) {
		int ancho = 0;

		if ((a & SHOW_DESCRIPCION) == SHOW_DESCRIPCION) {
			ancho += 1;
		}
		if ((a & SHOW_JUZGADO) == SHOW_JUZGADO) {
			ancho += 1;
		}
		if ((a & SHOW_FECHA) == SHOW_FECHA) {
			ancho += 1;
		}
		if ((a & SHOW_FECHA_PROXIMA) == SHOW_FECHA_PROXIMA) {
			ancho += 1;
		}
		return ancho;
	}
	
	protected void drawObject(Object object, Graphics g, int y, int w) {
		Actuacion a = (Actuacion) object;

		String text = "Ninguno";
		while (y <= this.getRowHeight()) {
			if ((_style & SHOW_DESCRIPCION) == SHOW_DESCRIPCION) {
				text = a.getDescripcion();
			}
			if ((_style & SHOW_JUZGADO) == SHOW_JUZGADO) {
				text = a.getJuzgado().getNombre();
			}
			if ((_style & SHOW_FECHA) == SHOW_FECHA) {
				text = calendarToString(a.getFecha());
			}
			if ((_style & SHOW_FECHA_PROXIMA) == SHOW_FECHA_PROXIMA) {
				text = calendarToString(a.getFechaProxima());
			}
			if (getAncho(_style) == 1) {
				g.drawText(text, 0, y);
			} else {
				g.drawText(text, 20, y);
			}
			y += getFont().getHeight();
		}
	}
	
	private String calendarToString(Calendar calendar) {
		String string = "";
		string.concat(calendar.get(Calendar.DAY_OF_MONTH)+"");
		string.concat("/");
		string.concat(calendar.get(Calendar.MONTH)+"");
		string.concat("/");
		string.concat(calendar.get(Calendar.YEAR)+"");
		string.concat(" a las ");
		string.concat(calendar.get(Calendar.HOUR)+"");
		string.concat(":");
		string.concat(calendar.get(Calendar.MINUTE)+"");
		string.concat(" ");
		if(calendar.get(Calendar.AM_PM) == Calendar.AM) {
			string.concat("AM");
		} else {
			string.concat("PM");
		}
		return string;
	}
}
