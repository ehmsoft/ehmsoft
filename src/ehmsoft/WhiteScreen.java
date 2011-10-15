package ehmsoft;

import net.rim.device.api.system.Application;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.MainScreen;

public class WhiteScreen extends MainScreen {

	public WhiteScreen() {
		super();
		UiApplication.getUiApplication().invokeLater(new Runnable() {

			public void run() {
				while (!Application.isEventDispatchThread())
					;
				UiApplication.getUiApplication().pushScreen(new ScreenMain());
			}
		});
	}
}
