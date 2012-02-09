package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.FullScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class AboutEhm extends FullScreen {

	/**
	 * Pantalla Acerca de ehmSoft
	 */
	private BitmapField _logo;
	private final String EMPRESA = "ehmSoftware S.A.S";
	private final String DIRECCION = "Calle 38 bis 9 b 31";
	private final String CIUDAD = "Pereira, Colombia";
	private final String TELEFONO = "Tel: (57)3175911125";
	private final String URL = "www.ehmsoft.com";
	private final String CONTACTO = "soporte@ehmsoft.com";

	public AboutEhm() {
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		this.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		if (Display.getWidth() == 480){
			_logo = new BitmapField(Bitmap.getBitmapResource("logoN450.png"),
					FIELD_HCENTER);
		}
		else if(Display.getWidth() == 360){
			_logo = new BitmapField(Bitmap.getBitmapResource("logoN350.png"),
					FIELD_HCENTER);
		}
		else {
			_logo = new BitmapField(Bitmap.getBitmapResource("logoN300.png"),
					FIELD_HCENTER);
		}
		add(new WhiteLabelField("\n", Field.FIELD_HCENTER));
		add(_logo);
		add(new WhiteLabelField(EMPRESA, Field.FIELD_HCENTER));
		add(new WhiteLabelField(URL, Field.FOCUSABLE | Field.FIELD_HCENTER));
		add(new WhiteLabelField(DIRECCION, Field.FOCUSABLE | Field.FIELD_HCENTER));
		add(new WhiteLabelField(CIUDAD, Field.FOCUSABLE | Field.FIELD_HCENTER));
		add(new WhiteLabelField(TELEFONO, Field.FOCUSABLE | Field.FIELD_HCENTER));
		add(new WhiteLabelField(CONTACTO, Field.FOCUSABLE | Field.FIELD_HCENTER));

	}

	public boolean onClose() {
		Util.popScreen(this);
		return true;
	}

	protected class WhiteLabelField extends LabelField {
		public WhiteLabelField(String texto, long style) {
			super(texto, style);
		}

		public void paint(Graphics graphics) {
			graphics.setColor(Color.WHITE);
			super.paint(graphics);
		}
	}
}
