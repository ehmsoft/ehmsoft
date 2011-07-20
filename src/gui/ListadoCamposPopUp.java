package gui;

import core.CampoPersonalizado;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ListadoCamposPopUp extends PopupScreen {

	private CampoPersonalizado _selected;
	private ListadoCamposLista _lista;

	public ListadoCamposPopUp() {
		super(new VerticalFieldManager());

			LabelField labelField = new LabelField("Campos personalizados",
					Field.FIELD_HCENTER);
			add(labelField);
			add(new SeparatorField());

		_lista = new ListadoCamposLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevoCampo n = new NuevoCampo();
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						_lista.insert(1, n.getCampo());
					} catch (Exception e) {
						return true;
					}
					return true;
				} else {
					_selected = (CampoPersonalizado)get(_lista, getSelectedIndex());
					UiApplication.getUiApplication().popScreen(getScreen());
					return true;
				}
			}
		};

			_lista.insert(0, "Crear nuevo campo");

		add(_lista);
		addKeyListener(new ListenerKey());
	}

	public void addCampo(CampoPersonalizado campo) {
		_lista.insert(_lista.getSize(), campo);
	}

	public CampoPersonalizado getSelected() {
		return _selected;
	}
	
	public class ListenerKey implements KeyListener
	 {    
	     // Implement methods in the KeyListener interface for handling keyboard events:
	     public boolean keyChar( char key, int status, int time ) 
	     {
	         return false;
	     }
	     
		public boolean keyDown(int keycode, int time) {
			if (keycode == 1769472) {
				UiApplication.getUiApplication().popScreen(getScreen());
				return true;
			} else {
				return false;
			}
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
