package module;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;

import org.xml.sax.SAXException;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class ReadPDFFile {

   public static void main(final String[] args) throws IOException,TikaException, SAXException {

      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
     
      String url="http://10.192.1.53:3000/thist/overview_cap_report_pdf?IP2=154.83.3.161&valid_input=1&window_fromts=1654626600&window_tots=1654713000";
  
      byte[] dowloadedFile = RestAssured.given().cookie("_session_id", "9898a28ac9cde80249ea156453e96071").when()
				.get(url)
				.then().extract().asByteArray();
      //FileUtils.writeByteArrayToFile(new File("C:\\Users\\dell\\Desktop\\zybisysapp\\test.pdf"), dowloadedFile);
      InputStream file= new ByteArrayInputStream(dowloadedFile);
      ParseContext pcontext = new ParseContext();
      
      //parsing the document using PDF parser
      PDFParser pdfparser = new PDFParser(); 
      pdfparser.parse(file, handler, metadata , pcontext);
      
      //getting the content of the document
      System.out.println("Contents of the PDF :" + handler.toString());
      
      //getting metadata of the document
      System.out.println("Metadata of the PDF:");
      String[] metadataNames = metadata.names();
      
      for(String name : metadataNames) {
         System.out.println(name+ " : " + metadata.get(name));
      }
   }
}