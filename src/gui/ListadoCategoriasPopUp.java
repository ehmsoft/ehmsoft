package gui;

import core.Categoria;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ListadoCategoriasPopUp extends PopupScreen {

	private Categoria _selected;
	private ListadoCategoriasLista _lista;
	private LabelField _title;
	
	public ListadoCategoriasPopUp() {
		super(new VerticalFieldManager());
		_title = new LabelField("Categorias",
				Field.FIELD_HCENTER);
		add(_title);
		add(new SeparatorField());
		
		_lista = new ListadoCategoriasLista() {
			protected boolean navigationClick(int status, int time) {
				if (String.class.isInstance(get(_lista, getSelectedIndex()))) {
					NuevaCategoria n = new NuevaCategoria(true);
					UiApplication.getUiApplication().pushModalScreen(
							n.getScreen());
					try {
						_lista.insert(1, n.getCategoria());
						_lista.setSelectedIndex(1);
					} catch (Exception e) {
						return true;
					}
					return true;
				} else {
					_selected = (Categoria) get(_lista, getSelectedIndex());
					UiApplication.getUiApplication().popScreen(getScreen());
					return true;
				}
			}
		};
		
		_lista.insert(0, "Nueva categoría");
		add(_lista);
		addKeyListener(new ListenerKey());
	}
	
	public void addCategoria(Categoria categoria) {
		_lista.insert(_lista.getSize(), categoria);
	}

	public Categoria getSelected() {
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
