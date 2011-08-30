package gui;

import gui.Listados.ListadoPersonasLista;

import java.util.Vector;

import net.rim.device.api.ui.DrawStyle;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import persistence.Persistence;
import core.Persona;

public class PersonasManager extends CustomManager {

	private LabelField _nombre;
	private LabelField _cedula;
	private LabelField _telefono;
	private LabelField _direccion;
	private LabelField _correo;
	private LabelField _notas;

	public PersonasManager() {
		super();
		setTitle("Personas");
		Persistence p;
		_lista = new ListadoPersonasLista();
		try {
			p = new Persistence();
			Vector v = p.consultarPersonas();
			_lista.loadFrom(v);
		} catch (Exception e) {
			Dialog.alert(e.toString());
		}
		setLista(_lista, 1);

		HorizontalFieldManager nombre = new HorizontalFieldManager();
		HorizontalFieldManager cedula = new HorizontalFieldManager();
		HorizontalFieldManager telefono = new HorizontalFieldManager();
		HorizontalFieldManager direccion = new HorizontalFieldManager();
		HorizontalFieldManager correo = new HorizontalFieldManager();
		HorizontalFieldManager notas = new HorizontalFieldManager();

		nombre.add(new LabelField("Nombre: "));
		nombre.getField(0).setFont(_bold);
		cedula.add(new LabelField("Cédula: "));
		cedula.getField(0).setFont(_bold);
		telefono.add(new LabelField("Teléfono: "));
		telefono.getField(0).setFont(_bold);
		direccion.add(new LabelField("Dirección: "));
		direccion.getField(0).setFont(_bold);
		correo.add(new LabelField("Correo: "));
		correo.getField(0).setFont(_bold);
		notas.add(new LabelField("Notas: "));
		notas.getField(0).setFont(_bold);

		_nombre = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_cedula = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_telefono = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_direccion = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_correo = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);
		_notas = new LabelField("", DrawStyle.RIGHT | Field.USE_ALL_WIDTH);

		nombre.add(_nombre);
		cedula.add(_cedula);
		telefono.add(_telefono);
		direccion.add(_direccion);
		correo.add(_correo);
		notas.add(_notas);

		_vertical.add(nombre);
		_vertical.add(new SeparatorField());
		_vertical.add(cedula);
		_vertical.add(new SeparatorField());
		_vertical.add(telefono);
		_vertical.add(new SeparatorField());
		_vertical.add(direccion);
		_vertical.add(new SeparatorField());
		_vertical.add(correo);
		_vertical.add(new SeparatorField());
		_vertical.add(notas);

		add(_manager);
		add(_title);
		add(_vertical);
	}

	protected void rightUpdate() {
		try {
			Persona p = (Persona) _lista.getSelectedElement();
			if (p == null) {
				p = (Persona) _lista.getElementAt(0);
			}
			_nombre.setText(p.getNombre());
			_cedula.setText(p.getId());
			_telefono.setText(p.getTelefono());
			_direccion.setText(p.getDireccion());
			_correo.setText(p.getCorreo());
			_notas.setText(p.getNotas());
			_vertical.invalidate();
		} catch (NullPointerException e) {

		}
	}
}
