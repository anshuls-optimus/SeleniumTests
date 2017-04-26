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

public class SeleniumTest3 {

	//Junit Test
	@org.junit.Test	
	public void test1() throws InterruptedException, IOException {
		
				
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\optimus120\\Desktop\\ANSHUL Desktop\\Selanium\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// Maximizing webdriver window.
		//driver.manage().window().maximize();
		
		// Defining Explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		//Navigating to destined Url.
		driver.get("https://www.freecharge.in/");
		
		//Input no.
		WebElement num = driver.findElement(By.cssSelector("input[name ='number']"));
		num.sendKeys("9654255645");
		Thread.sleep(3000);
		//Proceed
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.btn._2eaVn")));
		driver.findElement(By.cssSelector("button.btn._2eaVn")).click();
		
		Thread.sleep(3000);
		//View all planscssSelector
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button._3G6A5")));
		driver.findElement(By.cssSelector("button._3G6A5")).click();
		
		List<WebElement> recommendedPlans = driver.findElements(By.cssSelector("p._2WAF-"));
		int recommendedPlanSize = recommendedPlans.size();
		System.out.println("3. Total Recommended Plans: "+ recommendedPlanSize );
		List<WebElement> planTitle = driver.findElements(By.cssSelector("p._264pV"));
		List<WebElement> fullTalkTime = driver.findElements(By.cssSelector("p._2WAF-[data-reactid*='FULL TT']"));
		//List<WebElement> fullTalkTimeValue = driver.findElements(By.cssSelector("p[data-reactid*='FULL TT']:contains(Rs)"));
		System.out.println("Full TalkTime Plans: "+ fullTalkTime.size() );
		int[] ttValue = new int[10];
		for(int i =0;i<fullTalkTime.size();i++)
			ttValue[i] = Integer.parseInt(fullTalkTime.get(i).getText().substring(1));
			
		int smallest = ttValue[0];
		for(int i =0;i<fullTalkTime.size();i++)
		{	
			if(smallest >= ttValue[i])
				smallest = ttValue[i];
		}
		
		System.out.println("4. Most effective recommnded plan is of Rs. "+smallest);
		
		System.out.println("4.a) Full Talktime and Free minutes plans are:");
		for (int i=0;i<planTitle.size();i++)
		{
			if(planTitle.get(i).getText().contains("min free")&&planTitle.get(i).getText().contains("Full Talktime"))
				System.out.println(planTitle.get(i).getText());			
		}
		
		System.out.println("4.b) Another combo plans with internet and sim free are: ");
		for (int i=0;i<planTitle.size();i++)
		{
			if(planTitle.get(i).getText().contains("GB")&&planTitle.get(i).getText().contains("SIM"))
				System.out.println(planTitle.get(i).getText());			
		}
		
		int smsPlansCount=0;
		for (int i=0;i<planTitle.size();i++)
		{
			
			if(planTitle.get(i).getText().contains("SMS"))
			{	
				smsPlansCount++;
				//System.out.println(i+". SMS Plan: "+ planTitle.get(i).getText());
			}
		}
		
		System.out.println("4.c) Total SMS plans are: "+ smsPlansCount );
		
		// 5. Navigating to full talktime plan
		driver.findElement(By.cssSelector("._13hzN>span:nth-child(2)")).click();
		
		Thread.sleep(3000);
		List<WebElement> fullTTtabPlans = driver.findElements(By.cssSelector("p._264pV"));
		for(int i =0;i<fullTTtabPlans.size();i++)
		{	
			if(fullTTtabPlans.get(i).getText().contains("Full Talktime")) 
			{
				//System.out.println(fullTTtabPlans.get(i).getText());
			}
			else
			{
				throw new InterruptedException("5. Full Talktime not present in full talktime column.");
			}
		}
		System.out.println("5. All Full Talktimes are present inside full TT plans.");
		
		// 6. Navigating to top up tab.
		driver.findElement(By.cssSelector("._13hzN>span:nth-child(3)")).click();
		List<WebElement> topUpValidity = driver.findElements(By.cssSelector("p._34E11"));
		int counter = 0;
		int [] integerValidityValues = new int[100];
		
		for(int i=0;i<topUpValidity.size();i++)
		{
			//System.out.println("topUp Validities: "+topUpValidity.get(i).getText());
			if(!(topUpValidity.get(i).getText().contains("Unlimited Days")))
			{
				integerValidityValues[counter]= Integer.parseInt(topUpValidity.get(i).getText().split(" ")[0]);
				counter++;
			}
			else
			{
				//System.out.println("Least validity is Unlimited Days!!!");
			}	
		}
		
		int leastValidity = integerValidityValues[0];
		for(int i=0;i<integerValidityValues.length;i++)
		{
			if(leastValidity >= integerValidityValues[i])
			leastValidity = integerValidityValues[i];
		}
		if(leastValidity!=0)
			System.out.println("6. least validity plan is :  "+ leastValidity );
		else
			System.out.println("6. Least validity is Unlimited Days!!!");
		
		// 7. Navigating to 2G tab.
		driver.findElement(By.cssSelector("._13hzN>span:nth-child(6)")).click();
		List<WebElement> twoGPlans = driver.findElements(By.cssSelector("p._2WAF-"));
		System.out.println("7. Total number of 2G plans are: "+ twoGPlans.size());
//		
	}
}