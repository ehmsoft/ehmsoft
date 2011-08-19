package gui;

import java.util.Calendar;

import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.KeywordProvider;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import core.Actuacion;
import core.CalendarManager;

public class ListadoActuacionesLista extends ListaListas implements KeywordProvider, ListFieldCallback{
	
	private Bitmap _bell = Bitmap.getBitmapResource("bell.png");
	private Bitmap _clock = Bitmap.getBitmapResource("clock.png");
	
	public ListadoActuacionesLista() {
		super();
		setRowHeight(getFont().getHeight() * 2);
		setSourceList(this);
		setCallback(this);
	}

	public void drawListRow(ListField listField, Graphics graphics, int index,
			int y, int width) {
		ReadableList r = ((ListaListas) listField).getResultList();
		if (String.class.isInstance(r.getAt(index))) {
			graphics.setFont(graphics.getFont().derive(Font.BOLD));
			int color = graphics.getColor();
			graphics.setColor(Color.LIGHTGRAY);
			graphics.drawLine(5, listField.getRowHeight() + y - 1,
					listField.getWidth() - 5, listField.getRowHeight() + y - 1);
			graphics.setColor(color);
			graphics.drawText(r.getAt(index).toString(), 0, y);
		} else {
			Actuacion objeto = (Actuacion) r.getAt(index);
			try {
				int count = 0;
				if (objeto.getUid() != null) {
					BlackBerryEvent e = CalendarManager.consultarCita(objeto.getUid());
					if(e != null) {
						graphics.drawBitmap(0, y, 16, 16, _clock, 0, 0);
						count += 16;
						if (e.countValues(BlackBerryEvent.ALARM) > 0) {
							graphics.drawBitmap(16,y,16,16,_bell,0,0);
							count += 16;
						}
					} 
				}
				graphics.drawText(objeto.toString(), 0 + count, y);
				graphics.drawText(
						calendarToString(objeto.getFechaProxima(), false), 15,
						y + getFont().getHeight());
			} catch (Exception e) {
			}
		}

	}
	
	public String[] getKeywords(Object element) {
		if (String.class.isInstance(element)) {
			return null;
		} else {
			String[] s = new String[5];
			s[0] = ((Actuacion)element).getDescripcion();
			s[1] = ((Actuacion)element).getFechaProxima().get(Calendar.DAY_OF_MONTH) + "";
			s[2] = ((Actuacion)element).getFechaProxima().get(Calendar.MONTH) + "";
			s[3] = ((Actuacion)element).getFechaProxima().get(Calendar.YEAR) + "";
			s[4] = ((Actuacion)element).getFechaProxima().get(Calendar.HOUR) + "";
			return s;
		}
	}

	public Object get(ListField listField, int index) {
		// TODO Auto-generated method stub
		return null;
	}

	public int getPreferredWidth(ListField listField) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	static public String calendarToString(Calendar calendar, boolean alas) {
		String string = "";
		if (calendar.get(Calendar.DAY_OF_MONTH) < 10) {
			string = string + "0";
		}
		string = string + calendar.get(Calendar.DAY_OF_MONTH);
		string = string + "/";
		if ((calendar.get(Calendar.MONTH) + 1) < 10) {
			string = string + "0";
		}
		string = string + (calendar.get(Calendar.MONTH) + 1);
		string = string + "/";
		string = string + calendar.get(Calendar.YEAR);
		if (alas) {
			if (calendar.get(Calendar.HOUR) == 1) {
				string = string + " a la ";
			} else {
				string = string + " a las ";
			}
		} else {
			string = string + " - ";
		}
		if (calendar.get(Calendar.HOUR) < 10) {
			string = string + "0";
		}
		string = string + calendar.get(Calendar.HOUR);
		string = string + ":";
		if(calendar.get(Calendar.MINUTE) < 10) {
			string = string + "0";
		}
		string = string + calendar.get(Calendar.MINUTE);
		string = string + " ";
		if (calendar.get(Calendar.AM_PM) == Calendar.AM) {
			string = string + ("AM");
		} else {
			string = string + ("PM");
		}
		return string;
	}
}
