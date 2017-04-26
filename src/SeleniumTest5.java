import java.awt.Toolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.datatransfer.StringSelection;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;
//import java.util.logging.Logger;

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



import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;

import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import org.apache.log4j.Appender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.SimpleLayout;

public class SeleniumTest5 {
	
	int i=0;

	//Junit Test
	@org.junit.Test	
	public void test1() throws InterruptedException, IOException {
		
		Logger log = Logger.getLogger("SeleniumTest5");
		PropertyConfigurator.configure("C:\\Workspace\\SeleniumTestSeries\\src\\log4j.properties");
		
		
		log.info("starting Test");
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\optimus120\\Desktop\\ANSHUL Desktop\\Selanium\\chrome driver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		// Maximizing webdriver window.
	//	driver.manage().window().maximize();
		
		// Defining Explicit wait.
		WebDriverWait wait = new WebDriverWait(driver, 120);
		
		//Navigating to destined Url.
		log.info("logging in");
		driver.get("https://www.freecharge.in/"); 		
		
		//Input no.
		driver.findElement(By.xpath("//input[@name='number']")).sendKeys("9654255645");
		
		Thread.sleep(3000);
		//Proceed
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'Proceed']")));
		driver.findElement(By.xpath("//button[text() = 'Proceed']")).click();
		
		Thread.sleep(3000);
		log.info("view plans");
		//View all plans
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text() = 'View all plans']")));
		driver.findElement(By.xpath("//button[text() = 'View all plans']")).click();
		
		Workbook wb = new XSSFWorkbook();
		File file = new File("Recommended Plans.xlsx");
	    FileOutputStream fileOut = new FileOutputStream("Recommended Plans.xlsx");
	    CreationHelper createHelper = wb.getCreationHelper();
	    Sheet sheet1 = wb.createSheet("new sheet");
	    Row row1 = sheet1.createRow((short)0);
	    row1.createCell(0).setCellValue(createHelper.createRichTextString("Plan Name"));
	    row1.createCell(1).setCellValue(createHelper.createRichTextString("Plan Type"));
	    row1.createCell(2).setCellValue(createHelper.createRichTextString("Plan Validity"));
	    row1.createCell(3).setCellValue(createHelper.createRichTextString("Talk time"));
	    int noOfColumns = sheet1.getRow(0).getLastCellNum();
	    System.out.println(noOfColumns);
	    
	    //Fetching values for rows
	    log.info("exporting values in xlsx file");
	    Row rowi;
	    List<WebElement> myPlans=driver.findElements(By.className("_264pV"));
	    List<WebElement> validity=driver.findElements(By.xpath("//span[text()='Validity: ']/following-sibling::span"));
	    List<WebElement> talkTime=driver.findElements(By.xpath("//p[contains(text(),'Talktime Rs.')]"));
	    List<WebElement> planAmount=driver.findElements(By.className("_2WAF-"));
	    System.out.println(planAmount.size());
	    
	    
	    
	    
	    // Printing My plans in first column
	     for(int i=0;i<myPlans.size()||i<validity.size()||i<talkTime.size()||i<planAmount.size(); i++)
	    {
	    	 System.out.println(planAmount.size());
	    	 //loading text of each element in to array all_elements_text
	    	 rowi = sheet1.createRow(i+1);
	    	 if(i<myPlans.size())
	    	 {
	    		Cell cell = rowi.createCell(0);
	    		cell.setCellValue(myPlans.get(i).getText());
	    		if(myPlans.get(i).getText().contains("Full"))
	    		{
	    			XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();
                    style.setFillForegroundColor(HSSFColor.LIME.index);
                    style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

                    XSSFFont font = (XSSFFont) wb.createFont();
                    font.setColor(HSSFColor.RED.index);
                    style.setFont(font);
                    cell.setCellStyle(style);
	    		}
	    		    		 
	    	 }
	    	 if(i<planAmount.size())
	    		 rowi.createCell(1).setCellValue(planAmount.get(i).getText());
	    	 if(i<validity.size())
	    		 rowi.createCell(2).setCellValue(validity.get(i).getText());
	    	 if(i>myPlans.size()&&i>validity.size()&&i>talkTime.size()&&i>planAmount.size())
	    		 break;
	    }
	    
	    wb.write(fileOut);
	    fileOut.close();	
	    wb.close();
	    log.info("exit");
	    
	    Files.move(Paths.get("Recommended Plans.xlsx"), Paths.get("Execution Results/Recommended Plans.xlsx"));
	}
	
}