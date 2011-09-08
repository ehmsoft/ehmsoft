package ehmsoft;

import net.rim.device.api.ui.UiApplication;

public class Main extends UiApplication
{
	public Main() {
		pushScreen(new ScreenMain());
	}
	
    public static void main(String[] args)
    {
    	Main ehm = new Main();
    	ehm.enterEventDispatcher();
    }
}
