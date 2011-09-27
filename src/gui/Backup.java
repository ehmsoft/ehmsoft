package gui;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;
import javax.microedition.io.Connector;
import javax.microedition.io.file.FileConnection;
import javax.microedition.io.file.FileSystemRegistry;
import persistence.ConnectionManager;
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

		for (int i = 0; i < roots.length; i++) {
			if (roots[i].equalsIgnoreCase("sdcard/")) {
				strRoots[i] = "Tarjeta de Almacenamiento";
			} else if (roots[i].toString().equalsIgnoreCase("store/")) {
				strRoots[i] = "Memoria Interna";
			} else {
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
		if (dp.isSelected()) {
			if (dp.getRuta() == null) {
				_ruta = roots[0];
			} else {
				_ruta = dp.getRuta();
			}
			_nombreArchivo = _screen.getNombreArchivo();
		}
		dp = null;
	}

	private void guardar() {
		_nombreArchivo = _screen.getNombreArchivo();
		FileConnection fconn = null;
		try {
			fconn = (FileConnection) Connector.open("file:///" + _ruta
					+ _nombreArchivo);// Archivo a escribir
			ConnectionManager connMgr = new ConnectionManager();
			connMgr.prepararBD();
			FileConnection fconnDB = (FileConnection) Connector.open(connMgr
					.getDbLocation().toString());
			if (!fconn.exists()) {
				fconn.create();
			}
			OutputStream outputStream = null;
			InputStream inputStream = null;
			inputStream = fconnDB.openInputStream();

			// Open an output stream to the newly created file
			outputStream = fconn.openOutputStream();

			// Read data from the input stream and write the data to the
			// output stream.
			byte[] data = new byte[256];
			int length = 0;
			while (-1 != (length = inputStream.read(data))) {
				outputStream.write(data, 0, length);
			}

			// Close the connections

			if (fconn != null) {
				fconn.close();
			}
			if (fconnDB != null) {
				fconnDB.close();
			}
			if (outputStream != null) {
				outputStream.close();
			}
			if (inputStream != null) {
				inputStream.close();
			}
			_screen.alert("Archivo guardado!");
			UiApplication.getUiApplication().popScreen(_screen);
		} catch (IOException ioe) {
			_screen.alert("No se pudo guardar!");
		} catch (NullPointerException npe) {
			_screen.alert(Util.noSDString());
			System.exit(0);
		} catch (Exception e) {
			_screen.alert("Error desconocido. Código: Backup");
		} finally {
			if (fconn != null) {
				try {
					fconn.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public BackupScreen getScreen() {
		return _screen;
	}
}