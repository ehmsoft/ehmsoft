package gui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class EditableTextField extends HorizontalFieldManager {

	private LabelField _label;
	private BasicEditField _txtField;
	private LabelField _lblField;
	private int _color = 0x00757575;
	private boolean _editable = false;
	
	public EditableTextField() {
		this(0);
	}
	
	public EditableTextField(long style) {
		_txtField = new BasicEditField(style);
		_lblField = new LabelField("",LabelField.FOCUSABLE) {
			protected void paint(Graphics g) {
				g.setColor(_color);
				super.paint(g);
			}
		};
		_label = new LabelField();
		this.add(_label);
		this.add(_lblField);
	}

	public EditableTextField(String label, String initialValue, long style) {
		this(style);
		_label.setText(label);
		_lblField.setText(initialValue);
	}
	
	public EditableTextField(String label, String initialValue) {
		this(label, initialValue, 0);
	}

	public void setLabel(String label) {
		_label.setText(label);
	}

	public void setText(String text) {
		if(_editable) {
			_txtField.setText(text);
		} else {
			_lblField.setText(text);
		}
	}
	
	public void setEditableColor(int color) {
		if (_color != color) {
			_color = color;
			long style = _txtField.getStyle();
			String text = _txtField.getText();
			this.delete(_txtField);
			_txtField = new BasicEditField(style) {
				protected void paint(Graphics g) {
					g.setColor(_color);
					super.paint(g);
				}
			};
			_txtField.setText(text);
			_txtField.setEditable(false);
			this.add(_txtField);
		}
	}

	public String getText() {
		if (_editable) {
			return _txtField.getText();
		} else {
			return _lblField.getText();
		}
	}

	public int getTextLength() {
		if (_editable) {
			return _txtField.getTextLength();
		} else {
			return _lblField.getText().length();
		}
	}

	public void setEditable() {
		_txtField.setText(_lblField.getText());
		this.replace(_lblField, _txtField);
		_editable = true;
	}
	
	public void setMaxSize(int maxSize) {
		_txtField.setMaxSize(maxSize);
	}
	
	public void setEditable(boolean editable) {
		if(editable) {
			setEditable();
		} else {
			_lblField.setText(_txtField.getText());
			this.replace(_txtField, _lblField);
			_editable = false;
		}
	}
}
