package gui;

import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ListadoJuzgadosPopUp extends PopupScreen {

	private Object _selected;
	private ListadoJuzgadosLista _lista;
	private LabelField _title;
	
	public ListadoJuzgadosPopUp() {
		super(new VerticalFieldManager());

		LabelField _title = new LabelField("Juzgados",
				Field.FIELD_HCENTER);
		add(_title);
		add(new SeparatorField());

		_lista = new ListadoJuzgadosLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevoJuzgado n = new NuevoJuzgado();
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						_lista.insert(1, n.getJuzgado());
						_lista.setSelectedIndex(1);
					} catch (Exception e) {
						return true;
					}
					return true;
				} else {
					_selected = get(_lista, getSelectedIndex());
					UiApplication.getUiApplication().popScreen(getScreen());
					return true;
				}
			}
		};

		_lista.insert(0, "Crear nuevo juzgado");
		add(_lista);
		addKeyListener(new ListenerKey());
	}

	public void addJuzgado(Object juzgado) {
		_lista.insert(_lista.getSize(), juzgado);
	}

	public Object getSelected() {
		return _selected;
	}
	
	public void setTitle(String title) {
		_title.setText(title);
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

