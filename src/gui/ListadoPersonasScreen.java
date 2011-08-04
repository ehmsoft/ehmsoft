package gui;

import java.util.Vector;

import persistence.Persistence;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.KeywordFilterField;
import net.rim.device.api.ui.component.ListField;
import net.rim.device.api.ui.component.ListFieldCallback;
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
	private ListadoPersonasLista _unsortedList;
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
		_unsortedList = new ListadoPersonasLista();
		_lista.setSourceList(_unsortedList, _unsortedList);
		
		if((_style & NO_NUEVO) != NO_NUEVO) {
			if (tipo == 1)
				_unsortedList.insert(0, "Nuevo demandante");
			else
				_unsortedList.insert(0, "Nuevo demandado");
		}
		if((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		add(_lista);
	}
	
	protected boolean navigationClick(int status, int time) {
		if (String.class.isInstance(_lista.getElementAt(_lista.getSelectedIndex()))) {
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
				_unsortedList.insert(0, n.getPersona());
				_lista.setSelectedIndex(0);
			} else {
				_unsortedList.insert(1, n.getPersona());
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
			int index = _lista.getSelectedIndex();
			VerPersona verPersona = new VerPersona((Persona) _lista.getElementAt(index));
			UiApplication.getUiApplication().pushModalScreen(
					verPersona.getScreen());
			verPersona.actualizarPersona();
			_unsortedList.update(verPersona.getPersona(), verPersona.getPersona());
			_lista.updateList();
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
					persistence.borrarPersona((Persona) _lista.getElementAt(index));
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}

				_lista.delete(index);
			}
		}
	};

	public void addPersona(Object persona) {
		_unsortedList.insert(_unsortedList.getSize(), persona);
		_lista.updateList();
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
