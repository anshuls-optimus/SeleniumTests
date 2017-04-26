import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ById;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import com.sun.jna.platform.win32.DsGetDC.DOMAIN_CONTROLLER_INFO.ByReference;

import org.openqa.selenium.interactions.Actions;

public class SeleniumTest2 {
	
	//Junit Test
	@org.junit.Test	
	public void test1() throws InterruptedException, IOException, AWTException {
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\optimus120\\Desktop\\ANSHUL Desktop\\Selanium\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Maximizing webdriver window.
		//driver.manage().window().maximize();
		
		// Defining Explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		// Question. 1
		
		//Navigating to destined Url.
		driver.get("http://demoqa.com/draggable/");
		
		//drag and drop
		Actions act = new Actions(driver);
		WebElement Source = driver.findElement(By.xpath("//p[text()='Drag me around']"));
		act.dragAndDropBy(Source, 130, 120).perform();
		
		// Question. 2
		driver.navigate().to("http://www.toolsqa.com/automation-practice-switch-windows/");
		
		String winHandleBefore = driver.getWindowHandle();
		//driver.get("http://demoqa.com/draggable/");
				
		driver.findElement(By.id("button1")).click();
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@class = ' preload-me']")));
		
		for (String winHandle : driver.getWindowHandles())
		{
			   // Switch to child window
			   driver.switchTo().window(winHandle);
		}
		
		String winHandleAfter = driver.getWindowHandle();
		
		driver.getPageSource().contains("QA Automation Tools Tutorial");
		
		
		
		
		// to close the child window.
		driver.close();

		// to switch to parent window.
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(4000);
		driver.findElement(By.xpath("//h2[text()='1) Browser Windows']")).click();
		Thread.sleep(7000);
		
		//Question 3
		//JavascriptExecutor jse = (JavascriptExecutor)driver;
//		jse.executeScript("window.scrollBy(0,250)", "");
		
		Robot robot = new Robot();
		robot.keyPress(KeyEvent.VK_PAGE_DOWN);
		robot.keyRelease(KeyEvent.VK_PAGE_DOWN);
		Thread.sleep(7000);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("timingAlert")));
		driver.findElement(By.id("timingAlert")).click();
		
		// Get a handle to the open alert, prompt or confirmation
		Thread.sleep(10000);
		Alert alert = driver.switchTo().alert();
		// Get the text of the alert or prompt
		alert.getText();  
		// And acknowledge the alert (equivalent to clicking "OK")
		alert.accept();
		
		//Question 4 
		driver.navigate().to("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_select");
		Thread.sleep(2000);
		
//		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("iframewrapper")); //iframe ID is String
		WebElement iframe = driver.findElement(By.xpath("//iframe[@id = 'iframeResult']"));
		driver.switchTo().frame(iframe);
//		String attribute = driver.findElement(By.id("output-img")).getAttribute("src");
		String volvoOption = new Select(driver.findElement(By.xpath("//select"))).getFirstSelectedOption().getText();
		Assert.assertEquals(volvoOption, "Volvo");
		driver.switchTo().defaultContent();
		
		//Question 5
		driver.navigate().to("http://www.optimusinfo.com/contact-us/");
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement message_box = driver.findElement(By.id("avia_message_1"));
		message_box.sendKeys("123456789");		
		String enteredTextMessage = (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].value;",message_box);	
		System.out.println("Entered Text Message is " + enteredTextMessage);
		System.out.println("length of entered message is : " + enteredTextMessage.length());
		
	}

}
