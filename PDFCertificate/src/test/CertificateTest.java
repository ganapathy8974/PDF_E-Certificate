package test;
import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;  


public class CertificateTest {
	public static void main(String[] args) throws IOException {			 
		File file = new File("C:\\Users\\Ganu\\Desktop\\Sample.pdf");
		PDDocument doc = Loader.loadPDF(file);
		PDPage page = doc.getPage(0);
		PDImageXObject img = PDImageXObject.createFromFile("D:\\001Rock\\My Doc\\DSC_0474.jpg",doc);
		PDPageContentStream pcs = new PDPageContentStream(doc, page);
		pcs.beginText();	
		pcs.setFont(PDType1Font.COURIER_OBLIQUE, 10);
		pcs.newLineAtOffset(10, 500);
		pcs.setLeading(14.8f);
		String text ="This is an example of adding text to a page in the pdf document. we can   add as many lines";
		String text2 = "as we want like this using the showText() method of the ContentStream class";
		pcs.showText(text);
		pcs.newLine();
		pcs.showText(text2);
		pcs.newLine();		
		pcs.endText();		
		pcs.drawImage(img, 50, 50);
		pcs.close();
		doc.save("C:\\Users\\Ganu\\Desktop\\Update_Sample.pdf");
		doc.close();
	}
}
