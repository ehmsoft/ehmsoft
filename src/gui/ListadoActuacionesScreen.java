package gui;

import persistence.Persistence;

import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.MainScreen;
import core.Actuacion;
import core.Proceso;

public class ListadoActuacionesScreen extends MainScreen {

	public static final int ON_CLICK_VER = 4;
	public static final int ON_CLICK_SELECT = 5;

	private Object _selected;
	private ListadoActuacionesLista _lista;
	private Proceso _proceso;
	private boolean _onClickVer;

	public ListadoActuacionesScreen(Proceso proceso) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_proceso = proceso;
		setTitle("Nueva actuaci�n");
		_lista = new ListadoActuacionesLista();
		_lista.insert(0, "Nueva actuaci�n");
		add(_lista);
	}

	public ListadoActuacionesScreen(Proceso proceso, int[] style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_proceso = proceso;
		_lista = new ListadoActuacionesLista(style);
		_lista.insert(0, "Nueva actuaci�n");
		add(_lista);
	}

	public ListadoActuacionesScreen(int[] style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		for (int i = 0; i < style.length; i++) {
			int s = style[i];
			if (s == ON_CLICK_VER) {
				_onClickVer = true;
			} else if (s == ON_CLICK_SELECT) {
				_onClickVer = false;
			}
		}
		_lista = new ListadoActuacionesLista(style);
		add(_lista);
	}

	public ListadoActuacionesScreen() {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		_lista = new ListadoActuacionesLista();
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
		NuevaActuacion n = new NuevaActuacion(_proceso);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		try {
			_lista.insert(1, n.getActuacion());
		} catch (Exception e) {
		}
	}

	private void onClick() {
		if (_onClickVer) {
			onClickView();
		} else {
			onClickSelect();
		}
	}

	private void onClickSelect() {
		_selected = _lista.get(_lista, _lista.getSelectedIndex());
		UiApplication.getUiApplication().popScreen(getScreen());
	}

	private void onClickView() {
		menuVer.run();
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
			VerActuacion verActuacion = new VerActuacion(
					(Actuacion) _lista.get(_lista, index));
			UiApplication.getUiApplication().pushModalScreen(
					verActuacion.getScreen());
			try {
				verActuacion.actualizarActuacion();
				_lista.delete(index);
				_lista.insert(index, verActuacion.getActuacion());
				_lista.setSelectedIndex(index);
			} catch (Exception e) {
				_lista.delete(index);
			}
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("�Desdea eliminar la actuaci�n?", ask, 1);
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				int index = _lista.getSelectedIndex();
				try {
					persistence.borrarActuacion((Actuacion) _lista.get(_lista,
							index));
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				_lista.delete(index);
			}
		}
	};

	public void addActuacion(Object actuacion) {
		_lista.insert(_lista.getSize(), actuacion);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
