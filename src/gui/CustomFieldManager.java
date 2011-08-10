package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class CustomFieldManager extends Manager {	

	private CustomButtonField _left, _right;
	private VerticalFieldManager _manager;
	private Nodo _nodo;

	public CustomFieldManager(ListaCircular lista) {
		super(0);
		_nodo = lista.getElement();
		_nodo = _nodo.getSiguiente();
		_manager = new VerticalFieldManager();
		_manager.add((Manager) _nodo.getElement());
		
		_right = new CustomButtonField(Field.FOCUSABLE, Bitmap.getBitmapResource("right.png"));
		FieldChangeListener _listenRight = new FieldChangeListener() {

			public void fieldChanged(Field field, int context) {
				moveLeft();
				_manager.setFocus();
			}
		};
		_right.setChangeListener(_listenRight);
		
		_left = new CustomButtonField(Field.FOCUSABLE, Bitmap.getBitmapResource("left.png"));		
		FieldChangeListener _listenLeft = new FieldChangeListener() {

			public void fieldChanged(Field field, int context) {
				moveRight();
				_manager.setFocus();
			}
		};
		_left.setChangeListener(_listenLeft);
		
		add(_left);
		add(_manager);
		add(_right);
	}

	protected void sublayout(int width, int height) {
		int w = (int)(Display.getWidth() * 0.0333333333333);
		int h = (int)(Display.getHeight() * 0.133333333);
		
		layoutChild(_left, w , w);
		layoutChild(_right, w, w);
		layoutChild(_manager, width - (w*2), Display.getHeight() - h - (w*2));
		
		setPositionChild(_manager, w, w);
		setPositionChild(_left, 0, (Display.getHeight() / 2) - (w/2) - h);
		setPositionChild(_right, Display.getWidth() - w, (Display.getHeight() / 2) - (w/2) - h);
		setExtent(Display.getWidth(), Display.getHeight() - h);
		}

	public void moveLeft() {
		/*
		 * _nodo = _nodo.getSiguiente(); _manager = (Manager)
		 * _nodo.getElement(); _manager.invalidate();
		 */
		_nodo = _nodo.getSiguiente();
		_manager.deleteAll();
		_manager.add((Manager) _nodo.getElement());
	}
	
	public void moveRight() {
		/*
		 * _nodo = _nodo.getAnterior(); _manager = (Manager) _nodo.getElement();
		 * _manager.invalidate();
		 */
		_nodo = _nodo.getAnterior();
		_manager.deleteAll();
		_manager.add((Manager) _nodo.getElement());
	}
}
