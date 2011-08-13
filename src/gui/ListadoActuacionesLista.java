package gui;

import java.util.Calendar;

import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.KeywordProvider;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import core.Actuacion;

public class ListadoActuacionesLista extends ListaListas implements KeywordProvider, ListFieldCallback{
	
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
			graphics.drawText(objeto.toString(), 0, y);
			graphics.drawText(calendarToString(objeto.getFechaProxima(), false), 30, y + getFont().getHeight());
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
