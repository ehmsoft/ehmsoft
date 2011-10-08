package gui.Nuevos;

import gui.Util;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.SeparatorField;
import net.rim.device.api.ui.component.TextField;
import net.rim.device.api.ui.container.HorizontalFieldManager;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class NuevaCategoriaPopUp extends PopupScreen implements
		NuevaCategoriaInterface {

	private BasicEditField _txtDescripcion;
	private HorizontalFieldManager _statusField;

	public NuevaCategoriaPopUp() {
		super(new VerticalFieldManager());
		LabelField labelField = new LabelField("Nueva categoría",
				Field.FIELD_HCENTER);
		add(labelField);
		add(new SeparatorField());

		_txtDescripcion = new BasicEditField(TextField.NO_NEWLINE);
		_txtDescripcion.setLabel("Descripcion: ");

		add(_txtDescripcion);
		
		_statusField = new HorizontalFieldManager();
		add(_statusField);
		ButtonField btnfldOk = new ButtonField("OK", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		btnfldOk.setMinimalWidth(100);
		btnfldOk.setChangeListener(listenetAceptar);
		add(btnfldOk);
	}
	
	public void setStatus(Field field) {
		_statusField.add(field);
	}

	public int ask(Object[] options, String string, int index) {
		return Dialog.ask(string, options, index);
	}

	private FieldChangeListener listenetAceptar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(Util.GUARDAR);
		}
	};

	public String getDescripcion() {
		return _txtDescripcion.getText();
	}

	public boolean onClose() {
		fieldChangeNotify(Util.CERRAR);
		return false;
	}
}
