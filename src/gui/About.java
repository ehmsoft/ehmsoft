package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.FullScreen;
import net.rim.device.api.ui.decor.BackgroundFactory;

public class About extends FullScreen {

	/**
	 * Pantalla Acerca de
	 */
	private BitmapField _logo;
	private final String NOMBRE_APLICACION = "ehmSoftware Mobile";
	private final String VERSION = "Versión 1.0";
	private final String LICENCIA = "Licencia GNU GPL V3";
	private final String EMPRESA = "ehmSoft";
	private final String URL = "www.ehmSoft.com";
	private final String CONTACTO = "soporte@ehmsoft.com";

	public About() {
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		this.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		_logo = new BitmapField(Bitmap.getBitmapResource("logo.png"),
				FIELD_HCENTER);
		add(new WhiteLabelField("\n", Field.FIELD_HCENTER));
		add(_logo);
		add(new WhiteLabelField(NOMBRE_APLICACION, Field.FIELD_HCENTER));
		add(new WhiteLabelField(VERSION, Field.FIELD_HCENTER));
		add(new WhiteLabelField(LICENCIA, Field.FIELD_HCENTER));
		add(new WhiteLabelField(EMPRESA, Field.FIELD_HCENTER));
		add(new WhiteLabelField(URL, Field.FOCUSABLE
				| Field.FIELD_HCENTER));
		add(new WhiteLabelField(CONTACTO, Field.FOCUSABLE
				| Field.FIELD_HCENTER));

	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(this);
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
