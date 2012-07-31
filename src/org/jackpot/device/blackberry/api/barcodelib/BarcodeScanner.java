package org.jackpot.device.blackberry.api.barcodelib;

import java.io.IOException;
import java.util.Hashtable;
import java.util.Timer;
import java.util.TimerTask;

import javax.microedition.media.Manager;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.VideoControl;

import net.rim.device.api.system.Bitmap;
import net.rim.device.api.system.Display;
import net.rim.device.api.ui.Field;
import net.rim.device.api.ui.UiApplication;
import net.rim.device.api.ui.container.PopupScreen;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.common.HybridBinarizer;

/**
 * Barcode scanner main class
 * 
 * @author Ali Irawan (boylevantz@gmail.com)
 * @vesion 1.0
 */
public final class BarcodeScanner {
	private BarcodeDecoder _decoder;
	private BarcodeDecoderListener _listener;
	private VideoControl _vc;
	private Player _player;
	private Field _viewFinder;
	private String _encoding;
	private PopupScreen _waitingScreen;

	private BarcodeScanTask task;
	private Timer timer;

	/**
	 * Create the barcode scanner instance
	 * 
	 * @param decoder
	 *            specify the decoder instance
	 * @param listener
	 *            listener for handling events
	 * @throws IOException
	 *             occurred when video support is unavailable
	 * @throws MediaException
	 *             occurred when video cannot be started
	 */
	public BarcodeScanner(BarcodeDecoder decoder,
			BarcodeDecoderListener listener) throws IOException, MediaException {
		_decoder = decoder;
		_listener = listener;
		init();

	}

	/*
	 * initialize resource
	 */
	private void init() throws IOException, MediaException {
		_player = Manager.createPlayer("capture://video");
		_player.realize();
		_player.start();
		_vc = (VideoControl) _player.getControl("VideoControl");

		_viewFinder = (Field) _vc.initDisplayMode(
				VideoControl.USE_GUI_PRIMITIVE, "net.rim.device.api.ui.Field");

	}

	/**
	 * Get the player instance
	 * 
	 * @return player instance
	 */
	public Player getPlayer() {
		return _player;
	}

	/**
	 * Get the video control
	 * 
	 * @return video control instance
	 */
	public VideoControl getVideoControl() {
		return _vc;
	}

	/**
	 * Get the view finder
	 * 
	 * @return View finder instance
	 */
	public Field getViewFinder() {
		return _viewFinder;
	}

	/**
	 * Start scanning
	 * 
	 * @throws MediaException
	 *             occurred when Video Control is not initialized yet
	 */
	public void startScan() throws MediaException {
		setupDefaultEncoding();
		if (_vc != null) {
			UiApplication.getUiApplication().invokeLater(new Runnable() {
				public void run() {
					_viewFinder.setFocus();

				}
			});
			_vc.setVisible(true);

			task = new BarcodeScanTask();
			// create timer every 2 seconds, get a screenshot
			timer = new Timer();
			timer.schedule(task, 0, 2000); // 2 seconds once

		} else {
			throw new MediaException("Video Control is not initialized");
		}
	}

	public void stopScan() throws MediaException {
		if (timer != null) {
			timer.cancel(); // stop the timer
		}
	}

	/**
	 * Default setup encoding
	 */
	public void setupDefaultEncoding() {
		// System.getProperty("video.snapshot.encodings");
		_encoding = "encoding=jpeg&width=640&height=480&quality=superfine";
	}

	/**
	 * Setup specified encoding e.g.
	 * &quot;encoding=jpeg&width=640&height=480&quality=superfine&quot;
	 * 
	 * @param encodingType
	 */
	public void setupEncoding(String encodingType) {
		_encoding = encodingType;
	}

	final class BarcodeScanTask extends TimerTask {

		public void run() {

				System.out
						.println("--------------------------------->TimerTask run... preparing Capture");
				Bitmap bmpScreenshot = new Bitmap(Display.getWidth(),Display.getHeight());
				Display.screenshot(bmpScreenshot);

				System.out
						.println("--------------------------------->Captured");

				MultiFormatReader reader = new MultiFormatReader();

				// creating luminance source
				LuminanceSource source = new BitmapLuminanceSource(
						bmpScreenshot);
				BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(
						source));

				Hashtable hints = null;
				if (_decoder != null) {
					hints = _decoder._hints;
				}

				if (hints == null) {
					hints = new Hashtable(1);
					hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
				}

				Result result;
				String rawText = "";
				try {
					System.out
							.println("--------------------------------->Trying DECODE");
					result = reader.decode(bitmap, hints);
					
					System.out
							.println("--------------------------------->QR Code DECODED");
					rawText = result.getText();
					timer.cancel(); //stop the timer

					_listener.barcodeDecoded(rawText);
					_listener.barcodeDecodeProcessFinish();
				} catch (NotFoundException e) {
					System.out
							.println("--------------------------------->FAIL TO DECODE!!!!");
					_listener.barcodeFailDecoded(e);
				}

		}
	}
}
