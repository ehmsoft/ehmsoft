package gui;

import java.util.Vector;

import core.Persona;

import persistence.Persistence;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FocusChangeListener;
import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class PersonasManager extends Manager{
	
	private ListadoPersonasLista _lista;
	private ListadoManager _manager;
	private LabelField _title;
	private LabelField _nombre;
	private LabelField _cedula;
	private LabelField _telefono;
	private LabelField _direccion;
	private LabelField _correo;
	private LabelField _notas;
	
	private VerticalFieldManager _vertical;

	public PersonasManager(){
		super(0);
		Persistence p;
		_vertical = new VerticalFieldManager();
		_lista = new ListadoPersonasLista();
		_title = new LabelField("Personas", Manager.FIELD_HCENTER);
		_nombre = new LabelField();
		_cedula = new LabelField();
		_telefono = new LabelField();
		_direccion = new LabelField();
		_correo = new LabelField();
		_notas = new LabelField();

		try {
			p = new Persistence();
			Vector v = p.consultarPersonas();
			_lista.loadFrom(v);
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		
		_lista.setFont(_lista.getFont().derive(_lista.getFont().getStyle(), _lista.getFont().getHeight() - 10));
		_lista.setRowHeight(_lista.getFont().getHeight());
		_vertical.add(_nombre);
		_vertical.add(_cedula);
		_vertical.add(_telefono);
		_vertical.add(_direccion);
		_vertical.add(_correo);
		_vertical.add(_notas);
		
		_manager = new ListadoManager(_lista);
		_lista.setFocusListener(listenerLista);
		add(_manager);
		add(_title);
		add(_vertical);
	}
	
	public void setFocus() {
		_manager.setFocus();
	}
	
	FocusChangeListener listenerLista = new FocusChangeListener() {
		
		public void focusChanged(Field field, int eventType) {
			rightUpdate();
		}
	};
	
	void rightUpdate() {
		Persona p = (Persona) _lista.getSelectedElement();
		if(p == null) {
			p = (Persona) _lista.getElementAt(0);
		}
			_nombre.setText(p.getNombre());
			_cedula.setText(p.getId());
			_telefono.setText(p.getTelefono());
			_direccion.setText(p.getDireccion());
			_correo.setText(p.getCorreo());
			_notas.setText(p.getNotas());
			_vertical.invalidate();
	}

	protected void sublayout(int width, int height) {
		//layoutChild(_lista, width / 2, Display.getHeight());
		layoutChild(_manager, width / 2, Display.getHeight());
		layoutChild(_title, width / 2, getFont().getHeight());
		layoutChild(_vertical, width /2, height - (getFont().getHeight() * 2));
		
		//setPositionChild(_lista, 0, 0);
		setPositionChild(_manager, 0, 0);
		setPositionChild(_title, width / 2, 0);
		setPositionChild(_vertical, width / 2, getFont().getHeight() * 2 + 10);
		
		setExtent(Display.getWidth() - 64, Display.getHeight() - 48);
	}
	
	protected void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, getWidth() / 2, getHeight());
	}
}
