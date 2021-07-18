package test;

import java.awt.Color;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;


public class CertificatetTest2 {
	
 	public static void main(String[] args) throws Exception {	
		String title = "S.Ganapathy Ramasubramanian";
		PDDocument doc = new PDDocument();	
		PDRectangle rec = new PDRectangle(842, 595);//Page size creation 
		PDPage docPage = new PDPage(rec);		
		doc.addPage(docPage);
		PDPage page = doc.getPage(0);			
		FileInputStream font_alluraStream = new FileInputStream("src\\fonts\\Allura-Regular.ttf"); //font using the fileinputstream		
		PDType0Font name_font = PDType0Font.load(doc, font_alluraStream, true);		//set font to PDFont
		PDImageXObject background = PDImageXObject.createFromFile("src\\template\\Final_Certificate.jpg", doc);		
		PDPageContentStream content = new PDPageContentStream(doc, page);
		int fontSize2 = 48; // Or whatever font size you want.
		float titleWidth = name_font.getStringWidth(title) / 1000 * fontSize2; // get your text width 
		float titleHeight = name_font.getFontDescriptor().getFontBoundingBox().getHeight() / 1000 * fontSize2; // get your text height
		content.drawImage(background, 0, 0, 842, 595);	
		content.beginText();	
		content.newLineAtOffset((page.getMediaBox().getWidth() - titleWidth) / 2, page.getMediaBox().getHeight() - 220 - titleHeight); // this is make your text center
		content.setFont(name_font, fontSize2);			
		Color color = new Color(253, 47, 111);// you need to create colour objects to whole project 
		content.setNonStrokingColor(color);			
		content.showText(title);		
		content.endText();		
		
		
		
		//---------------------------------------------------------------------------------------------------------------------------------------------------------
		
		try
		{		    

		    PDFont pdfFont = PDType1Font.HELVETICA;
		    float fontSize = 12;
		    float leading = 1.5f * fontSize;

		    PDRectangle mediabox = page.getMediaBox();
		    float margin = 72;
		    float width = mediabox.getWidth() - 2*margin;
		    float startX = mediabox.getLowerLeftX() + margin;
		    float startY = mediabox.getUpperRightY() - margin;

		    String text = "I am trying to create a PDF file with a lot of text contents in the document. I am using PDFBox"; 
		    List<String> lines = new ArrayList<String>();
		    int lastSpace = -1;
		    while (text.length() > 0)
		    {
		        int spaceIndex = text.indexOf(' ', lastSpace + 1);
		        if (spaceIndex < 0)
		            spaceIndex = text.length();
		        String subString = text.substring(0, spaceIndex);
		        float size = fontSize * pdfFont.getStringWidth(subString) / 1000;
		        System.out.printf("'%s' - %f of %f\n", subString, size, width);
		        if (size > width)
		        {
		            if (lastSpace < 0)
		                lastSpace = spaceIndex;
		            subString = text.substring(0, lastSpace);
		            lines.add(subString);
		            text = text.substring(lastSpace).trim();
		            System.out.printf("'%s' is line\n", subString);
		            lastSpace = -1;
		        }
		        else if (spaceIndex == text.length())
		        {
		            lines.add(text);
		            System.out.printf("'%s' is line\n", text);
		            text = "";
		        }
		        else
		        {
		            lastSpace = spaceIndex;
		        }
		    }

		    content.beginText();
		    content.setFont(pdfFont, fontSize);
		    content.newLineAtOffset(startX, startY);
		    for (String line: lines)
		    {
		        content.showText(line);
		        content.newLineAtOffset(0, -leading);
		    }
		    content.endText(); 
		    content.close();
			doc.save("C:\\Users\\Ganu\\Desktop\\Final\\Final_Certificate(Test).pdf");
		    
		}
		finally
		{
		    if (doc != null)
		    {
		        doc.close();
		    }
		}	
		
	}
}
