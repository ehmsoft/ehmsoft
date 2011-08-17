package gui;

import persistence.Persistence;

import net.rim.blackberry.api.pdap.BlackBerryEvent;
import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.MenuItem;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Menu;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.MainScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;
import core.Actuacion;
import core.CalendarManager;
import core.Proceso;

public class ListadoActuacionesScreen extends MainScreen {

	public static final int ON_CLICK_VER = 1;
	public static final int ON_CLICK_SELECT = 2;
	public static final int NO_NUEVO = 4;
	public static final int SEARCH = 8;

	private Object _selected;
	private ListadoActuacionesLista _lista;
	private Proceso _proceso;
	private long _style;
	private BitmapField _cita;
	private BitmapField _alarm;
	private Bitmap _bell = Bitmap.getBitmapResource("bell.png");
	private Bitmap _clock = Bitmap.getBitmapResource("clock.png");

	public ListadoActuacionesScreen(Proceso proceso, long style) {
		super(MainScreen.VERTICAL_SCROLL | MainScreen.VERTICAL_SCROLLBAR);
		HorizontalFieldManager h = new HorizontalFieldManager();
		VerticalFieldManager r = new VerticalFieldManager(Field.USE_ALL_WIDTH);
		VerticalFieldManager l = new VerticalFieldManager();
		HorizontalFieldManager r1 = new HorizontalFieldManager(Field.FIELD_RIGHT);
		_cita = new BitmapField(null);
		_alarm = new BitmapField(null);
		
		r1.add(_cita);
		r1.add(_alarm);
		
		l.add(new LabelField("Actuaciones"));
		r.add(r1);		

		h.add(l);
		h.add(r);
		setTitle(h);
		
		_style = style;
		_proceso = proceso;
		_lista = new ListadoActuacionesLista();

		if ((_style & NO_NUEVO) != NO_NUEVO && _proceso != null) {
			_lista.insert(0, "Nueva actuación");
		}
		if ((_style & SEARCH) == SEARCH) {
			add(_lista.getKeywordField());
		}
		add(_lista);
		_lista.setFocusListener(listenerLista);
	}

	public ListadoActuacionesScreen(long style) {
		this(null, style);
	}
	
	public ListadoActuacionesScreen(Proceso proceso) {
		this(proceso, 0);
	}

	public ListadoActuacionesScreen() {
		this(null, 0);
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
	
	FocusChangeListener listenerLista = new FocusChangeListener() {

		public void focusChanged(Field field, int eventType) {
			try {
				if (!String.class.isInstance(_lista.getSelectedElement())) {
					Actuacion a = (Actuacion) _lista.getSelectedElement();
					_cita.setBitmap(null);
					_alarm.setBitmap(null);
					if (a.getUid() != null) {
						BlackBerryEvent e = CalendarManager.consultarCita(a
								.getUid());
						if (e != null) {
							_cita.setBitmap(_clock);
							if (e.countValues(BlackBerryEvent.ALARM) > 0) {
								_alarm.setBitmap(_bell);
							}
						}
					}
				}
			} catch (Exception e) {

			}
		}
	};

	private void onNew() {
		NuevaActuacion n = new NuevaActuacion(_proceso);
		UiApplication.getUiApplication().pushModalScreen(n.getScreen());
		try {
			_lista.insert(1, n.getActuacion());
		} catch (Exception e) {
		}
	}

	private void onClick() {
		if ((_style & ON_CLICK_VER) == ON_CLICK_VER) {
			onClickVer();
		} else {
			onClickSelect();
		}
	}

	private void onClickSelect() {
		_selected = _lista.getSelectedElement();
		UiApplication.getUiApplication().popScreen(getScreen());
	}

	private void onClickVer() {
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
			Actuacion old = (Actuacion) _lista.getSelectedElement();
			VerActuacion v = new VerActuacion(old);
			UiApplication.getUiApplication().pushModalScreen(v.getScreen());
			try {
				v.actualizarActuacion();
				Actuacion nw = v.getActuacion();
				_lista.update(old, nw);
				_lista.getFocusListener().focusChanged(null, 0);
			} catch (Exception e) {
				_lista.remove(old);
			}
		}
	};

	private final MenuItem menuDelete = new MenuItem("Eliminar", 0, 0) {

		public void run() {
			Object[] ask = { "Si", "No" };
			int sel = Dialog.ask("¿Desdea eliminar la actuación?", ask, 1);
			if (sel == 0) {
				Persistence persistence = null;
				try {
					persistence = new Persistence();
					persistence.borrarActuacion((Actuacion) _lista
							.getSelectedElement());
				} catch (Exception e) {
					Dialog.alert(e.toString());
				}
				int index = _lista.getIndex(_lista.getSelectedElement());
				_lista.remove(index);
				_lista.setSelectedIndex(index);
			}
		}
	};

	public void addActuacion(Object actuacion) {
		_lista.insert(actuacion);
	}

	public Object getSelected() {
		return _selected;
	}

	public boolean onClose() {
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
