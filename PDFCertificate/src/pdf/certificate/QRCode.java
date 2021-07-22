package pdf.certificate;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.EnumMap;
import java.util.Map;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

public class QRCode {	
	public BufferedImage generateQR(String text) throws Exception {	    
	        int size = 180;	       //Set QRcode size here
	        BitMatrix byteMatrix = null;
	        int CrunchifyWidth = 0;	         
	        
	        Map<EncodeHintType, Object> hintMap = new EnumMap<EncodeHintType, Object>(EncodeHintType.class);
	        hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	        
	        hintMap.put(EncodeHintType.MARGIN, 0); 
	        hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
	        
	        //Create QRCodeWriter Object 
	        QRCodeWriter qrCodeWriter = new QRCodeWriter();
	        try {
	        	byteMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, size,
	            size, hintMap);
	            CrunchifyWidth = byteMatrix.getWidth();	          
	        }catch (WriterException e) {
	        	e.printStackTrace(); 
	         }
	        
	         //Create buffered image object
	         BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth,BufferedImage.TYPE_INT_RGB);
	         image.createGraphics();
	         
	         //Print the QRcode in buffered image.
	         Graphics2D graphics = (Graphics2D) image.getGraphics();
	         graphics.setColor(Color.WHITE);
	         graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
	         graphics.setColor(Color.BLACK);
	            	
	         for (int i = 0; i < CrunchifyWidth; i++) {
	        	 for (int j = 0; j < CrunchifyWidth; j++) {
	        		 if (byteMatrix.get(i, j)) {
	        			 graphics.fillRect(i, j, 1, 1);
	        		 }
	        	 }
	         }
	         //Return the buffered image object
	        return image;
	}	
	

}
