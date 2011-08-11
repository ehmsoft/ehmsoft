package gui;

import java.util.Vector;

import core.Actuacion;

import persistence.Persistence;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.XYEdges;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import net.rim.device.api.ui.decor.Border;
import net.rim.device.api.ui.decor.BorderFactory;

public class ActuacionesManager extends Manager {

	private ListadoActuacionesLista _lista;
	private ListadoManager _manager;
	private VerticalFieldManager _title;
	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;
	private VerticalFieldManager _vertical;

	public ActuacionesManager(int cant) {
		super(0);
		Persistence p;
		Font t = getFont();
		int s = t.getStyle();
		int h = t.getHeight();
		int r = (int) (Display.getHeight() * 0.02777);
		t = t.derive(s, h - r);
		setFont(t);
		Font bold = getFont().derive(Font.BOLD);
		_vertical = new VerticalFieldManager(Field.NON_FOCUSABLE);
		_lista = new ListadoActuacionesLista();
		_title = new VerticalFieldManager(VerticalFieldManager.USE_ALL_WIDTH);
		_title.add(new LabelField("Actuaciones críticas", Field.FIELD_HCENTER));
		_title.add(new SeparatorField());
		_title.setFont(bold);

		HorizontalFieldManager juzgado = new HorizontalFieldManager();
		HorizontalFieldManager fecha = new HorizontalFieldManager();
		VerticalFieldManager fechas = new VerticalFieldManager();
		HorizontalFieldManager fechaP = new HorizontalFieldManager();
		HorizontalFieldManager descripcion = new HorizontalFieldManager();

		descripcion.add(new LabelField("Descripción: "));
		descripcion.getField(0).setFont(bold);
		juzgado.add(new LabelField("Juzgado: "));
		juzgado.getField(0).setFont(bold);
		fecha.add(new LabelField("Fecha: "));
		fecha.getField(0).setFont(bold);
		fechas.add(new LabelField("Fecha"));
		fechas.setFont(bold);
		fechas.add(new LabelField("próxima: "));
		fechaP.add(fechas);

		_juzgado = new LabelField("", LabelField.RIGHT
				| LabelField.USE_ALL_WIDTH);
		_fecha = new LabelField("", LabelField.RIGHT | LabelField.USE_ALL_WIDTH);
		_fechaProxima = new LabelField("", LabelField.RIGHT
				| LabelField.USE_ALL_WIDTH);
		_descripcion = new LabelField("", LabelField.RIGHT
				| LabelField.USE_ALL_WIDTH);

		juzgado.add(_juzgado);
		fecha.add(_fecha);
		fechaP.add(_fechaProxima);
		descripcion.add(_descripcion);

		try {
			p = new Persistence();
			Vector v = p.consultarActuacionesCriticas(cant);
			_lista.loadFrom(v);
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}

		t = _lista.getFont();
		s = t.getStyle();
		h = t.getHeight();
		r = (int) (Display.getHeight() * 0.02777);
		t = t.derive(s, h - r);

		Border border = BorderFactory.createRoundedBorder(new XYEdges(r, r, r,
				r));

		_lista.setFont(t);
		_lista.setRowHeight(_lista.getFont().getHeight() * 2);
		_vertical.add(descripcion);
		_vertical.add(new SeparatorField());
		_vertical.add(juzgado);
		_vertical.add(new SeparatorField());
		_vertical.add(fecha);
		_vertical.add(new SeparatorField());
		_vertical.add(fechaP);

		_manager = new ListadoManager(_lista);
		_manager.setBorder(border);
		_lista.setFocusListener(listenerLista);
		_lista.setSelectedIndex(0);
		add(_manager);
		add(_title);
		add(_vertical);
	}

	FocusChangeListener listenerLista = new FocusChangeListener() {

		public void focusChanged(Field field, int eventType) {
			rightUpdate();
		}
	};

	void rightUpdate() {
		try {
			Actuacion a = (Actuacion) _lista.getSelectedElement();
			if (a == null) {
				a = (Actuacion) _lista.getElementAt(0);
			}
			_descripcion.setText(a.getDescripcion());
			_juzgado.setText(a.getJuzgado().getNombre());
			String fecha = ListadoActuacionesLista.calendarToString(
					a.getFecha(), true);
			fecha = fecha.substring(0, 10) + "\n" + fecha.substring(11);
			_fecha.setText(fecha);
			fecha = ListadoActuacionesLista.calendarToString(
					a.getFechaProxima(), true);
			fecha = fecha.substring(0, 10) + "\n" + fecha.substring(11);
			_fechaProxima.setText(fecha);
			_vertical.invalidate();
		} catch (NullPointerException e) {
		}
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
		super(Manager.VERTICAL_SCROLL);
		_lista = l;
		add(_lista);
		_lista.setSelectedIndex(0);
	}

	protected void sublayout(int width, int height) {
		int p = (int) (Display.getHeight() * 0.02777);
		int w = (int) (Display.getWidth() * 0.0333333333333);
		int h = (int) (Display.getHeight() * 0.133333333);

		layoutChild(_lista, width + p, Display.getHeight() - h - (w * 2) - p);
		setPositionChild(_lista, 0, 0);
		setExtent((Display.getWidth() - (w * 2)) / 2 - (p * 3),
				Display.getHeight() - h - (w * 2) - 2 * p);
	}

	protected void paint(Graphics g) {
		g.setDrawingStyle(Graphics.DRAWSTYLE_AAPOLYGONS, true);
		super.paint(g);
	}
}
