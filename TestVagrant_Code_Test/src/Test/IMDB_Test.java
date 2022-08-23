package Test;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;


import PageObject.IMDB;
import Resource.Base;
import io.github.bonigarcia.wdm.WebDriverManager;


public class IMDB_Test extends Base {

	public  WebDriver driver;
	public String ReleaseDate,Country;
	@Test(priority=1)
	public void ValidateImdb() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		 driver = initilazier();
		 driver.get("https://www.imdb.com/title/tt9389998/?ref_=nv_sr_srsg_0");
		 driver.manage().window().maximize();
		 IMDB imdb = new IMDB(driver);
		  ReleaseDate = imdb.getreleasedate().getText();
		  Country=imdb.getcountry().getText();
		 System.out.println("Release Date : "+ReleaseDate);
		 System.out.println("Country of Origin : "+Country);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView()",imdb.getreleasedate());
			js.executeScript("arguments[0].style.border='3px dotted red'", imdb.getreleasedate());
			getscreenshot();
			
	}
@AfterTest
public void TearDown()
{
	driver.close();
}

public void getscreenshot() throws IOException
{
	 File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir")+"\\ScreenShots\\screenshot1.png");
		FileUtils.copyFile(src, dest);
}
	
}
