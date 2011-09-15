package gui;

import java.util.Enumeration;
import java.util.Vector;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Font;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.VerticalFieldManager;
import persistence.Persistence;
import core.Actuacion;

public class ActuacionesManager {

	private VerticalFieldManager _vertical;
	private ObjectListField _lista;
	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;

	public ActuacionesManager(int cant) {
		_vertical = new VerticalFieldManager();
		_vertical.setFont(_vertical.getFont().derive(
				_vertical.getFont().getStyle(),
				_vertical.getFont().getHeight() - 5));
		_lista = new ObjectListField();
		_lista.setFont(_lista.getFont().derive(_lista.getFont().getStyle(),
				_lista.getFont().getHeight() - 8));
		_lista.setRowHeight(_lista.getFont().getHeight() * 2);

		try {
			Vector v = new Persistence().consultarActuacionesCriticas(cant);
			Enumeration e = v.elements();
			while(e.hasMoreElements()) {
				_lista.insert(_lista.getSize(), e.nextElement());
			}
		} catch (NullPointerException e) {
			Util.noSd();
		} catch (Exception e) {
			Util.alert(e.toString());
		}
		//_lista.setFocusListener(listener);
		
		LabelField lblDescripcion = new LabelField("Descripción: ");
		LabelField lblJuzgado = new LabelField("Juzgado: ");
		LabelField lblFecha = new LabelField("Fecha: ");
		LabelField lblFechaProxima = new LabelField("Fecha próxima: ");
		
		Font bold = _vertical.getFont().derive(Font.BOLD);
		
		lblDescripcion.setFont(bold);
		lblJuzgado.setFont(bold);
		lblFecha.setFont(bold);
		lblFechaProxima.setFont(bold);


		_juzgado = new LabelField();
		_fecha = new LabelField();
		_fechaProxima = new LabelField();
		_descripcion = new LabelField();
		
		_vertical.add(lblDescripcion);
		_vertical.add(_descripcion);
		_vertical.add(new SeparatorField());
		_vertical.add(lblJuzgado);
		_vertical.add(_juzgado);
		_vertical.add(new SeparatorField());
		_vertical.add(lblFecha);
		_vertical.add(_fecha);
		_vertical.add(new SeparatorField());
		_vertical.add(lblFechaProxima);
		_vertical.add(_fechaProxima);
	}

/*	private FocusChangeListener listener = new FocusChangeListener() {

		public void focusChanged(Field field, int context) {
			try {
				Actuacion a = (Actuacion) _lista.get(_lista, _lista.getSelectedIndex());
				if (a == null) {
					a = (Actuacion) _lista.get(_lista, 0);
				}
				_descripcion.setText(a.getDescripcion());
				_juzgado.setText(a.getJuzgado().getNombre());
				String fecha = Util.calendarToString(a.getFecha(), true);
				_fecha.setText(fecha);
				fecha = Util.calendarToString(a.getFechaProxima(), true);
				_fechaProxima.setText(fecha);
				} catch (NullPointerException e) {
			}
		}
	};*/

	public VerticalFieldManager getRight() {
		return _vertical;
	}

	public ObjectListField getLeft() {
		return _lista;
	}
}
