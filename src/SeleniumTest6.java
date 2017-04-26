import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumTest6 {
	
	By draggable = By.xpath("//div[@id = 'draggableview']");
	By droppable = By.xpath("//div[@id = 'droppableview']");
	By getActualText = By.xpath("//div[@id = 'droppableview']");
	By ProductCategory = By.xpath("//a[text() = 'Product Category']");
	By iMac = By.xpath("//a[text() = 'iMacs']");
	By searchBox = By.xpath("//input[@id='lst-ib']");
	
	@Test
	public void javaScriptTest() throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\optimus120\\Desktop\\ANSHUL Desktop\\Selanium\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		Actions act = new Actions(driver);
		// maximize browser
				//driver.manage().window().maximize();

				//Navigating to demoqa site
				driver.navigate().to("http://demoqa.com/droppable/");
				WebDriverWait wait = new WebDriverWait(driver, 120);	

				WebElement drag_me_around = driver.findElement(draggable);
							
				wait.until(ExpectedConditions.elementToBeClickable(drag_me_around));

				WebElement drop_me = driver.findElement(droppable);
							
				wait.until(ExpectedConditions.elementToBeClickable(drop_me));	

				act.dragAndDrop(drag_me_around, drop_me).perform();		
				String actualText=driver.findElement(getActualText).getText();
				Assert.assertEquals("Dropped!", actualText);
		
		
		
				
				//Navigating to demoqa site
				driver.navigate().to("http://store.demoqa.com/");

				// maximize browser
				//driver.manage().window().maximize();

				WebElement product_category = driver.findElement(ProductCategory);
				
				wait.until(ExpectedConditions.elementToBeClickable(product_category));	
				act.moveToElement(product_category).build().perform();

				WebElement mac = driver.findElement(iMac);
				
				wait.until(ExpectedConditions.elementToBeClickable(mac));	
				act.moveToElement(mac);
				act.click().build().perform();
				
				
				
				
				//Navigating to google site
				driver.navigate().to("https://www.google.co.in/");

				// maximize browser
				//driver.manage().window().maximize();

				WebElement search_text_box = driver.findElement(searchBox);

				search_text_box.sendKeys("Sample Highlight");

				//Creating JavaScriptExecuter Interface
				JavascriptExecutor js = (JavascriptExecutor)driver;
				for (int iCnt = 0; iCnt < 3; iCnt++) {
					//Execute javascript
					js.executeScript("arguments[0].style.border='4px groove green'", search_text_box);
					Thread.sleep(1000);
					js.executeScript("arguments[0].style.border=''", search_text_box);
				}

				act.keyDown(Keys.CONTROL).sendKeys(String.valueOf('\u0061')).sendKeys(Keys.BACK_SPACE).build().perform();
				
				
				
				
				
				//driver.manage().window().maximize();
				driver.get("https://www.w3schools.com/tags/tryit.asp?filename=tryhtml_input_accept");
				driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='iframeResult']")));

				WebElement choose_file = driver.findElement(By.xpath("//input[@type='file']"));
				choose_file.sendKeys("C:\\Users\\optimus103.OPTIMUSDOM\\Downloads\\PerfTestFiles-Confidential\\PerfTest-small2.pdf");
				WebElement submitBtn = driver.findElement(By.xpath("//input[@type='submit']"));
				submitBtn.click();
				WebElement file_text = driver.findElement(By.xpath("//div[@class='w3-large w3-border']"));
				Assert.assertEquals("Assertion mis=match", "pic=PerfTest-small2.pdf", file_text.getText());
				
				
		
		
		driver.close();

	}

	

}