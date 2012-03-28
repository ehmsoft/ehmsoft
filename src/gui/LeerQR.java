package gui;

import java.util.Hashtable;
import java.util.Vector;
import javax.microedition.media.MediaException;
import net.rim.device.api.system.Application;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.FieldChangeListener;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.component.ButtonField;
import net.rim.device.api.ui.component.Dialog;
import net.rim.device.api.ui.component.LabelField;
import net.rim.device.api.ui.component.Status;
import net.rim.device.api.ui.container.MainScreen;

import org.jackpot.device.blackberry.api.barcodelib.BarcodeDecoder;
import org.jackpot.device.blackberry.api.barcodelib.BarcodeDecoderListener;
import org.jackpot.device.blackberry.api.barcodelib.BarcodeScanner;

import persistence.Persistence;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.DecodeHintType;

import core.Proceso;
public class LeerQR extends MainScreen implements
FieldChangeListener {
	ButtonField btnStart;
	Proceso proceso;
	LabelField lblMensaje;
	String id_proceso;
	ViewFinderScreen _viewScreen;
	private final String MENSAJE = "Escanee el código QR impreso desde la aplicación de escritorio para ubicar rápidamente" +
	"el proceso ligado. RECUERDE QUE DEBE HABER SINCRONIZADO EL DISPOSITIVO CON EL COMPUTADOR DE ESCRITORIO ANTES DE ESCANEAR.";
	public LeerQR() {
		super(USE_ALL_WIDTH | Field.FIELD_HCENTER);
		lblMensaje = new LabelField(MENSAJE);
		btnStart = new ButtonField("Iniciar Escaneo", ButtonField.CONSUME_CLICK);
		btnStart.setChangeListener(this);
		add(btnStart);
		add(lblMensaje);
	}

	public void fieldChanged(Field field, int context) {
		switch (field.getIndex()) {
		case 0:
			_viewScreen = new ViewFinderScreen();
			UiApplication.getUiApplication().pushModalScreen(_viewScreen);
		}
	}

	public final class ViewFinderScreen extends MainScreen {
		BarcodeScanner scanner;
		BarcodeDecoder decoder;
		BarcodeDecoderListener listener;
		Field viewFinder;
		public ViewFinderScreen() {

			Hashtable hints = new Hashtable(1);
			Vector formats = new Vector(1);
			formats.addElement(BarcodeFormat.QR_CODE); // You can change format
			// here
			hints.put(DecodeHintType.POSSIBLE_FORMATS, formats);

			decoder = new BarcodeDecoder(hints);

			listener = new BarcodeDecoderListener() {

				public void barcodeFailDecoded(Exception ex) {

				}

				public void barcodeDecoded(String rawText) {
					synchronized (Application.getEventLock()) {
						id_proceso = rawText;
					}
				}

				public void barcodeDecodeProcessFinish() {
					try {
						scanner.stopScan();
					} catch (MediaException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					synchronized (Application.getEventLock()) {
						UiApplication.getUiApplication().popScreen(_viewScreen);
						addBtnVerProceso();
					}
				}
			};
			try {
				scanner = new BarcodeScanner(decoder, listener);
				viewFinder = scanner.getViewFinder();
				scanner.getVideoControl().setDisplayFullScreen(true);
				add(viewFinder);

				scanner.startScan();
			} catch (Exception e) {
				Status.show(e.getMessage());
				e.printStackTrace();
			}
		}

		protected boolean navigationClick(int arg0, int arg1) {
			// TODO Auto-generated method stub
			try {
				scanner.stopScan();
			} catch (MediaException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.close();
			return true;
		}
		
		
		
		
		
		public boolean onClose() {
			try {
				scanner.stopScan();
			} catch (MediaException e) {
				e.printStackTrace();
			}
			return super.onClose();
		}

	}
	private void addBtnVerProceso(){
		if (id_proceso.startsWith("id_proceso:")){
			id_proceso = id_proceso.substring(id_proceso.indexOf(":") + 1);
			Proceso p = null;
			try {
				p = new Persistence().consultarProceso(id_proceso);
			} catch (NullPointerException e) {
				Util.noSd();
			} catch (Exception e) {
				Util.alert(e.toString());
			}
			if (p != null) {
				proceso = p;
				final String radicado = p.getRadicado();
				UiApplication.getUiApplication().invokeLater(new Runnable() {
					
					public void run() {
						if (Dialog.ask(Dialog.D_YES_NO, "Se ha detectado proceso con radicado Nº: "+ radicado + ". Desea verlo?") == Dialog.YES){
							close();
							Util.verProceso(proceso);
						}else{
							close();
						}
						
					}
				});
				
			}
		}else{
			Util.alert("El código escaneado no corresponde a un código válido. Por favor reimprima el código");
			this.close();
		}
	}
	

	
	public boolean onClose() {
		Util.popScreen(this);
		return true;
	}
}