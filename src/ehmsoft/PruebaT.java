package ehmsoft;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.container.MainScreen;

public class PruebaT extends MainScreen {
	
	ButtonField _prueba;
	ButtonField _inicial;
	public PruebaT() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		// TODO Auto-generated constructor stub
		
		_prueba = new ButtonField("Pantalla de prueba");
		_prueba.setChangeListener(listenerPrueba);
		
		_inicial = new ButtonField("Pantalla inicial");
		_inicial.setChangeListener(listenerInicial);
		
		add(_prueba);
		add(_inicial);
	}
	
	FieldChangeListener listenerPrueba = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().pushScreen(new Prueba());
		}
	};
	
	FieldChangeListener listenerInicial = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			UiApplication.getUiApplication().pushScreen(new ScreenMain());
		}
	};

}
