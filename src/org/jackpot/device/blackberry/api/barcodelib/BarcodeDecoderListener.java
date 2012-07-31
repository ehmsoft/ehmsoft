package org.jackpot.device.blackberry.api.barcodelib;

/**
 * Listener for implementing events that is occurred during the decoding barcode process
 * @author Ali Irawan (boylevantz@gmail.com)
 * @version 1.0
 */
public interface BarcodeDecoderListener {

	public void barcodeDecoded(String rawText);
	public void barcodeFailDecoded(Exception ex);
	public void barcodeDecodeProcessFinish();
	
	
}
