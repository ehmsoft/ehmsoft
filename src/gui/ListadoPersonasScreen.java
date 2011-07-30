package gui;

import persistence.Persistence;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Persona;

public class ListadoPersonasScreen extends MainScreen {
	
	public static final int ON_CLICK_VER = 64;
	public static final int ON_CLICK_SELECT = 128;
	public static final int NO_NUEVO = 256;

	private Object _selected;
	private ListadoPersonasLista _lista;
	private int _tipo;
	private long _style;

	public ListadoPersonasScreen(int tipo) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);

		_tipo = tipo;
		_lista = new ListadoPersonasLista();

		if (tipo == 1)
			_lista.insert(0, "Nuevo demandante");
		else
			_lista.insert(0, "Nuevo demandado");
		add(_lista);
	}
	
	public ListadoPersonasScreen(int tipo, long style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		
		_tipo = tipo;
		_lista = new ListadoPersonasLista(style);
		
		if((_style & NO_NUEVO) != NO_NUEVO) {
			if (tipo == 1)
				_lista.insert(0, "Nuevo demandante");
			else
				_lista.insert(0, "Nuevo demandado");
		}
		add(_lista);
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
		if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			menuVer.run();
		} else {
			_selected = _lista.get(_lista, _lista.getSelectedIndex());
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}
	
	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.get(_lista,
				_lista.getSelectedIndex()))) {
			menu.add(menuVer);
			menu.add(menuDelete);
		}
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			int index = _lista.getSelectedIndex();
			VerPersona verPersona = new VerPersona((Persona) _lista.get(_lista,
					index));
			UiApplication.getUiApplication().pushModalScreen(
					verPersona.getScreen());
			verPersona.actualizarPersona();
			_lista.delete(index);
			_lista.insert(index, verPersona.getPersona());
			_lista.setSelectedIndex(index);
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel;
			if (_tipo == 1) {
				sel = Dialog.ask("¿Desdea eliminar el demandante?", ask, 1);
			} else {
				sel = Dialog.ask("¿Desdea eliminar el demandado?", ask, 1);
			}
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				int index = _lista.getSelectedIndex();
				try {
					persistence.borrarPersona((Persona) _lista.get(_lista,
							index));
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}

				_lista.delete(index);
			}
		}
	};

	public void addPersona(Object persona) {
		_lista.insert(_lista.getSize(), persona);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
