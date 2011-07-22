package gui;

import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevaCategoriaPopUp extends PopupScreen {
	
	private BasicEditField _txtDescripcion;
	private boolean _guardar = false;

	public NuevaCategoriaPopUp() {
		super(new VerticalFieldManager());
		LabelField labelField = new LabelField("Nueva categoría",
				Field.FIELD_HCENTER);
		add(labelField);
		add(new SeparatorField());
		
		_txtDescripcion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripcion: ");

		add(_txtDescripcion);
		
		ButtonField btnfldOk = new ButtonField("OK", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		btnfldOk.setMinimalWidth(100);
		btnfldOk.setChangeListener(listenetAceptar);
		add(btnfldOk);
		addKeyListener(new ListenerKey());
	}
	
	private FieldChangeListener listenetAceptar = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			if (_txtDescripcion.getTextLength() == 0) {
				Dialog.inform("Todos los campos están vacíos, no se guardará");
				UiApplication.getUiApplication().popScreen(getScreen());
			} else {
				_guardar = true;
				UiApplication.getUiApplication().popScreen(getScreen());
			}
		}
	};
	
	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public boolean isGuardado() {
		return _guardar;
	}
	
	public class ListenerKey implements KeyListener
	 {    
	     public boolean keyChar( char key, int status, int time ) 
	     {
	         return false;
	     }
	     
		public boolean keyDown(int keycode, int time) {
			if (keycode == 1769472) {
				if (_txtDescripcion.getTextLength() != 0) {
					Object[] ask = { "Guardar", "Descartar", "Cancelar" };
					int sel = Dialog.ask("Se han detectado cambios", ask, 2);
					if (sel == 0) {
						_guardar = true;
						UiApplication.getUiApplication().popScreen(getScreen());
						return true;
					} else if (sel == 1) {
						UiApplication.getUiApplication().popScreen(getScreen());
						return true;
					} else if (sel == 2) {
						return true;
					}
				} else {
					UiApplication.getUiApplication().popScreen(getScreen());
					return true;
				}
			} else {
				return false;
			}
			return true;
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}

		public boolean keyUp(int keycode, int time) {
			return false;
		}
	 }
}
