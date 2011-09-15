package gui;


import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.container.PopupScreen;
import net.rim.device.api.ui.container.VerticalFieldManager;

public class BackupScreen extends PopupScreen {

	/**
	 * 
	 */
	private BasicEditField _txtNombreArchivo;
	private ObjectChoiceField _lsRoots;
	private LabelField _lblTitulo;
	private ButtonField _btnfldOk;
	private ButtonField _btnfldCancel;

	public BackupScreen() {
		super(new VerticalFieldManager(VERTICAL_SCROLL | VERTICAL_SCROLLBAR));
		_txtNombreArchivo = new BasicEditField("Nombre: ", "copia.bk");

		_lsRoots = new ObjectChoiceField();
		_lsRoots.setChangeListener(listenerRoots);
		_lblTitulo = new LabelField("Guardar Archivo", Field.FIELD_HCENTER);

		_btnfldOk = new ButtonField("Guardar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnfldOk.setMinimalWidth(100);
		_btnfldOk.setChangeListener(listenerGuardar);
		_btnfldCancel = new ButtonField("Cancelar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnfldCancel.setChangeListener(listenerCancel);
		add(_lblTitulo);
		add(_txtNombreArchivo);

		add(_lsRoots);
		add(_btnfldOk);
		add(_btnfldCancel);
	}

	private FieldChangeListener listenerGuardar = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			fieldChangeNotify(Util.GUARDAR);
		}
	};
	private FieldChangeListener listenerCancel = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			Util.popScreen(getScreen());
		}
	};
	private FieldChangeListener listenerRoots = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
				fieldChangeNotify(Util.ROOT_SELECCIONADO);
		}
	};

	public String getChoice() {
		return (String) _lsRoots.getChoice(_lsRoots.getSelectedIndex());
	}

	public int getIndexChoice() {
		return _lsRoots.getSelectedIndex();
	}

	public String getNombreArchivo() {
		return _txtNombreArchivo.getText();
	}
	public void setChoice(int choice){
		_lsRoots.setSelectedIndex(choice);
	}
	public void setLsRoots(String[] roots) {
		_lsRoots.setChoices(roots);
	}
	public boolean onClose(){
		UiApplication.getUiApplication().popScreen(getScreen());
		return true;
	}
}
