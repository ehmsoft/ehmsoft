package ehmsoft;

import gui.ActuacionesManager;
import gui.CustomFieldManager;
import gui.ListaCircular;
import gui.PersonasManager;
import gui.Util;
import gui.Listados.ListadoCampos;
import gui.Listados.ListadoCategorias;
import gui.Listados.ListadoJuzgados;
import gui.Listados.ListadoPersonas;
import gui.Listados.ListadoProcesos;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
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
		menu.add(menuNuevos);
		menu.add(menuPreferencias);
	}
	
	private final MenuItem menuListas = new MenuItem("Listado",0, 0) {
		
		public void run() {
			UiApplication.getUiApplication().pushModalScreen(new Listados(Listados.LISTA));
		}
	};
	
	private final MenuItem menuNuevos = new MenuItem("Nuevo", 0, 0) {
		
		public void run() {
			UiApplication.getUiApplication().pushModalScreen(new Listados(Listados.NUEVO));
		}
	};
	
	private final MenuItem menuPreferencias = new MenuItem("Preferencias", 0, 0) {
		
		public void run() {
			Dialog.alert("En desarrollo en la otra rama");
		}
	};
}

class Listados extends PopupScreen {
	
	private ObjectListField _lista;
	private int _style;
	
	public static final short LISTA = 1;
	public static final short NUEVO = 2;
	
	public Listados(int style) {
		super(new VerticalFieldManager());
		_style = style;
		_lista = new ObjectListField();
		if((_style & LISTA) == LISTA) {
			add(new LabelField("Ver listado de:", FIELD_HCENTER));
			Object[] o = {"Demandantes", "Demandados", "Juzgados", "Campos personalizados", 
					"Categorías", "Procesos"};
			_lista.set(o);
		} else if((_style & NUEVO) == NUEVO) {
			add(new LabelField("Crear:", FIELD_HCENTER));
			Object[] o = {"Demandante", "Demandado", "Juzgado", "Campos personalizado", 
					"Categoría", "Proceso"};
			_lista.set(o);
		}
		add(new SeparatorField());
		add(_lista);
	}	
	
	protected boolean navigationClick(int arg0, int arg1) {
		int index = _lista.getSelectedIndex();
		UiApplication.getUiApplication().popScreen(this);
		if (index == 0) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoPersonas(1, false, ListadoPersonas.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaPersona(1);
			}
		} else if (index == 1) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoPersonas(2, false, ListadoPersonas.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaPersona(2);
			}
		} else if (index == 2) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoJuzgados(false, ListadoJuzgados.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevoJuzgado();
			}
		} else if (index == 3) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoCampos(false, ListadoCampos.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevoCampoPersonalizado();
			}
		} else if (index == 4) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoCategorias(false, ListadoCategorias.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevaCategoria(false);
			}
		} else if (index == 5) {
			if ((_style & LISTA) == LISTA) {
				Util.listadoProcesos(false, ListadoProcesos.ON_CLICK_VER);
			} else if ((_style & NUEVO) == NUEVO) {
				Util.nuevoProceso();
			}
		}
		return true;
	}
	
	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(this);
		return true;
	}
}
