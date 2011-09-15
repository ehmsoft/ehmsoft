package gui;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;

public class DirectoryPicker {
	private DirectoryPickerScreen _screen;
	private boolean _selected = false;
	private String ruta = null;
	public DirectoryPicker() {
		_screen = new DirectoryPickerScreen();
		_screen.setChangeListener(listener);
		actualizarLista(ruta);
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			switch (context) {
			case Util.CLICK:
				String path = _screen.getSelected();
				_screen.alert(path);
				break;
			case Util.GUARDAR:
				break;
			}
		}
	};

	private void actualizarLista(String ruta) {
		if (ruta == null) {
			Enumeration e = FileSystemRegistry.listRoots();
			Vector v = new Vector();
			while (e.hasMoreElements()) {
				v.addElement(e.nextElement());
			}
			String[] roots = new String[v.size()];
			v.copyInto(roots);
			_screen.setLista(roots);
		} else {
			try {
				Enumeration fileEnum;
				Vector filesVector = new Vector();
				FileConnection fc = (FileConnection) Connector.open("file:///"
						+ ruta);
				fileEnum = fc.list();
				filesVector.addElement("..");
				while (fileEnum.hasMoreElements()) {
					filesVector.addElement((String)fileEnum.nextElement());
				}
			} catch (Exception ex) {
				_screen.alert("Directorio no encontrado");
			}
		}
	}
	
	public DirectoryPickerScreen getScreen() {
		return _screen;
	}

	public boolean isSelected() {
		return _selected;
	}
}
