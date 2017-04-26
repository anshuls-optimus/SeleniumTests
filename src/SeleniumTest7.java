import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
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

public class SeleniumTest7 implements Runnable{
	
	Thread t=new Thread();
    t.start();
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("In run...");
    }
	
	//Junit Test
	@org.junit.Test	
	public void test1() throws InterruptedException, IOException 
	{
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\optimus120\\Desktop\\ANSHUL Desktop\\Selanium\\chrome driver\\chromedriver.exe");
		WebDriver freechargeDriver = new ChromeDriver();
		freechargeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		
        
		
	}
	
	void gmail()
	{
		
	}
	void freecharge()
	{
		// Maximizing webdriver window.
		//driver.manage().window().maximize();
		
		// Defining Explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Navigating to destined Url.
		freechargeDriver.get("https://www.freecharge.in/");
		
		//Input no.
		WebElement num = freechargeDriver.findElement(By.cssSelector("input[name ='number']"));
		num.sendKeys("9654255645");
		Thread.sleep(3000);
		//Proceed
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn._2eaVn")));
		freechargeDriver.findElement(By.cssSelector("button.btn._2eaVn")).click();
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@class='_3mvx0' and text()='Login/Register']")));
		freechargeDriver.findElement(By.xpath("//a[@class='_3mvx0' and text()='Login/Register']")).click();
		
		//username
		freechargeDriver.findElement(By.xpath("//input[@id='userName']")).sendKeys("anshul24sharma@gmail.com");
		//password
		freechargeDriver.findElement(By.xpath("//input[@id='password']")).sendKeys("");
		//Login
		freechargeDriver.findElement(By.xpath("//button[@class='btn _32_N-' and text()= 'Login']")).click();
		Thread.sleep(3000);
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@placeholder='Enter Otp']")));
		freechargeDriver.findElement(By.xpath("//input[@placeholder='Enter Otp']")).sendKeys(otp);
		
		freechargeDriver.findElement(By.xpath("//button[@class='btn i-hQn' and @type = 'submit']")).click();
	}
}