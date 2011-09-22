package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Color;
import net.rim.device.api.ui.Graphics;
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
		super(FullScreen.VERTICAL_SCROLL | FullScreen.VERTICAL_SCROLLBAR);
		this.setBackground(BackgroundFactory.createSolidBackground(Color.BLACK));
		_logo = new BitmapField(Bitmap.getBitmapResource("logo.png"), FIELD_HCENTER);
		add(new WhiteLabelField("\n", LabelField.FIELD_HCENTER));
		add(_logo);
		add(new WhiteLabelField(NOMBRE_APLICACION, LabelField.FIELD_HCENTER));
		add(new WhiteLabelField(VERSION, LabelField.FIELD_HCENTER));
		add(new WhiteLabelField(LICENCIA, LabelField.FIELD_HCENTER));
		add(new WhiteLabelField(EMPRESA, LabelField.FIELD_HCENTER));
		add(new WhiteLabelField(URL, LabelField.FOCUSABLE | LabelField.FIELD_HCENTER));
		add(new WhiteLabelField(CONTACTO, LabelField.FOCUSABLE | LabelField.FIELD_HCENTER));
		
	}
	public boolean onClose(){
		UiApplication.getUiApplication().popScreen(this);
		return true;
	}
	protected class WhiteLabelField extends LabelField{
		public WhiteLabelField(String texto, long style){
			super(texto,style);
		}
		public void paint(Graphics graphics)
	    {
	        graphics.setColor(Color.WHITE);
	        super.paint(graphics);
	    }
	}
}
