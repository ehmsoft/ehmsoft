package org.jackpot.device.blackberry.api.barcodelib;

import net.rim.device.api.system.Bitmap;

import com.google.zxing.qrcode.encoder.ByteMatrix;

/**
 * Class to create Barcode in Bitmap format
 * @author Ali Irawan (boylevantz@gmail.com)
 * @version 1.0
 */ 
public final class BarcodeBitmap {

	/**
	 * Create a bitmap using the byte matrix
	 * @param byteMatrix the bytes represented in matrix
	 * @param size the width or height of the matrix
	 * @return a {@link Bitmap} instance
	 */
	public static Bitmap createBitmap(ByteMatrix byteMatrix, int size) {
		byte[] temp = new byte[size * size];
		int i=0;
		for (int y = 0; y < size; y++) {
			for (int x = 0; x < size; x++) {
				temp[i++] = byteMatrix.get(x, y);
			}
		}
		Bitmap bitmap = new Bitmap(Bitmap.getDefaultType(), size, size, temp);
		return bitmap;
	}
}
