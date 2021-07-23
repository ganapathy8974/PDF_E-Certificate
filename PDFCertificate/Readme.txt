Project Name : PDF E - Certificate													
																	
Application:
------------
This project helps to generate PDF certificate dynamically. you simply Enter the details it will generate beautiful certificate with QRcode.

Used Dependencies:
------------------
<dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>pdfbox</artifactId>
    <version>3.0.0-RC1</version>
</dependency>

 <dependency>
    <groupId>org.apache.pdfbox</groupId>
    <artifactId>xmpbox</artifactId>
    <version>3.0.0-RC1</version>
</dependency>

<dependency>  
	<groupId>org.apache.pdfbox</groupId>  
	<artifactId>preflight</artifactId>  
	<version>3.0.0-RC1</version>  
</dependency>

<dependency>  
	<groupId>org.apache.pdfbox</groupId>  
	<artifactId>pdfbox-tools</artifactId>  
	<version>3.0.0-RC1</version>  
</dependency> 

<dependency>
    <groupId>com.google.zxing</groupId>
    <artifactId>core</artifactId>
    <version>3.3.0</version>
</dependency>

<dependency>
	<groupId>com.google.zxing</groupId>
	<artifactId>javase</artifactId>
	<version>3.3.0</version>
</dependency>

Used Class:
-----------
Totally this project contaion 3 classes 
1)Certificate
2)QRCode
3)Text

QRcode:
------
This class have generateQR method.

QRcode Package is zxing
	
	i)generateQR - Method
	---------------------		
		*You need to pass data string to this method it will generate QRcode and return the BufferedImage.
		
Text:
-----	
This class have paragraph method to print the paragraph in your certificate.

	i) Paragraph-Method:
	--------------------
		*You need to pass the paragraph string to this class long with margin and font size.
		
		*This class split the string for your margin size and store into a array list and return the array list

Certificate:
------------
This class have getCertificate Method.

	i)getCertificate - Method:
	--------------------------
		*This method generate the certificate.
		
		*This method has some parameters that name, description, qrData, signDate these are only dynamic data in this certificate.
		
		*All Static content are pre-designed and stored as a .jpg in the template dir. This method first get the template and place the dynamic content on it.

How to get the certificate?
---------------------------
1) Enter required Details in main function in certificate class. click the run button.

2) Find your Certificate in the desktop.

		





















