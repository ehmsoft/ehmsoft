package gui;

import java.util.Calendar;

import net.rim.device.api.ui.Graphics;
import core.Actuacion;

public class ListadoActuacionesLista extends ListaListas {
	
	public static final int SHOW_DESCRIPCION = 0;
	public static final int SHOW_JUZGADO = 1;
	public static final int SHOW_FECHA = 2;
	public static final int SHOW_FECHA_PROXIMA = 3;
	
	private int[] _style;
	
	public ListadoActuacionesLista() {
		super(1);
		_style = new int[1];
		_style[0] = SHOW_DESCRIPCION;
	}

	public ListadoActuacionesLista(int[] style) {
		super(getAncho(style));
		_style = style;
	}
	
	private static int getAncho(int[] a) {
		int ancho = 0;
		for (int i = 0; i < a.length; i++) {
			int s = a[i];
			if (s == SHOW_DESCRIPCION) {
				ancho += 1;
			} else if (s == SHOW_JUZGADO) {
				ancho += 1;
			} else if (s == SHOW_FECHA) {
				ancho += 1;
			} else if (s == SHOW_FECHA_PROXIMA) {
				ancho += 1;
			}
		}
		return ancho;
	}
	
	protected void drawObject(Object object, Graphics g, int y, int w) {
		Actuacion a = (Actuacion) object;
		for(int i = 0; i < _style.length; i++) {
			String text = new String();
			int s = _style[i];
			if(s == SHOW_DESCRIPCION) {
				text = a.getDescripcion();
			}
			else if(s == SHOW_JUZGADO) {
				text = a.getJuzgado().getNombre();
			}
			else if(s == SHOW_FECHA) {
				text = calendarToString(a.getFecha());
			}
			else if(s == SHOW_FECHA_PROXIMA) {
				text = calendarToString(a.getFechaProxima());
			}
			if(i == 0) {
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
