package pdf.certificate;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class Certificate {

	public static void main(String[] args) {
		Certificate certificate = new Certificate();
		String name="S.Ganapathy Ramasubramanian";
		String discription="Has satisfactorily completed 5 months of “Java Full Stack, MEAN Stack, and MERN Stack” training from 1/01/2021 to 18/07/2022.";
		String qrData ="ASP21058";
		String signDate="17/07/2021";
		try {			
			System.out.println(certificate.getCertificate(name, discription, qrData, signDate));
		}catch(Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public String getCertificate(String name,String discription,String qrData,String signDate) throws Exception{	
		
		//Document and Page Creation
		PDDocument doc = new PDDocument();
		PDRectangle pageSize = new PDRectangle(842, 595);
		PDPage docpage = new PDPage(pageSize);
		doc.addPage(docpage);
		PDPage page = doc.getPage(0);
		
		//Page Content Stream Object Creation of the PDF Document.
		PDPageContentStream content = new PDPageContentStream(doc, page);
		
		//Load the Background in Page
		PDImageXObject background = PDImageXObject.createFromFile("src\\template\\Final_Certificate.jpg", doc);
		content.drawImage(background, 0, 0, 842, 595);
		
		//Load Signature in the Certificate
		PDImageXObject sign = PDImageXObject.createFromFile("src\\template\\signature.png", doc);
		content.drawImage(sign, 660, 170, 160, 50);
		
		//Color Object Creation
		Color nameColor = new Color(253, 47, 111);
		Color blackColor = Color.BLACK;
		
		//Load Name on the Certificate 
		
		//Selecting the Font using file input stream
		FileInputStream fontFileAllura = new FileInputStream("src\\fonts\\Allura-Regular.ttf");
		PDType0Font nameFont = PDType0Font.load(doc, fontFileAllura, true);
		FileInputStream fontFileLato = new FileInputStream("src\\fonts\\Lato-Regular_0.ttf");
		PDType0Font latoFont = PDType0Font.load(doc, fontFileLato, true);
		
		//Calculate the Name String Height and Width for Center the Text		
		int nameFontSize = 48; // Or whatever font size you want.
		float titleWidth = nameFont.getStringWidth(name) / 1000 * nameFontSize; // get your text width 
		float titleHeight = nameFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * nameFontSize; // get your text height
				
		//Text Name append		
		content.beginText();	
		content.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - 215 - titleHeight); // this is make your text center
		content.setFont(nameFont, nameFontSize);		
		content.setNonStrokingColor(nameColor);			
		content.showText(name);		
		content.endText();	
		
		//Load Sign Date on the certificate 
		
		//Calculate the Sign Date String Height and Width for Center the Text		
		int signDateFontSize = 14; // Or whatever font size you want.
		float signDateWidth = latoFont.getStringWidth(signDate) / 1000 * signDateFontSize; // get your text width 
		float signDateHeight = latoFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * signDateFontSize; // get your text height
		
		//Text Sign Text Append 
		content.beginText();
		//content.newLineAtOffset(680,150);
		content.newLineAtOffset((page.getMediaBox().getWidth() - signDateWidth) / 2 + 303 , page.getMediaBox().getHeight() - 435 - signDateHeight); 
		content.setLeading(14.5f);
		content.setFont(latoFont, signDateFontSize);
		content.setNonStrokingColor(blackColor);
		content.showText(signDate);		
		content.endText();		
		
		//QR-Code Generation and Printing	
		QRCode qr = new QRCode();		
		BufferedImage QRBufferedImage = qr.generateQR(qrData);
		PDImageXObject QRImage = LosslessFactory.createFromImage(doc, QRBufferedImage);
		content.drawImage(QRImage, 85, 80);					
				
	    
		//Certificate Content 
		
		//Paragraph font size and margin 
		float contentFontSize = 12;
		float margin = 200;
		PDRectangle mediabox = page.getMediaBox(); 
		float width = mediabox.getWidth() - 2*margin; 
		float leading = 1.5f * contentFontSize;
		
		//passing the font size and margin to the paragraph method in the text class. 
		//That is return the lines as a Arraylist.		
		Text text = new Text(page);		
		ArrayList<String> lines = text.paragraph(discription, latoFont, contentFontSize, width);
		content.beginText();
		content.setFont(latoFont, contentFontSize);	 
		float contentWidth = latoFont.getStringWidth(lines.get(0)) / 1000 * contentFontSize; // get your text width 
		float contentHeight = latoFont.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * contentFontSize; // get your text height
		content.newLineAtOffset((page.getMediaBox().getWidth() - contentWidth) / 2 , page.getMediaBox().getHeight() - 280 - contentHeight); 
		 //  content.newLineAtOffset(280, 280);
			
		//Printing the array list with text align center logic.
		int i = 0;		
		for (String line: lines){
		    if(i>=0) {
		    	float contentWidth2 = latoFont.getStringWidth(line) / 1000 * contentFontSize; // get your text width
			    content.newLineAtOffset((width - contentWidth2) / 2 , -leading);
		    }	        
		    content.showText(line);	 
		    i++;
		}
		content.endText(); 
			
	   		
		content.close();//Should close the Content Stream in final stage.		
		doc.save("C:\\Users\\Ganu\\Desktop\\Final\\"+qrData+"-HIT-Certificate.pdf");//Save the Document Path as where you want.
		doc.close();//Should close the doc final position 
		return name+"'s E-Certificate Genarated succussfully";
	}

}
