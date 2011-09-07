package gui;

import gui.Listados.ListadoActuacionesLista;

import java.util.Vector;

import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.component.BitmapField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;
import persistence.Persistence;
import core.Actuacion;

public class ActuacionesManager {

	private VerticalFieldManager _vertical;
	private ListadoActuacionesLista _lista;
	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;
	private BitmapField _cita;
	private BitmapField _alarma;

	public ActuacionesManager(int cant) {
		_vertical = new VerticalFieldManager();
		_vertical.setFont(_vertical.getFont().derive(
				_vertical.getFont().getStyle(),
				_vertical.getFont().getHeight() - 5));
		Persistence p;
		_lista = new ListadoActuacionesLista();
		_lista.setFont(_lista.getFont().derive(_lista.getFont().getStyle(),
				_lista.getFont().getHeight() - 8));
		_lista.setRowHeight(_lista.getFont().getHeight() * 2);

		try {
			p = new Persistence();
			Vector v = p.consultarActuacionesCriticas(cant);
			_lista.loadFrom(v);
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		_lista.setFocusListener(listener);

		HorizontalFieldManager juzgado = new HorizontalFieldManager();
		HorizontalFieldManager fecha = new HorizontalFieldManager();
		VerticalFieldManager fechas = new VerticalFieldManager();
		HorizontalFieldManager fechaP = new HorizontalFieldManager();
		HorizontalFieldManager descripcion = new HorizontalFieldManager();
		HorizontalFieldManager alarma = new HorizontalFieldManager();

		descripcion.add(new LabelField("Descripción: "));
		juzgado.add(new LabelField("Juzgado: "));
		fecha.add(new LabelField("Fecha: "));
		fechas.add(new LabelField("Fecha"));
		fechas.add(new LabelField("próxima:"));
		fechaP.add(fechas);

		_juzgado = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_fecha = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_fechaProxima = new LabelField("", DrawStyle.RIGHT
				| Field.USE_ALL_WIDTH);
		_descripcion = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);

		juzgado.add(_juzgado);
		fecha.add(_fecha);
		fechaP.add(_fechaProxima);
		descripcion.add(_descripcion);

		_vertical.add(descripcion);
		_vertical.add(new SeparatorField());
		_vertical.add(juzgado);
		_vertical.add(new SeparatorField());
		_vertical.add(fecha);
		_vertical.add(new SeparatorField());
		_vertical.add(fechaP);
		_vertical.add(new SeparatorField());
		_vertical.add(alarma);
	}

	private FocusChangeListener listener = new FocusChangeListener() {

		public void focusChanged(Field field, int context) {
			try {
				Actuacion a = (Actuacion) _lista.getSelectedElement();
				if (a == null) {
					a = (Actuacion) _lista.getElementAt(0);
				}
				_descripcion.setText(a.getDescripcion());
				_juzgado.setText(a.getJuzgado().getNombre());
				String fecha = Util.calendarToString(a.getFecha(), true);
				fecha = fecha.substring(0, 10) + "\n" + fecha.substring(11);
				_fecha.setText(fecha);
				fecha = Util.calendarToString(a.getFechaProxima(), true);
				fecha = fecha.substring(0, 10) + "\n" + fecha.substring(11);
				_fechaProxima.setText(fecha);
				_cita.setBitmap(null);
				_alarma.setBitmap(null);
				_vertical.invalidate();
			} catch (NullPointerException e) {
			}
		}
	};

	public VerticalFieldManager getRight() {
		return _vertical;
	}

	public ListadoActuacionesLista getLeft() {
		return _lista;
	}
}
