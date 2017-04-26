import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
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

public class SeleniumTest1 {

	//Junit Test
	@org.junit.Test	
	public void test1() throws InterruptedException, IOException {
		
				
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\optimus120\\Desktop\\ANSHUL Desktop\\Selanium\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Maximizing webdriver window.
		//driver.manage().window().maximize();
		
		// Defining Explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		//Navigating to destined Url.
		driver.get("https://www.freecharge.in/");
		
		//Verifying all the brands
		driver.getPageSource().contains("Airtel");
		driver.getPageSource().contains("Aircel");
		
		//Input invalid no.
		driver.findElement(By.xpath("//input[@name='number']")).sendKeys("1234");
		
		WebElement errorIconClass = driver.findElement(By.className("error-icon"));
		Assert.assertEquals(true, errorIconClass.isDisplayed());
		
		driver.findElement(By.xpath("//input[@name='number']")).clear();
		//Input invalid no.
		driver.findElement(By.xpath("//input[@name='number']")).sendKeys("9654255645");
		
		// Verifying that prepaid is selected
		Boolean prepaidCheckBox = driver.findElement(By.xpath("//span[text()='Prepaid']/preceding-sibling::input")).isSelected();
		Assert.assertTrue(prepaidCheckBox);
		
		Thread.sleep(2000);
		
		// Verifying that prepaid is selected
		//String operatorBox = new Select(driver.findElement(By.xpath("//select[@name = 'operator']"))).getFirstSelectedOption().getText();
		String operatorBox = new Select(driver.findElement(By.xpath("//select[@name = 'operator']"))).getFirstSelectedOption().getText();
		Assert.assertEquals(operatorBox, "Vodafone");
		
		List<WebElement> totalOperators = driver.findElements(By.xpath("//select[@name = 'operator']"));
		System.out.println("Total no. of operators are:" + totalOperators);
		
		//Proceed
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Proceed']")));
		driver.findElement(By.xpath("//button[text() = 'Proceed']")).click();
		
		String priceField = driver.findElement(By.xpath("//input[@placeholder = 'Enter Amount']")).getText();
		System.out.println("price fieldis:."+priceField+".itis");
		Assert.assertEquals(priceField,"");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._3G6A5")));
		driver.findElement(By.cssSelector("button._3G6A5")).click();
		
		//Total no. of recommended plans are:
		List<WebElement> recommendedPlans = driver.findElements(By.cssSelector("p._2WAF-"));
		int recommendedPlanSize = recommendedPlans.size();
		System.out.println("11. Total Recommended Plans: "+ recommendedPlanSize );
		
		List<WebElement> planTitle = driver.findElements(By.cssSelector("p._264pV"));
		List<WebElement> fullTalkTime = driver.findElements(By.cssSelector("p._2WAF-[data-reactid*='FULL TT']"));
		//List<WebElement> fullTalkTimeValue = driver.findElements(By.cssSelector("p[data-reactid*='FULL TT']:contains(Rs)"));
		System.out.println(" Full TalkTime Plans: "+ fullTalkTime.size() );
		int[] ttValue = new int[10];
		for(int i =0;i<fullTalkTime.size();i++)
		{
			ttValue[i] = Integer.parseInt(fullTalkTime.get(i).getText().substring(1));
			System.out.println(ttValue[i]);
			
		}	
		int smallest = ttValue[0];
		for(int i =0;i<fullTalkTime.size();i++)
		{	
			if(smallest >= ttValue[i])
				smallest = ttValue[i];
		}
		
		System.out.println("12.Most effective recommnded plan is of Rs. "+smallest);
		
//		
	}
}