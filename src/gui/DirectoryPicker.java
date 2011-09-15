package gui;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;

public class DirectoryPicker {
	private DirectoryPickerScreen _screen;
	private boolean _selected = false;
	private String _ruta = null;

	public DirectoryPicker() {
		_screen = new DirectoryPickerScreen();
		_screen.setChangeListener(listener);
		actualizarLista(_ruta);
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			switch (context) {
			case Util.CLICK:
				String path = _screen.getSelected();
				// Hace las verificaciones necesarias para armar bien la
				// direccion
				if (_ruta == null) {
					_ruta = path;
				} else if (path.endsWith("..")) {
					_ruta = _ruta.substring(0, _ruta.lastIndexOf('/'));
					_ruta = _ruta.substring(0, _ruta.lastIndexOf('/') + 1);
				} else {
					_ruta += path;
				}
				if (_ruta.indexOf('/') == -1) {
					_ruta = null;
				}
				actualizarLista(_ruta);
				break;
			case Util.GUARDAR:
				_selected = true;
				UiApplication.getUiApplication().popScreen(_screen);
				break;
			}
		}
	};

	private void actualizarLista(String _ruta) {
		// Carga la lista de archivos y directorios, actualizando la pantalla
		if (_ruta == null) {
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
						+ _ruta);
				fileEnum = fc.list();
				filesVector.addElement("..");
				while (fileEnum.hasMoreElements()) {
					String element = (String) fileEnum.nextElement();
					if (element.endsWith("/")) {
						filesVector.addElement(element);
					}
				}
				String[] roots = new String[filesVector.size()];
				filesVector.copyInto(roots);
				_screen.setLista(roots);
			} catch (Exception ex) {
				_screen.alert("Directorio no encontrado");
				_ruta = null;
			}
		}
	}

	public DirectoryPickerScreen getScreen() {
		return _screen;
	}

	public String getRuta() {
		return _ruta;
	}

	public boolean isSelected() {
		return _selected;
	}
}
