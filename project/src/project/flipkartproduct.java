package project;

public class flipkartproduct {
	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.OutputStream;
	import java.util.NoSuchElementException;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;
	import org.apache.poi.hssf.usermodel.HSSFCell;
	import org.apache.poi.hssf.usermodel.HSSFRow;
	import org.apache.poi.hssf.usermodel.HSSFSheet;
	import org.apache.poi.hssf.usermodel.HSSFWorkbook;
	import org.apache.poi.ss.usermodel.Cell;
	import org.apache.poi.ss.usermodel.CellType;
	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.ss.usermodel.Sheet;
	import org.apache.poi.ss.usermodel.Workbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.interactions.Actions;

	public class Flipkartproduct  {

		public static void main(String[] args) throws NoSuchElementException, IOException{

			// setting file location and reading WorkBook
			File file=new File("D:\\login.xls");
			FileInputStream fs = new FileInputStream(file);
			HSSFWorkbook workbook=new HSSFWorkbook(fs);
			HSSFSheet sheetr=workbook.getSheetAt(0);			
			HSSFRow rowr=sheetr.getRow(1);
			//username
			HSSFCell cellr = rowr.getCell(0);	
			cellr.setCellType(CellType.STRING); 
			String name=cellr.getStringCellValue();	
			//password
			HSSFCell cell1 = rowr.getCell(1);
			String key = cell1.getStringCellValue();

			System.setProperty("webdriver.chrome.driver", "C:\\selenium\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			Actions action =new Actions(driver);
			driver.get("https://www.flipkart.com/");
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

			WebElement username=driver.findElement(By.xpath("//*[@class='_2IX_2- VJZDxU']"));
			username.sendKeys(name);
			WebElement password=driver.findElement(By.xpath("//*[@class='_2IX_2- _3mctLh VJZDxU']"));
			password.sendKeys(key+ Keys.ENTER);
			WebElement search=driver.findElement(By.xpath("//*[@name='q']"));
			search.sendKeys("tshirts for boys"+Keys.ENTER);

			//Create New workbook for store product details
			Workbook wb = new HSSFWorkbook();		
			Sheet sheet =wb.createSheet("PRODUCT");
			Row row =sheet.createRow(0);

			Cell cell=row.createCell(0);
			cell.setCellValue("SNO");
			row.createCell(1).setCellValue("BRAND NAME");
			row.createCell(2).setCellValue("PRODUCT NAME");
			row.createCell(3).setCellValue("AMOUNT");
			row.createCell(4).setCellValue("MATERIAL");
			row.createCell(5).setCellValue("RATING");

			int j=1;
			
			for(int i=2;i<=11;i++){	

				for(int k=1;k<=4;k++){
					String path ="//*[@id='container']/div/div[3]/div[1]/div[2]/div["+i+"]/div/div["+k+"]";
					driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
					System.out.println(" ("+i+" , "+k+")");

					//Select multiple Product 
					WebElement product=driver.findElement(By.xpath(path));
					action.moveToElement(product).click().perform();

					//Store the parent & child windows
					String oldwindow =driver.getWindowHandle();
					Set<String> handles=driver.getWindowHandles();

					for(String newwindow:handles){
						driver.switchTo().window(newwindow);
					}			

					//Brand Name
					WebElement brnd=driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span[1]"));
					String company=brnd.getText();

					//Product name
					WebElement namee=driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[2]/div/div[1]/h1/span[2]"));
					String brand=namee.getText();

					//Product Amount
					WebElement amount=driver.findElement(By.xpath("//*[@class='_30jeq3 _16Jk6d']"));
					String price =amount.getText();

					//Product Details
					WebElement drop=driver.findElement(By.xpath("//*[@class='col col-11-12 _2cLjiM']"));
					action.moveToElement(drop).click().perform();

					//Product Material
					WebElement sze=driver.findElement(By.xpath("//*[@id='container']/div/div[3]/div[1]/div[2]/div[6]/div[3]/div/div/div[2]/div/div/div[6]/div[2]"));
					String size=sze.getText();

					//Product Rating & Review
					WebElement rtg=driver.findElement(By.xpath("//*[@class='_2_R_DZ']"));
					String rating =rtg.getText();

					//Creating new row & insert the values 
					Row newrow = sheet.createRow(j);
					newrow.createCell(0).setCellValue(j);
					newrow.createCell(1).setCellValue(company);
					newrow.createCell(2).setCellValue(brand);
					newrow.createCell(3).setCellValue(price);
					newrow.createCell(4).setCellValue(size);
					newrow.createCell(5).setCellValue(rating);

					//To Close The Child Window 			
					driver.close();
					driver.switchTo().window(oldwindow);
					OutputStream fileOut = new FileOutputStream("D://product.xls"); 
					wb.write(fileOut);
					j++;				
				}
			}
			workbook.close();
			wb.close();			
			driver.quit();
			System.out.println("******DETAILS UPLOADED******");
			system.out.println("done")
		}
	}

}
