package ehmsoft;

import persistence.Persistence;
import gui.ActuacionesManager;
import gui.CustomButtonField;
import gui.CustomFieldManager;
import gui.ListaCircular;
import gui.ListadoActuacionesLista;
import gui.PersonasManager;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
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
		ActuacionesManager a = new ActuacionesManager(5);
		ActuacionesManager p = new ActuacionesManager(10);
		PersonasManager q = new PersonasManager();
		
		ListaCircular l = new ListaCircular();
		l.add(a);
		l.add(p);
		l.add(q);
		
		_middle = new CustomFieldManager(l);
		add(_middle);
		
		_bottom = new HorizontalFieldManager();
		Bitmap bm = new Bitmap((int)(Display.getHeight() * 0.133333333), (int)(Display.getHeight() * 0.133333333));
		bm = Bitmap.getBitmapResource("big.png");
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
