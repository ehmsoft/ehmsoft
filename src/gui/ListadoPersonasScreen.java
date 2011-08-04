package gui;

import persistence.Persistence;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Persona;

public class ListadoPersonasScreen extends MainScreen {

	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;

	private Object _selected;
	private ListadoPersonasLista _lista;
	private int _tipo;
	private long _style;

	public ListadoPersonasScreen(int tipo) {
		this(tipo, 0);
	}

	public ListadoPersonasScreen(int tipo, long style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_style = style;
		_tipo = tipo;
		_lista = new ListadoPersonasLista();
		_lista.setLabel("Buscar: ");

		if ((_style & NO_NUEVO) != NO_NUEVO) {
			if (tipo == 1)
				_lista.insert(0, "Nuevo demandante");
			else
				_lista.insert(0, "Nuevo demandado");
		}
		if ((_style & SEARCH) == SEARCH) {
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
			if((_style & NO_NUEVO) == NO_NUEVO) {
				_lista.insert(0, n.getPersona());
				_lista.setSelectedIndex(0);
			} else {
				_lista.insert(1, n.getPersona());
				_lista.setSelectedIndex(1);
			}
		} catch (Exception e) {

		} finally {
			n = null;
		}
	}

	private void onClick() {
		if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			menuVer.run();
		} else {
			_selected = _lista.getSelectedElement();
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}

	protected void makeMenu(Menu menu, int instance) {
		if (!String.class.isInstance(_lista.getSelectedElement())) {
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
			_lista.update(old, nw);
			if (_lista.getKeywordField().getTextLength() != 0) {
				_lista.setText(nw.getNombre());
			}
			old = null;
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
					persistence.borrarPersona((Persona) _lista
							.getSelectedElement());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}

				int index = _lista.getIndex(_lista
						.getSelectedElement());
				_lista.remove(index);
				_lista.setSelectedIndex(index);
			}
		}
	};

	public void addPersona(Object persona) {
		_lista.insert(persona);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
