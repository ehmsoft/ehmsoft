package gui;

import java.util.Vector;

import net.rim.device.api.collection.ReadableList;
import net.rim.device.api.collection.util.UnsortedReadableList;
import net.rim.device.api.system.Characters;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.XYRect;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.ui.component.KeywordProvider;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
import net.rim.device.api.ui.decor.BackgroundFactory;

public abstract class ListaListas extends KeywordFilterField implements
		ListFieldCallback {
	private Unsorted _u;

	public ListaListas() {
		super();
		setCallback(this);
		_u = new Unsorted();
		setKeywordField(new CustomKeywordField());
	}

	public void setSelectedIndex(int index) {
		super.setSelectedIndex(index);
	}

	public void loadFrom(Vector vector) {
		_u.loadFrom(vector.elements());
	}

	public void setSourceList(KeywordProvider helper) {
		setSourceList(_u, helper);
	}

	public void insert(int index, Object element) {
		_u.insert(index, element);
		updateList();
	}

	public void update(Object old, Object nw) {
		_u.update(old, nw);
		updateList();
		invalidate();
		if (getKeywordField().getTextLength() != 0) {
			if (!old.equals(nw)) {
				setText("");
				setText(nw.toString());
			}
		}
	}

	public void setText(String text) {
		getKeywordField().setText(text);
		updateList();
		invalidate();
	}

	public void remove(Object element) {
		_u.delete(element);
		updateList();
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
		}
		graphics.drawText(r.getAt(index).toString(), 0, y);
	}

	public Object get(ListField listField, int index) {
		return null;
	}

	public int getPreferredWidth(ListField listField) {
		return 0;
	}

	public int indexOfList(ListField listField, String prefix, int start) {
		return 0;
	}
}

class Unsorted extends UnsortedReadableList {
	public void insert(int index, Object element) {
		insertAt(index, element);
	}

	public Object getAt(int index) {
		try {
			return super.getAt(index);
		} catch (Exception e) {
			return super.getAt(index - 1);
		}
	}

	public void delete(Object element) {
		doRemove(element);
	}

	public void update(Object old, Object nw) {
		doUpdate(old, nw);
	}
}

final class CustomKeywordField extends BasicEditField {
	// Contructor
	CustomKeywordField() {
		// Custom style.
		super(USE_ALL_WIDTH | NON_FOCUSABLE | NO_LEARNING | NO_NEWLINE);
		setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		setLabel("Buscar: ");
	}

	/**
	 * Intercepts ESCAPE key.
	 * 
	 * @see net.rim.device.api.ui.component.TextField#keyChar(char,int,int)
	 */
	protected boolean keyChar(char ch, int status, int time) {
		switch (ch) {
		case Characters.ESCAPE:
			// Clear keyword.
			if (super.getTextLength() > 0) {
				setText("");
				return true;
			}
		}
		return super.keyChar(ch, status, time);
	}

	/**
	 * Overriding super to add custom painting to our class.
	 * 
	 * @see net.rim.device.api.ui.Field#paint(Graphics)
	 */
	protected void paint(Graphics graphics) {
		graphics.setColor(Color.WHITE);
		super.paint(graphics);

		// Draw caret.
		getFocusRect(new XYRect());
		drawFocus(graphics, true);
	}
}
