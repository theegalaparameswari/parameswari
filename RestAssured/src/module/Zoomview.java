package module;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Zoomview {
	
  WebDriver driver;
  String COCfrom = "August 15, 2022  00:00";
  String COCto = "August 16, 2022  23:59";
  String trisulfrom = "2022-08-22 00:00:00";
  String trisulto = "2022-08-22 23:59:00";
  @Test
  public void main() throws InterruptedException {
	  
	  WebDriverManager.chromedriver().setup();
	 WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		/*
		 * Login to Dev COC Module
		 */
		driver.get("https://dev.zybisys.com/login");
		driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys("abdul@gmail.com");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Zybisys@321");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Thread.sleep(5000);
		/*
		 * Script for Expanding polyline in Dashboard
		 * 
		 */
		 WebElement svgObject = driver.findElement(By.xpath("(//*[local-name()='svg']/*[local-name()='polyline'])[1]"));
		    Actions builder = new Actions(driver);
		    builder.click(svgObject).build().perform();
		    Thread.sleep(5000);
		    /*
		     * Click on Manage Submodule Script*
		     * 
		     */
	    WebElement svgObject1 = driver.findElement(By.xpath("(//*[local-name()='svg']//*[local-name()='path'])[5]"));
	    Actions builder1 = new Actions(driver);
	    builder.click(svgObject1).build().perform();
	    Thread.sleep(5000);
	    /*
	     * Click on ZoomView
	     */
	    driver.findElement(By.xpath("//span[text()='Zoom View']")).click();
	    Thread.sleep(5000);
	    /*Click on Internet
	     * 
	     */
	    driver.findElement(By.xpath("//span[text()='Internet']")).click();
	    /* 
	     * Select Yesterday from the dropdown
	     */
	    Select objSelect=new Select(driver.findElement(By.xpath("//select[@name='Interval']")));
	    objSelect.selectByVisibleText("Yesterady");
	    Thread.sleep(2000);
	    /*
	     * Click on View More
	     */
	    JavascriptExecutor js = (JavascriptExecutor) driver;
	    while(driver.findElements(By.xpath("//span[text()='View More']")).size()>=1) {
	    	js.executeScript("arguments[0].scrollTo(0,900)", driver.findElement(By.xpath("//div[@id='coc-panel-tooltip-action']")));
	    	driver.findElement(By.xpath("//span[text()='View More']")).click();
	    }
	

	
		Thread.sleep(5000);
	    HashMap<String,String> map=new HashMap<String,String>();
	    List<WebElement> internalIps = driver.findElements(By.xpath("//div[text()='External IP Communication']//parent::div//parent::div/table/tbody/tr/th[1]"));
	    List<WebElement> received = driver.findElements(By.xpath("//div[text()='External IP Communication']//parent::div//parent::div/table/tbody/tr/td[2]"));
	    List<WebElement> transmitted = driver.findElements(By.xpath("//div[text()='External IP Communication']//parent::div//parent::div/table/tbody/tr/td[3]"));
	    List<WebElement> total = driver.findElements(By.xpath("//div[text()='External IP Communication']//parent::div//parent::div/table/tbody/tr/td[4]"));
	    List<WebElement> externalIp = driver.findElements(By.xpath("//div[text()='External IP Communication']//parent::div//parent::div/table/tbody/tr/td[1]"));
	    Float totalreceved = 0f;
	    Float totaltransmitted = 0f;
	    Float totalMb = 0f;
	    String internalIp ="";
	    List<String> internalIpstrs = new ArrayList<>();
	    for(int i=0; i<internalIps.size(); i++) {
	    	internalIp = internalIps.get(0).getText().trim();
	    	totalreceved = totalreceved + getBandWidthInMb(received.get(i).getText().trim());
	    	System.out.println("Received "+received.get(i).getText().trim());
	    	totaltransmitted = totaltransmitted + getBandWidthInMb(transmitted.get(i).getText().trim());
	    	System.out.println("transmitted "+transmitted.get(i).getText().trim());
	    	totalMb = totalMb + getBandWidthInMb(total.get(i).getText().trim());
	    	System.out.println("total "+total.get(i).getText().trim());
	    
	    }
	    
	    /* TestScript for Trisul Login
	     * 
	     */
	    driver.get("http://10.192.1.53:3000/trisul_web_user/login?dbc_id=");
	    Thread.sleep(1000);
	    driver.findElement(By.xpath("//input[@type='text']")).sendKeys("parameswari");
	    driver.findElement(By.xpath("//input[@type='password']")).sendKeys("S00TVa3U");
	    driver.findElement(By.xpath("//input[@type='submit']")).click();
	    Thread.sleep(5000);
	    /*
	     * Click on Reports
	     * 
	     */
	    driver.findElement(By.xpath("//a[contains(text(), 'Reports')]")).click();
	    Thread.sleep(1000);
	    /*Click on Readymade
	     * 
	     */
	    driver.findElement(By.xpath("//a[contains(text(), 'Readymade')]")).click();
	    /*
	     * Click on Endpoint Application
	     * 
	     */
	    driver.findElement(By.xpath("//a[contains(text(), 'Endpoints and Applications')]")).click();

/*
 * Click on Static IP Report 
 * 
 */
    	driver.findElement(By.xpath("//label[text()=' Static IP Report ']//following::div[1]/input[1]")).sendKeys(internalIp);
	    driver.findElement(By.xpath("//label[contains(text(),'Static IP Report')]")).click();
	    /*
	     * Click on Yesterday
	     * 
	     */
	    Select objselect = new Select(driver.findElement(By.xpath("(//select[@class='form_time_select'])[12]")));
	    objselect.selectByVisibleText("Yesterday");
	    
/*
 * Script for Total, Received, Transmitted bandwidth
 * 
 */
    	Thread.sleep(7000);
    	js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//h4[contains(text(),'Total Traffic')]")));
    	System.out.println("Total in Total="+driver.findElement(By.xpath("//h4[contains(text(),'Total Traffic')]")).getText());
    	Float totalTrisul = getBandWidthInMb(driver.findElement(By.xpath("//h4[contains(text(),'Total Traffic')]")).getText().replace("Total Traffic", "").replace("-", "").trim().replace(" ", "").trim());
    	js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//legend[contains(text(),'Received Traffic')]")));
    	System.out.println("Total in Received="+driver.findElement(By.xpath("//legend[contains(text(),'Received Traffic')]")).getText());
    	Float receivedTrisul = getBandWidthInMb(driver.findElement(By.xpath("//legend[contains(text(),'Received Traffic')]")).getText().replace("Received Traffic", "").replace("-", "").trim().replace(" ", "").trim());
    	js.executeScript("arguments[0].scrollIntoView()",driver.findElement(By.xpath("//legend[contains(text(),'Transmit Traffic')]")));
    	System.out.println("Total Transmitted in Trisul="+driver.findElement(By.xpath("//legend[contains(text(),'Transmit Traffic')]")).getText());
    	Float transmittedTrisul = getBandWidthInMb(driver.findElement(By.xpath("//legend[contains(text(),'Transmit Traffic')]")).getText().replace("Transmit Traffic", "").replace("-", "").trim().replace(" ", "").trim());
    	driver.findElement(By.xpath("//a[@class='pdfbtn pull-right text-danger']")).click();
    	
    	if(Math.floor(totalreceved) != Math.floor(receivedTrisul)) {
    		System.out.println("Receved in value in Coc="+totalreceved+" not matched with trisul="+receivedTrisul);
    	}
    	
    	if(Math.floor(totaltransmitted) != Math.floor(transmittedTrisul)) {
    		System.out.println("Transmitted in value in Coc="+totaltransmitted+"MB not matched with trisul="+transmittedTrisul+"MB");
    	}
    	
    	if(Math.floor(totalMb) != Math.floor(totalTrisul)) {
    		System.out.println("Total in value in Coc="+totalMb+"MB not matched with trisul="+totalTrisul+"MB");
    	}
    
  }
  
  private Float getBandWidthInGB(String text) {
		if(text.contains("GB")) {
			return Float.parseFloat(text.replace("GB", "").trim());	
		}else if(text.contains("MB")) {
			return Float.parseFloat(text.replace("MB", "").trim())/1000;
		}else {
			return 0f;
		}
	}
  private Float getBandWidthInMb(String text) {
		if(text.contains("GB")) {
			return Float.parseFloat(text.replace("GB", "").trim())*1000;	
		}else if(text.contains("MB") ||text.contains("MB")) {
			return Float.parseFloat(text.replace("MB", "").trim());
		}else if(text.contains("KB")) {
			return Float.parseFloat(text.replace("KB", "").trim())/1000;
		}else if(text.contains("Bytes")){
			return Float.parseFloat(text.replace("Bytes", "").trim())/1000/1000;
		}else {
			System.out.println("text");
			return 0f;
		
	}
}
}

	    
	    
