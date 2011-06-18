package gui;

import net.rim.device.api.ui.Graphics;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.HorizontalFieldManager;

public class EditableTextField extends HorizontalFieldManager {
	
	LabelField _label;
	BasicEditField _txtField;
	
	public EditableTextField(long style) {
		_txtField = new BasicEditField(style) {
			protected void paint(Graphics g) {
				g.setColor(0x00CDC9C9);
				super.paint(g);
			}
		};
		_txtField.setEditable(false);
		_label = new LabelField();
		this.add(_label);
		this.add(_txtField);
	}
	
	public EditableTextField(String label, String initialValue) {
		_txtField = new BasicEditField(null, initialValue) {
			protected void paint(Graphics g) {
				g.setColor(0x00CDC9C9);
				super.paint(g);
			}
		};
		_txtField.setEditable(false);
		_label = new LabelField(label);
		this.add(_label);
		this.add(_txtField);
	}
	
	public EditableTextField(String label, String initialValue, long style) {
		_txtField = new BasicEditField(style) {
			protected void paint(Graphics g) {
				g.setColor(0x00CDC9C9);
				super.paint(g);
			}
		};
		_txtField.setText(initialValue);
		_txtField.setEditable(false);
		_label = new LabelField(label);
		this.add(_label);
		this.add(_txtField);
	}
	
	public void setLabel(String label) {
		_label.setText(label);
	}
	
	public void setText(String text) {
		_txtField.setText(text);
	}
	
	public String getText() {
		return _txtField.getText();
	}
	
	public void setEditable() {
		long style = _txtField.getStyle();
		String text = _txtField.getText();
		this.delete(_txtField);
		_txtField = new BasicEditField(style);
		_txtField.setText(text);
		_txtField.setEditable(true);
		this.add(_txtField);
	}
}
