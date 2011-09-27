package ehmsoft;

import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class WhiteScreen extends MainScreen {

	public WhiteScreen() {
		super();
		UiApplication.getUiApplication().invokeLater(new Runnable() {
			
			public void run() {
				while(!UiApplication.getUiApplication().isHandlingEvents());
				UiApplication.getUiApplication().pushScreen(new ScreenMain());
			}
		});
	}
}
