package gui;

import persistence.Persistence;
import core.Categoria;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;

public class ListadoCategoriasScreen extends MainScreen {
	
	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;

	private Categoria _selected;
	private ListadoCategoriasLista _lista;
	private long _style;

	public ListadoCategoriasScreen(long style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_style = style;
		setTitle("Listado de categorías");

		_lista = new ListadoCategoriasLista();
		_lista.setLabel("Buscar: ");

		if ((_style & NO_NUEVO) != NO_NUEVO) {
			_lista.insert(0, "Crear nueva categoría");
		}
		if ((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		add(_lista);
	}
	
	public ListadoCategoriasScreen() {
		this(0);
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
		NuevaCategoria n = new NuevaCategoria();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		try {
			if((_style & NO_NUEVO) == NO_NUEVO) {
				_lista.insert(0, n.getCategoria());
				_lista.setSelectedIndex(0);
			} else {
				_lista.insert(1, n.getCategoria());
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
			_selected = (Categoria) _lista.getSelectedElement();
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
			Categoria old = (Categoria) _lista.getSelectedElement();
			VerCategoria v = new VerCategoria(old);
			UiApplication.getUiApplication().pushModalScreen(
					v.getScreen());
			v.actualizarCategoria();
			Categoria nw = v.getCategoria();
			_lista.update(old, nw);
			if (_lista.getKeywordField().getTextLength() != 0) {
				_lista.setText(nw.getDescripcion());
			}
			old = null;
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desdea eliminar la categoría?", ask, 1);
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
					persistence.borrarCategoria((Categoria) _lista.getSelectedElement());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				
				int index = _lista.getSelectedIndex();
				_lista.remove(index);
				_lista.setSelectedIndex(index);
			}
		}
	};

	public void addCategoria(Categoria categoria) {
		_lista.insert(categoria);
	}

	public Categoria getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
