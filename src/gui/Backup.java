package gui;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.io.file.FileSystemRegistry;

import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;

public class Backup {
	private BackupScreen _screen;
	private String _ruta = null;
	private String _nombreArchivo;
	private String[] roots;
	public Backup() {
		_screen = new BackupScreen();
		_screen.setChangeListener(listener);
		Enumeration e = FileSystemRegistry.listRoots();
		Vector v = new Vector();
		while (e.hasMoreElements()) {
			v.addElement(e.nextElement());
		}
		roots = new String[v.size()];
		String[] strRoots = new String[v.size() + 1];
		v.copyInto(roots);
		
		for(int i=0;i<roots.length;i++){
			if(roots[i].equalsIgnoreCase("sdcard/")){
				strRoots[i] = "Tarjeta de Almacenamiento";
			} else if(roots[i].toString().equalsIgnoreCase("store/")){
				strRoots[i] = "Memoria Interna";
			} else{
				strRoots[i] = roots[i];
			}
		}
		strRoots[v.size()] = "Examinar...";
		_screen.setLsRoots(strRoots);
	}

	private FieldChangeListener listener = new FieldChangeListener() {

		public void fieldChanged(Field field, int context) {
			switch (context) {
			case Util.ROOT_SELECCIONADO:
				if (_screen.getIndexChoice() == roots.length) {
					_screen.setChoice(0);
					examinar();
				} else {
					_ruta = roots[_screen.getIndexChoice()];
					_nombreArchivo = _screen.getNombreArchivo();
				}
				break;
			case Util.GUARDAR:
				guardar();
			default:
				break;
			} 
		}
	};

	private void examinar() {
		DirectoryPicker dp = new DirectoryPicker();
		UiApplication.getUiApplication().pushModalScreen(dp.getScreen());
		if(dp.isSelected()){
			if(dp.getRuta() == null){
				_ruta = roots[0];
			}else{
				_ruta = dp.getRuta();
			}
			_nombreArchivo = _screen.getNombreArchivo();
		}
		dp = null;
	}
	private void guardar(){
		_screen.alert("Se va a guardar en: " + _ruta + _nombreArchivo);
	}
	public BackupScreen getScreen() {
		return _screen;
	}
}