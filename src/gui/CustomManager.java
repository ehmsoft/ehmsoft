package gui;

import gui.Listados.ListadoActuacionesLista;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public abstract class CustomManager extends Manager{
	
	protected ListaListas _lista;
	protected ListadoManager _manager;
	protected VerticalFieldManager _title;
	protected VerticalFieldManager _vertical;
	protected Font _bold;
	
	public CustomManager() {
		super(0);
		Font t = getFont();
		int s = t.getStyle();
		int h = t.getHeight();
		int r = (int) (Display.getHeight() * 0.02777);
		t = t.derive(s, h - r);
		setFont(t);
		_bold = getFont().derive(Font.BOLD);
		_vertical = new VerticalFieldManager(Field.NON_FOCUSABLE);
		_lista = new ListadoActuacionesLista();
		_title = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH);
		_title.add(new LabelField("Manager generico", Field.FIELD_HCENTER));
		_title.add(new SeparatorField());
		_title.setFont(_bold);
	}
	
	FocusChangeListener listenerLista = new FocusChangeListener() {

		public void focusChanged(Field field, int eventType) {
			rightUpdate();
		}
	};

	protected void rightUpdate() {
	}
	
	protected void setTitle(String title) {
		((LabelField)_title.getField(0)).setText(title);
	}
	
	protected void setLista(ListaListas lista, int rows) {
		_lista = lista;
		Font t = _lista.getFont();
		int s = t.getStyle();
		int h = t.getHeight();
		int r = (int) (Display.getHeight() * 0.02777);
		t = t.derive(s, h - r);

		Border border = BorderFactory.createRoundedBorder(new XYEdges(r, r, r,
				r));

		_lista.setFont(t);
		_lista.setRowHeight(_lista.getFont().getHeight() * rows);
		_lista.setFocusListener(listenerLista);
		_lista.setSelectedIndex(0);
		_manager = new ListadoManager(_lista);
		_manager.setBorder(border);
	}
	
	protected void sublayout(int width, int height) {
		int p = (int) (Display.getHeight() * 0.02777);
		int w = (int) (Display.getWidth() * 0.0333333333333);
		int h = (int) (Display.getHeight() * 0.133333333);

		layoutChild(_manager, (width / 2), Display.getHeight() - h - (w * 2));
		layoutChild(_title, (width / 2) - (p * 2), getFont().getHeight()
				+ (p / 2));
		layoutChild(_vertical, (width / 2) - (p * 2), height
				- (getFont().getHeight() * 2));

		setPositionChild(_manager, 0, 0);
		setPositionChild(_title, (width / 2) + p, 0);
		setPositionChild(_vertical, (width / 2) + p, getFont().getHeight() * 2
				+ ((p / 2) + p));

		setExtent(Display.getWidth() - (w * 2), Display.getHeight() - h
				- (w * 2));
	}
}

class ListadoManager extends Manager {

	ListaListas _lista;

	public ListadoManager(ListaListas l) {
		super(Manager.VERTICAL_SCROLL | Manager.VERTICAL_SCROLLBAR);
		_lista = l;
		add(_lista);
		_lista.setSelectedIndex(0);
	}

	protected void sublayout(int width, int height) {
		int p = (int) (Display.getHeight() * 0.02777);
		int w = (int) (Display.getWidth() * 0.0333333333333);
		int h = (int) (Display.getHeight() * 0.133333333);

		layoutChild(_lista, width + p, Display.getHeight() - h - (w * 2) - (2 * p));
		setPositionChild(_lista, 0, 0);
		setExtent((Display.getWidth() - (w * 2)) / 2 - (p * 3),
				Display.getHeight() - h - (w * 2) - (2 * p));
	}
}
