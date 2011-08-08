package gui;

import java.util.Enumeration;
import java.util.Vector;

import persistence.Persistence;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class ActuacionesManager extends Manager{
	
	private ListadoActuacionesLista _lista;
	private LabelField _title;
	private LabelField _juzgado;
	private LabelField _fecha;
	private LabelField _fechaProxima;
	private LabelField _descripcion;
	private VerticalFieldManager _vertical;

	public ActuacionesManager(){
		super(0);
		Persistence p;
		_vertical = new VerticalFieldManager();
		_lista = new ListadoActuacionesLista();
		_title = new LabelField("Actuaciones críticas", FIELD_HCENTER);
		_juzgado = new LabelField();
		_fecha = new LabelField();
		_fechaProxima = new LabelField();
		_descripcion = new LabelField();
		try {
			p = new Persistence();
			Vector v = p.consultarActuacionesCriticas(10);
			Enumeration e = v.elements();
			while(e.hasMoreElements()) {
				_lista.insert(e.nextElement());
			}
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		
		_lista.setFont(_lista.getFont().derive(_lista.getFont().getStyle(), _lista.getFont().getHeight() - 10));
		_lista.setRowHeight(_lista.getFont().getHeight() * 2);
		_vertical.add(_descripcion);
		_vertical.add(_juzgado);
		_vertical.add(_fecha);
		_vertical.add(_fechaProxima);
		
		add(_lista);
		add(_title);
		add(_vertical);
	}	

	protected void sublayout(int width, int height) {
		layoutChild(_lista, width / 2, Display.getHeight());
		layoutChild(_title, width / 2, getFont().getHeight());
		layoutChild(_vertical, width /2, height - getFont().getHeight());
		
		setPositionChild(_lista, 0, 0);
		setPositionChild(_title, width / 2, 0);
		setPositionChild(_vertical, width / 2, getFont().getHeight());
		
		setExtent(Display.getWidth() - 64, Display.getHeight() - 48);
	}
}
