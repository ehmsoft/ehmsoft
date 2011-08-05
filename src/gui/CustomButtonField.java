package gui;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.Graphics;

public class CustomButtonField extends Field {
	private Bitmap _currentPicture, _onPicture, _offPicture;
	private boolean _big = false;
	private boolean _arrow = false;
	
	CustomButtonField(long style, Bitmap on, Bitmap off, boolean arr) {
		super(style);
		_onPicture = on;
		_offPicture = off;
		_currentPicture = _offPicture;
		if(arr) {
			_arrow = true;
		}
	}
	
	CustomButtonField(String text, long style, Bitmap on) {
		super(style);
		_onPicture = on;
		_offPicture = on;
		_currentPicture = _offPicture;
		_big = true;
	}
	
	CustomButtonField(long style, Bitmap on) {
		super(style);
		_onPicture = on;
		_offPicture = on;
		_currentPicture = _offPicture;
		_arrow = true;
	}
	
	public int getPreferredHeight() {
		if(_big) {
			return 200;
		} else if(_arrow) {
			return 16;
		} else {
			return 50;
		}
	}
	
	public int getPreferredWidth() {
		if(_big) {
			return 200;
		} else if(_arrow) {
			return 16;
		} else {
			return 118;
		}
	}
	
	protected void onFocus(int direction) {
		_currentPicture = _onPicture;
		if(_arrow) {
			this.fieldChangeNotify(150);
		}
		invalidate();
	}
	
	protected void onUnfocus() {
		_currentPicture = _offPicture;
		invalidate();
	}
	
	protected void drawFocus(Graphics graphics, boolean on) {
		
	}
	
	protected void layout(int width, int height) {
		setExtent(Math.min(width, getPreferredWidth()), 
				Math.min(height, getPreferredHeight()));
	}
	
	protected void paint(Graphics graphics) {
		graphics.drawBitmap(0, 0, getPreferredWidth(), getHeight(), _currentPicture, 0, 0);
	}
	
	protected boolean navigationCLick(int status, int time) {
		fieldChangeNotify(1);
		return true;
	}
}
