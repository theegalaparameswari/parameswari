package module;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.response.Response;

public class DashBoardInstances {
	
	
	
	/*
	 * This is not required because it is in UAT Admin not Dev Admin
	 * 
	 */
	
	WebDriver driver;
	
	int totalsize=0;
	String customerName="Dummy FINANCIAL SERVICES PRIVATE LIMITED";
	String customerEmail="trade@gmail.com";
	String customerPassWord="Zybisys@123";
	List<String> instaceNames = new ArrayList<String>();
	@Test
	public void instance() throws InterruptedException {
		
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		


		driver.get("https://dev.zybisys.com/login");
		Reporter.log(
				"navigated successfulluy to https://dev.zybisys.com/login</br>");
		driver.findElement(By.xpath("//input[@id='login-email']")).sendKeys(customerEmail);
		Reporter.log("entered successfully email trade@gmail.com </br>");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(customerPassWord);
		Reporter.log("entered succesfully password Zybisys@123 </br>");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Reporter.log("Clicked on login succesfully");
		Thread.sleep(5000);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollTo(0,500)", driver.findElement(By.xpath("//div[@id='coc-panel-tooltip-action']")));
		List<WebElement> Totalinstances=driver.findElements(By.xpath("//div[@class='pointer']/p"));
	
		Totalinstances.forEach(instance->{
			instaceNames.add(instance.getText().trim());
		});
		//instaceNames.stream().forEach(test->System.out.println(test));
		System.out.println("Total instances in coc=" +Totalinstances.size());
		driver.quit();
		

	}
	
	

	@Test
	public void login() throws InterruptedException {
       
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://uat-admin-console.zybisys.com/customers/details/5eb25f0c37ad1d2e13d2abf9");
		Reporter.log(
				"navigated successfulluy to https://uat-admin-conssole.zybisys.com/customers/details/5eb25f0c37ad1d2e13d2abf9</br>");
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("admin@zybisys.com");
		Reporter.log("entered successfully email admin@zybisys.com </br>");
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Zyb1&y&0");
		Reporter.log("entered succesfully password Zyb1&y&0 </br>");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		Reporter.log("Clicked on login succesfully");

		driver.manage().window().maximize();
		Thread.sleep(5000);
		driver.findElement(By.xpath("//a[@href='/vm-masters']")).click();
		Thread.sleep(5000);		
		driver.findElement(By.xpath("//span[text()='Show All Customer']")).click();
		Thread.sleep(5000);
		while(driver.findElements(By.xpath("//div[@title='"+customerName+"']")).size()==0)
			driver.findElement(By.xpath("//div[@class='ant-select-selector']/span/input")).sendKeys(Keys.DOWN);
		WebElement element = driver.findElement(By.xpath("//div[@title='"+customerName+"']"));
	
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
		Thread.sleep(5000); 
		List<WebElement> Totalinstances=driver.findElements(By.xpath("//tbody/tr[@class=\"ant-table-row ant-table-row-level-0\"]/td[@class=\"ant-table-cell\"][2]/h5[1]"));
		System.out.println("Total instances in admin=" +Totalinstances.size());
		List<String> adminNames =  new ArrayList<String>();
		System.out.println("\nmissing instances in coc\n");
		
		Totalinstances.forEach(instance->{
			adminNames.add(instance.getText().trim());
		});
		
		adminNames.forEach(instance->{
			if(!instaceNames.contains(instance)) {
				System.out.println(instance+" "+instaceNames.contains(instance));
			}
			
		});
		
		System.out.println("\nmissing instances in admin\n");
		instaceNames.forEach(instance->{
			if(!adminNames.contains(instance)) {
				System.out.println(instance);
			}
		});
		
			
		
		driver.quit();
}
}



		


