package gui;

import core.Preferencias;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;

public abstract class FondoNormal extends MainScreen {

	/**
	 * Crea el fondo para las pantallas nuevas
	 */
	VerticalFieldManager _vertical;

	public FondoNormal() {
		super();
		_vertical = new VerticalFieldManager(VERTICAL_SCROLL
				| VERTICAL_SCROLLBAR | USE_ALL_WIDTH);
		// TODO Auto-generated constructor stub
		this.getMainManager().setBackground(
				BackgroundFactory.createLinearGradientBackground(0x0099CCFF,
						0x0099CCFF, 0x00336699, 0x00336699));
		Bitmap borderBitmap = Bitmap.getBitmapResource("rounded-border.png");

		_vertical.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,
				12, 12, 12), borderBitmap));
		setFont(Preferencias.getTipoFuente());
		super.add(_vertical);
	}

	public void add(Field field) {
		if (_vertical.getFieldCount() != 0) {
			_vertical.add(new SeparatorField());
		}
		_vertical.add(field);
	}

	public void add(Field field, boolean separator) {
		if (separator) {
			add(field);
		} else {
			_vertical.add(field);
		}
	}

	public Field getFieldWithFocus() {
		return _vertical.getFieldWithFocus();
	}

	public Field getLeafFieldWithFocus() {
		return _vertical.getLeafFieldWithFocus();
	}

	public void delete(Field field) {
		int index = _vertical.getFieldWithFocusIndex();
		Field separator = _vertical.getField(index - 1);
		if (index > 0) {
			if (SeparatorField.class.isInstance(separator)) {
				_vertical.delete(separator);
			}
		}
		_vertical.delete(field);
	}
}
