package ehmsoft;

import gui.ActuacionesManager;
import gui.CustomFieldManager;
import gui.ListaCircular;
import gui.PersonasManager;
import gui.Listados.ListadoCampos;
import gui.Listados.ListadoCategorias;
import gui.Listados.ListadoJuzgados;
import gui.Listados.ListadoPersonas;
import gui.Listados.ListadoProcesos;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

/**
 * A class extending the MainScreen class, which provides default standard
 * behavior for BlackBerry GUI applications.
 */
public class ScreenMain extends MainScreen {
	/**
	 * Creates a new screenMain object
	 */
	CustomFieldManager _middle;	
	
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
	}

	protected void makeMenu(Menu menu, int instance) {
		menu.add(menuListas);
	}
	
	private final MenuItem menuListas = new MenuItem("Listado",0, 0) {
		
		public void run() {
			UiApplication.getUiApplication().pushModalScreen(new Listados());
		}
	};
}

class Listados extends PopupScreen {
	
	private ObjectListField _lista;
	
	public Listados() {
		super(new VerticalFieldManager());
		add(new LabelField("Seleccione un listado", FIELD_HCENTER));
		add(new SeparatorField());
		
		_lista = new ObjectListField();
		
		Object[] o = {"Demandantes", "Demandados", "Juzgados","Campos personalizados", 
				"Categorías", "Procesos"};
		
		_lista.set(o);
		add(_lista);
		addKeyListener(new ListenerKey());
	}
	
	
	
	protected boolean navigationClick(int arg0, int arg1) {
		String s = (String)_lista.get(_lista,_lista.getSelectedIndex());
		if(s.equals("Demandantes")) {
			UiApplication.getUiApplication().popScreen(getScreen());
			ListadoPersonas l = new ListadoPersonas(1, ListadoPersonas.ON_CLICK_VER | ListadoPersonas.SEARCH);
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		} else if (s.equals("Demandados")) {
			UiApplication.getUiApplication().popScreen(getScreen());
			ListadoPersonas l = new ListadoPersonas(2, ListadoPersonas.ON_CLICK_VER);
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		} else if (s.equals("Juzgados")) {
			UiApplication.getUiApplication().popScreen(getScreen());
			ListadoJuzgados l = new ListadoJuzgados();
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		} else if (s.equals("Campos personalizados")) {
			UiApplication.getUiApplication().popScreen(getScreen());
			ListadoCampos l = new ListadoCampos();
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		} else if (s.equals("Categorias")) {
			UiApplication.getUiApplication().popScreen(getScreen());
			ListadoCategorias l = new ListadoCategorias();
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		} else if (s.equals("Procesos")) {
			UiApplication.getUiApplication().popScreen(getScreen());
			ListadoProcesos l = new ListadoProcesos();
			UiApplication.getUiApplication().pushModalScreen(l.getScreen());
		}
		return super.navigationClick(arg0, arg1);
	}



	public class ListenerKey implements KeyListener
	 {    
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
