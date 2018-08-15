package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {

	WebDriver driver;
	String url = "https://www.dice.com/";
	
	@Test
	public void jobSearch() throws InterruptedException{
		WebDriverManager.chromedriver().setup();
//		System.setProperty("webdriver.chrome.driver", "/Users/RizaCepni/Documents/workspace/Drivers/chromedriver");
		driver = new ChromeDriver();
		
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get(url);
		
		WebElement jobTitle  = driver.findElement(By.id("search-field-keyword"));
		WebElement zipCode = driver.findElement(By.id("search-field-location"));
		WebElement findButton =  driver.findElement(By.id("findTechJobs"));
	
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";
		
		if(actualTitle.equalsIgnoreCase(expectedTitle)){
			System.out.println("Step PASS. Dice homepage successfully loaded");
		}else{
			System.out.println("Step FAIL. Dice homepage did not load successfully");
			throw new RuntimeException("Step FAIL. Dice homepage did not load successfully");
		}
		
		
		jobTitle.clear();		
		jobTitle.sendKeys("Java developer");
		zipCode.clear();
		zipCode.sendKeys("22315");
		findButton.submit();  // or click(); sometimes we need to submit() for button tags
		
		
		//I do not know why but below easiest way did not work..
//		String count = driver.findElement(By.id("posiCountMobileId")).getText();
		
		String result = driver.findElement(By.xpath("//h4[@class='pull-left posiCount sort']")).getText();
		String[] results = result.split(" ");
		
		System.out.println(results[4] +" number of positions found.");
		
		int numberPositions = Integer.parseInt(results[4].replace(",", ""));
		
		if(numberPositions > 0){
			System.out.println("Congrats! You have " + numberPositions +" chances for a new job.");
		}else{
			System.out.println("No worries! This is not the end of world. You will find something else.");
		}
		
	}
	
	
	@AfterTest
	public void tearDown(){
		driver.close();
	}
	
	
	
}
