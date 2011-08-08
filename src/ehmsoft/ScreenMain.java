package ehmsoft;

import persistence.Persistence;
import gui.ActuacionesManager;
import gui.CustomButtonField;
import gui.CustomFieldManager;
import gui.ListadoActuacionesLista;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public class ScreenMain extends MainScreen {
	/**
	 * Creates a new screenMain object
	 */
	CustomFieldManager _middle;
	HorizontalFieldManager _bottom;
	
	
	public ScreenMain() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		ActuacionesManager a = new ActuacionesManager();
		_middle = new CustomFieldManager(a);
		add(_middle);
		
		_bottom = new HorizontalFieldManager();
		Bitmap bm = Bitmap.getBitmapResource("big.png");
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		_bottom.add(new BitmapField(bm));
		add(_bottom);
	}
}
