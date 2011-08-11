package gui;

import java.util.Vector;

import core.Actuacion;

import persistence.Persistence;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ActuacionesManager extends CustomManager {

	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;

	public ActuacionesManager(int cant) {
		super();
		setTitle("Actuaciones cr�ticas");
		Persistence p;
		_lista = new ListadoActuacionesLista();
		try {
			p = new Persistence();
			Vector v = p.consultarActuacionesCriticas(cant);
			_lista.loadFrom(v);
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		setLista(_lista,2);

		HorizontalFieldManager juzgado = new HorizontalFieldManager();
		HorizontalFieldManager fecha = new HorizontalFieldManager();
		VerticalFieldManager fechas = new VerticalFieldManager();
		HorizontalFieldManager fechaP = new HorizontalFieldManager();
		HorizontalFieldManager descripcion = new HorizontalFieldManager();

		descripcion.add(new LabelField("Descripci�n: "));
		descripcion.getField(0).setFont(_bold);
		juzgado.add(new LabelField("Juzgado: "));
		juzgado.getField(0).setFont(_bold);
		fecha.add(new LabelField("Fecha: "));
		fecha.getField(0).setFont(_bold);
		fechas.add(new LabelField("Fecha"));
		fechas.setFont(_bold);
		fechas.add(new LabelField("pr�xima: "));
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
		
		_vertical.add(descripcion);
		_vertical.add(new SeparatorField());
		_vertical.add(juzgado);
		_vertical.add(new SeparatorField());
		_vertical.add(fecha);
		_vertical.add(new SeparatorField());
		_vertical.add(fechaP);

		add(_manager);
		add(_title);
		add(_vertical);
	}

	protected void rightUpdate() {
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
}
