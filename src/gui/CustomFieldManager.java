package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.Manager;

public class CustomFieldManager extends Manager {
	

	private CustomButtonField _left, _right;
	private ActuacionesManager _manager;

	public CustomFieldManager(ActuacionesManager manager) {
		super(0);
		_manager = manager;
		
		_right = new CustomButtonField(Field.FOCUSABLE, Bitmap.getBitmapResource("right.png"));
		FieldChangeListener _listenRight = new FieldChangeListener() {

			public void fieldChanged(Field field, int context) {
			}
		};
		_right.setChangeListener(_listenRight);
		
		_left = new CustomButtonField(Field.FOCUSABLE, Bitmap.getBitmapResource("left.png"));		
		FieldChangeListener _listenLeft = new FieldChangeListener() {

			public void fieldChanged(Field field, int context) {
			}
		};
		_left.setChangeListener(_listenLeft);
		
		add(_left);
		add(_manager);
		add(_right);
	}

	protected void sublayout(int width, int height) {
		layoutChild(_left, 32, 32);
		layoutChild(_right, 32, 32);
		layoutChild(_manager, width - 64, height);
		
		setPositionChild(_manager, 32, 0);
		setPositionChild(_left, 0, (Display.getHeight() / 2) - 16 - 24);
		setPositionChild(_right, Display.getWidth() - 32, (Display.getHeight() / 2) - 16 - 24);
		setExtent(Display.getWidth() - 64, Display.getHeight() - 48);
	}
	
	public void moveLeft() {
	}
	
	public void moveRight() {
	}
}
