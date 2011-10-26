package gui;

import core.Preferencias;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectListField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class DirectoryPickerScreen extends PopupScreen {

	/**
	 * 
	 */

	private LabelField _lblTitulo;
	private ObjectListField _lsDirectorios;
	private ButtonField _btnfldOk;
	private ButtonField _btnfldCancel;

	public DirectoryPickerScreen() {
		super(new VerticalFieldManager(VERTICAL_SCROLL | VERTICAL_SCROLLBAR));
		setFont(Preferencias.getTipoFuente());
		_lblTitulo = new LabelField("Examinar", Field.FIELD_HCENTER);
		_btnfldOk = new ButtonField("Seleccionar Aquí",
				ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
		_btnfldOk.setMinimalWidth(100);
		_btnfldOk.setChangeListener(listenerSeleccionar);
		_btnfldCancel = new ButtonField("Cancelar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnfldCancel.setChangeListener(listenerCancel);

		_lsDirectorios = new ObjectListField() {
			protected boolean navigationClick(int status, int time) {
				fieldChangeNotify(Util.CLICK);
				return true;
			}
		};
		_lsDirectorios.setChangeListener(listenerLista);

		add(_lblTitulo);
		add(_lsDirectorios);
		add(_btnfldOk);
		add(_btnfldCancel);
	}

	private FieldChangeListener listenerSeleccionar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(Util.GUARDAR);
		}
	};
	private FieldChangeListener listenerCancel = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Util.popScreen(getScreen());
		}
	};
	private FieldChangeListener listenerLista = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			if (context == Util.CLICK) {
				fieldChangeNotify(Util.CLICK);
			}
		}
	};

	public String getSelected() {
		return (String) _lsDirectorios.get(_lsDirectorios,
				_lsDirectorios.getSelectedIndex());
	}

	public void setLista(Object[] list) {
		_lsDirectorios.set(list);
	}

	public void alert(String alert) {
		Dialog.alert(alert);
	}

	public boolean onClose() {
		Util.popScreen(getScreen());
		return true;
	}
}
