package gui;

import core.Juzgado;
import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ListadoJuzgadosPopUp extends PopupScreen {


	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;

	private Juzgado _selected;
	private ListadoJuzgadosLista _lista;
	private LabelField _title;
	private long _style;

	public ListadoJuzgadosPopUp() {
		this(0);
	}

	public ListadoJuzgadosPopUp(long style) {
		super(new VerticalFieldManager(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR));
		_title = new LabelField("Juzgados", FIELD_HCENTER);
		add(_title);
		add(new SeparatorField());
		
		_style = style;
		_lista = new ListadoJuzgadosLista();

		if ((_style & NO_NUEVO) != NO_NUEVO) {
			_lista.insert(0, "Crear nuevo juzgado");
		}
		if((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		add(_lista);
		addKeyListener(new ListenerKey());
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
		NuevoJuzgado n = new NuevoJuzgado();
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		try {
			if((_style & NO_NUEVO) == NO_NUEVO) {
				_lista.insert(0, n.getJuzgado());
				_lista.setSelectedIndex(0);
			} else {
				_lista.insert(1, n.getJuzgado());
				_lista.setSelectedIndex(1);
			}
		} catch (Exception e) {

		} finally {
			n = null;
		}
	}

	private void onClick() {
		if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			Juzgado old = (Juzgado) _lista.getSelectedElement();
			VerJuzgado v = new VerJuzgado(old);
			UiApplication.getUiApplication().pushModalScreen(
					v.getScreen());
			v.actualizarJuzgado();
			Juzgado nw = v.getJuzgado();
			_lista.update(old, nw);
			if (_lista.getKeywordField().getTextLength() != 0) {
				_lista.setText(nw.getNombre());
			}
			old = null;
		} else {
			_selected = (Juzgado)_lista.getSelectedElement();
			UiApplication.getUiApplication().popScreen(getScreen());
		}
	}

	public void addJuzgado(Juzgado juzgado) {
		_lista.insert(juzgado);
	}

	public Juzgado getSelected() {
		return _selected;
	}

	public void setTitle(String title) {
		_title.setText(title);
	}

	public class ListenerKey implements KeyListener {
		// Implement methods in the KeyListener interface for handling keyboard
		// events:
		public boolean keyChar(char key, int status, int time) {
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
