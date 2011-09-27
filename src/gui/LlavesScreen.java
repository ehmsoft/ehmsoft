package gui;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class LlavesScreen extends PopupScreen {

	/**
	 * 
	 */
	private LabelField _lblTitulo;
	private ButtonField _btnfldOk;
	private ButtonField _btnfldCancel;
	private BasicEditField _txtEstado;
	private BasicEditField _txtLlave;
	public LlavesScreen() {
		super(new VerticalFieldManager());
		_lblTitulo = new LabelField("Activación",
				Field.FIELD_HCENTER);
		
		_btnfldOk = new ButtonField("Aceptar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnfldOk.setMinimalWidth(100);
		_btnfldOk.setChangeListener(listenerOk);
		_btnfldCancel = new ButtonField("Cancelar",
				ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
		_btnfldCancel.setChangeListener(listenerCancel);
		
		_txtEstado = new BasicEditField("Estado:", "Desactivado",12,BasicEditField.READONLY | BasicEditField.NON_FOCUSABLE);
		_txtLlave = new BasicEditField("Llave:", "");
		
		add(_lblTitulo);
		add(_txtEstado);
		add(_txtLlave);
		add(_btnfldOk);
		add(_btnfldCancel);
		
		

	}
	private FieldChangeListener listenerOk = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(Util.GUARDAR);
		}
	};
	
	public void alert(String text) {
		Dialog.alert(text);
	}
	
	private FieldChangeListener listenerCancel = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Util.popScreen(getScreen());
		}
	};
	
	public void setEstadoActivacion(String estado){
		_txtEstado.setText(estado);
	}
	public void setLlave(String llave){
		_txtLlave.setText(llave);
	}
	
	public String getLlave(){
		return _txtLlave.getText();
	}
}
