package gui;

import net.rim.device.api.system.KeyListener;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevaCategoriaPopUp extends PopupScreen implements
		NuevaCategoriaInterface {

	private BasicEditField _txtDescripcion;

	public final int GUARDAR = 1;
	public final int CERRAR = 2;

	public NuevaCategoriaPopUp() {
		super(new VerticalFieldManager());
		LabelField labelField = new LabelField("Nueva categoría",
				Field.FIELD_HCENTER);
		add(labelField);
		add(new SeparatorField());

		_txtDescripcion = new BasicEditField(BasicEditField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripcion: ");

		add(_txtDescripcion);

		ButtonField btnfldOk = new ButtonField("OK", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		btnfldOk.setMinimalWidth(100);
		btnfldOk.setChangeListener(listenetAceptar);
		add(btnfldOk);
		addKeyListener(new ListenerKey());
	}

	public void alert(String string) {
		Dialog.alert(string);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	private FieldChangeListener listenetAceptar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(GUARDAR);
		}
	};

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public class ListenerKey implements KeyListener {
		public boolean keyChar(char key, int status, int time) {
			return false;
		}

		public boolean keyDown(int keycode, int time) {
			if (keycode == 1769472) {
				fieldChangeNotify(CERRAR);
				return true;
			} else {
				return false;
			}
		}

		public boolean keyRepeat(int keycode, int time) {
			return false;
		}

		public boolean keyStatus(int keycode, int time) {
			return false;
		}

		public boolean keyUp(int keycode, int time) {
			return false;
		}
	}
}
