package gui;

import core.Persona;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ListadoPersonasPopUp extends PopupScreen {
	
	public static final int ON_CLICK_VER = 64;
	public static final int ON_CLICK_SELECT = 128;
	public static final int NO_NUEVO = 256;

	private Object _selected;
	private ListadoPersonasLista _lista;
	private int _tipo;
	private LabelField _title;
	private long _style;

	public ListadoPersonasPopUp(int tipo) {
		super(new VerticalFieldManager());
		_tipo = tipo;

		if (tipo == 1) {
			_title = new LabelField("Demandantes",
					Field.FIELD_HCENTER);

		} else if (tipo == 2) {
			_title = new LabelField("Demandados",
					Field.FIELD_HCENTER);
		}
		add(_title);
		add(new SeparatorField());

		_lista = new ListadoPersonasLista();

		if (tipo == 1)
			_lista.insert(0, "Crear nuevo demandante");
		else
			_lista.insert(0, "Crear nuevo demandado");
		add(_lista);
		addKeyListener(new ListenerKey());
	}
	
	public ListadoPersonasPopUp(int tipo, long style) {
		super(new VerticalFieldManager());
		_style = style;
		_lista = new ListadoPersonasLista(style);
		
		if ((_style & NO_NUEVO) != NO_NUEVO) {
			if (tipo == 1)
				_lista.insert(0, "Crear nuevo demandante");
			else
				_lista.insert(0, "Crear nuevo demandado");
		}
		add(_lista);
		addKeyListener(new ListenerKey());
	}
	
	protected boolean navigationClick(int status, int time) {
		if (String.class.isInstance(_lista.get(_lista,
				_lista.getSelectedIndex()))) {
			onNew();
			return true;
		} else {
			onClick();
			return true;
		}
	}
	
	private void onNew() {
		NuevaPersona n = new NuevaPersona(_tipo);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		try {
			if((_style & NO_NUEVO) == NO_NUEVO) {
				_lista.insert(0, n.getPersona());
				_lista.setSelectedIndex(0);
			} else {
				_lista.insert(1, n.getPersona());
				_lista.setSelectedIndex(0);
			}
		} catch(Exception e) {
			
		} finally {
			n = null;
		}
	}
	
	private void onClick() {
		if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			int index = _lista.getSelectedIndex();
			VerPersona verPersona = new VerPersona((Persona) _lista.get(_lista,
					index));
			UiApplication.getUiApplication().pushModalScreen(
					verPersona.getScreen());
			verPersona.actualizarPersona();
			_lista.delete(index);
			_lista.insert(index, verPersona.getPersona());
			_lista.setSelectedIndex(index);
		} else {
			_selected = _lista.get(_lista, _lista.getSelectedIndex());
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}

	public void addPersona(Object persona) {
		_lista.insert(_lista.getSize(), persona);
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
