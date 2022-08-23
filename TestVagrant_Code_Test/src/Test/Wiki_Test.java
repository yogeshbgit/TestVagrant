package Test;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import PageObject.Wiki;
import Resource.Base;
import io.github.bonigarcia.wdm.WebDriverManager;


public class Wiki_Test extends Base {
public  WebDriver driver;

	
	@Test(priority=1)
	public void Validatewiki() throws IOException
	{
		WebDriverManager.chromedriver().setup();
		IMDB_Test it = new IMDB_Test();
		it.ValidateImdb();
		it.TearDown();
		driver = initilazier();
		 driver.get("https://en.wikipedia.org/wiki/Pushpa:_The_Rise");
		 driver.manage().window().maximize();
		 Wiki wiki = new Wiki(driver);
		 String ReleaseDate = wiki.getreleasedate().getText();
		 String Country=wiki.getcountry().getText();
		 System.out.println("Release Date : "+ReleaseDate);
		 System.out.println("Country of Origin : "+Country);
		 Assert.assertEquals(it.Country,Country);
		 Assert.assertEquals(it.ReleaseDate,ReleaseDate);
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		 js.executeScript("arguments[0].scrollIntoView()",wiki.getreleasedate());
	}

	
	
	@AfterTest
	public void TearDown()
	{
		driver.close();
	}
}
