package gui;

import persistence.Persistence;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Persona;

public class ListadoPersonasScreen extends MainScreen {
	
	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;

	private Object _selected;
	private KeywordFilterField _lista;
	private ListadoPersonasLista _sortedList;
	private int _tipo;
	private long _style;

	public ListadoPersonasScreen(int tipo) {
		this(tipo,0);
	}
	
	public ListadoPersonasScreen(int tipo, long style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_style = style;
		_tipo = tipo;
		_lista = new KeywordFilterField();
		//_lista.setKeyword("Buscar: ");
		_lista.setLabel("Buscar: ");
		_sortedList = new ListadoPersonasLista();
		_lista.setSourceList(_sortedList, _sortedList);
		
		if((_style & NO_NUEVO) != NO_NUEVO) {
			if (tipo == 1)
				_sortedList.insert(0,"Nuevo demandante");
			else
				_sortedList.insert(0,"Nuevo demandado");
		}
		if((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		add(_lista);
	}
	
	protected boolean navigationClick(int status, int time) {
		if (String.class.isInstance(_lista.getSelectedElement())) {
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
			Persona p = n.getPersona();
			_sortedList.insert(1,p);
			_lista.updateList();
			_lista.invalidate();
			_lista.setSelectedIndex(1);
		} catch(Exception e) {
			
		} finally {
			n = null;
		}
	}
	
	private void onClick() {
		if((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			menuVer.run();
		} else {
			_selected = _lista.getElementAt(_lista.getSelectedIndex());
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}
	
	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.getElementAt(_lista.getSelectedIndex()))) {
			menu.add(menuVer);
			menu.add(menuDelete);
		}
	}

	private final MenuItem menuVer = new MenuItem("Ver", 0, 0) {

		public void run() {
			Persona old = (Persona) _lista.getSelectedElement();
			VerPersona verPersona = new VerPersona(old);
			UiApplication.getUiApplication().pushModalScreen(
					verPersona.getScreen());
			verPersona.actualizarPersona();
			Persona nw = verPersona.getPersona();
			//_sortedList.delete(old);
			//_sortedList.insert(index ,nw);
			_sortedList.update(old, nw);
			_lista.updateList();
			if(_lista.getKeywordField().getTextLength() != 0) {
				_lista.getKeywordField().setText(nw.getNombre());
				_lista.updateList();
				_lista.invalidate();
			}
			//int index = _sortedList.getIndex(p);
			//_lista.setSelectedIndex(index);
			//_lista.setSelectedIndex(index);
			//_lista.invalidate();
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
				try {
					persistence.borrarPersona((Persona)_lista.getSelectedElement());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				
				int index = _sortedList.getIndex(_lista.getSelectedElement());
				_sortedList.delete(index);
				_lista.updateList();
				_lista.setSelectedIndex(index);
			}
		}
	};

	public void addPersona(Object persona) {
		_sortedList.insert(_sortedList.getSize(), persona);
		_lista.updateList();
		_lista.invalidate();
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
