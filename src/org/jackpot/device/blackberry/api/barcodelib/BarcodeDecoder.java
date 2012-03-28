package org.jackpot.device.blackberry.api.barcodelib;

import java.util.Hashtable;

import com.google.zxing.DecodeHintType;

/**
 * BarcodeDecoder holds information of hints for reading
 * @author Ali Irawan (boylevantz@gmail.com)
 * @version 1.0
 */
public final class BarcodeDecoder {

	Hashtable _hints;
	
	/**
	 * Default constructors. By default hints would use DecodeHintType.TRY_HARDER as true. 
	 */
	public BarcodeDecoder(){
		_hints = new Hashtable();
		_hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
	}
	/**
	 * Construct with specified hints
	 * @param hints specify the hints
	 */
	public BarcodeDecoder(Hashtable hints){
		_hints = hints;
	}
}
