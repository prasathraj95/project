package project;


	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.util.List;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;

	import org.apache.poi.ss.usermodel.Row;
	import org.apache.poi.xssf.usermodel.XSSFSheet;
	import org.apache.poi.xssf.usermodel.XSSFWorkbook;
	import org.openqa.selenium.By;
	import org.openqa.selenium.Keys;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;

	public class FlipkartToExcel
	{

		public static void main(String[] args) throws Exception 
		{
			File src = new File("F:\\Excel\\flipkart.xlsx");
			File src1 = new File("F:\\Excel\\flipkart1.xlsx");
			FileInputStream fis = new FileInputStream(src);
			XSSFWorkbook book = new XSSFWorkbook(fis);
			XSSFSheet sheet = book.getSheetAt(0);
			String url = sheet.getRow(0).getCell(0).getStringCellValue();
			String username = sheet.getRow(1).getCell(0).getStringCellValue();
			String password = sheet.getRow(2).getCell(0).getStringCellValue();

			System.setProperty("webdriver.chrome.driver", "F:\\Drivers\\Chrome Dr\\chromedriver.exe");
			WebDriver driver = new ChromeDriver();
			//driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			driver.get(url);
			String parent = driver.getWindowHandle();

			//driver.findElement(By.xpath("//*[@class='_1_3w1N']")).click();
			driver.findElement(By.xpath("//*[@class='_2IX_2- VJZDxU']")).sendKeys(username);
			driver.findElement(By.xpath("//*[@class='_2IX_2- _3mctLh VJZDxU']")).sendKeys(password);
			driver.findElement(By.xpath("//*[@class='_2KpZ6l _2HKlqd _3AWRsL']")).click();
			driver.findElement(By.xpath("//*[@class='_3704LK']")).sendKeys("SAMSUNG Galaxy A73 5G",Keys.ENTER);

			FileOutputStream fos = new FileOutputStream(src);
			sheet = book.getSheetAt(1);
			Row ro = sheet.createRow(0);
			ro.createCell(0).setCellValue("Product Id");
			ro.createCell(1).setCellValue("Product Title");
			ro.createCell(2).setCellValue("In The Box");
			ro.createCell(3).setCellValue("Model Number");
			ro.createCell(4).setCellValue("Model Name");
			ro.createCell(5).setCellValue("Color");
			ro.createCell(6).setCellValue("Browse Type");
			
			try {
				int row=1;
				int cell=0;
				int rowforspec=1;
				for(int i=1;i<=10;i++)		
				{
					int id = i;
					String prodId = Integer.toString(id);

					String intToString = Integer.toString(i);
					//String prodPaths = "(//*[@class='_396cs4 _3exPp9'])"+"["+ "\""+intToString+"\"" +"]";
					String prodPaths1 = "(//*[@class='_4rR01T'])"+"["+ intToString +"]";
					System.out.println(prodPaths1);
					Thread.sleep(5000);
					driver.findElement(By.xpath(prodPaths1)).click();

					Set<String> window = driver.getWindowHandles();
					for (String control : window)
					{
						driver.switchTo().window(control); 
					}
					Thread.sleep(5000);

					String prodName = driver.findElement(By.xpath("//*[@class='B_NuCI']")).getText();
					ro = sheet.createRow(row); 

					ro.createCell(cell).setCellValue(prodId);

					ro.createCell(1).setCellValue(prodName);

					WebElement spec1 = driver.findElement(By.xpath("(//*[@class='_14cfVK'])[1]/tbody/tr[1]/td[2]"));
					String specific1 = spec1.getText();
					ro.createCell(2).setCellValue(specific1);

					WebElement spec2 =  driver.findElement(By.xpath("(//*[@class='_14cfVK'])[1]/tbody/tr[2]/td[2]"));
					String specific2 = spec2.getText();
					ro.createCell(3).setCellValue(specific2);

					WebElement spec3 = driver.findElement(By.xpath("(//*[@class='_14cfVK'])[1]/tbody/tr[3]/td[2]"));
					String specific3 = spec3.getText();
					ro.createCell(4).setCellValue(specific3);

					WebElement spec4 =  driver.findElement(By.xpath("(//*[@class='_14cfVK'])[1]/tbody/tr[4]/td[2]"));
					String specific4 = spec4.getText();
					ro.createCell(5).setCellValue(specific4);

					WebElement spec5 = driver.findElement(By.xpath("(//*[@class='_14cfVK'])[1]/tbody/tr[5]/td[2]"));
					String specific5 = spec5.getText();
					ro.createCell(6).setCellValue(specific5);

					driver.close();
					driver.switchTo().window(parent);	
					row++;
				}
				book.write(fos);
				book.close();
			}
			catch(Exception e)
			{

			}

		}

	}

}
