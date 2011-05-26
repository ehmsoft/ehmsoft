package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.BackgroundFactory;
import net.rim.device.api.ui.decor.BorderFactory;

public abstract class FondoNuevos extends MainScreen {

	/**
	 * Crea el fondo para las pantallas nuevas
	 */
	protected VerticalFieldManager _vertical;

	public FondoNuevos() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		this.getMainManager().setBackground(
				BackgroundFactory.createLinearGradientBackground(0x0099CCFF,
						0x0099CCFF, 0x00336699, 0x00336699));
		_vertical = new VerticalFieldManager() {
			public void add(Field field) {
				if (this.getFieldCount() != 0)
					super.add(new SeparatorField());
				super.add(field);
			}
		};
		Bitmap borderBitmap = Bitmap.getBitmapResource("rounded-border.png");
		_vertical.setBorder(BorderFactory.createBitmapBorder(new XYEdges(12,
				12, 12, 12), borderBitmap));
	}
	
	protected void addElem(Field field){
		_vertical.add(field);
	}
}
