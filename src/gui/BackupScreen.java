package gui;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.file.FileSystemRegistry;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.BasicEditField;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.ObjectChoiceField;
import net.rim.device.api.ui.component.ObjectListField;
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
		super(new VerticalFieldManager());
		_txtNombreArchivo = new BasicEditField("Nombre:", "copia.bk");
		Enumeration e = FileSystemRegistry.listRoots();
		Vector v = new Vector();
		while(e.hasMoreElements()){
			v.addElement(e.nextElement());
		}
		String [] roots = new String[v.size()];
		v.copyInto(roots);
		
		_lsRoots = new ObjectChoiceField("",roots);
				_lblTitulo = new LabelField("Guardar Archivo",
				Field.FIELD_HCENTER);
		
		_btnfldOk = new ButtonField("Guardar", ButtonField.CONSUME_CLICK
				| Field.FIELD_HCENTER);
		_btnfldOk.setMinimalWidth(100);
		
		_btnfldCancel = new ButtonField("Cancelar",
				ButtonField.CONSUME_CLICK | Field.FIELD_HCENTER);
		_btnfldCancel.setChangeListener(listenerCancel);
		add(_lblTitulo);
		add(_txtNombreArchivo);
		
		add(_lsRoots);
		add(_btnfldOk);
		add(_btnfldCancel);
	}
	private FieldChangeListener listenerCancel = new FieldChangeListener() {
		
		public void fieldChanged(Field field, int context) {
			Util.popScreen(getScreen());
		}
	};
}
