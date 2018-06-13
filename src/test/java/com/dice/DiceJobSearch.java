package com.dice;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DiceJobSearch {
	public static void main(String[] args) {
		
		//System.setProperty("webdriver.chrome.driver", "/Users/emin/Documents/selenium dependencies/drivers/chromedriver");
		WebDriverManager.chromedriver().setup(); // If we write this we do not need above methods
		
		
		
		WebDriver driver = new ChromeDriver();
		
		//full screen
		driver.manage().window().fullscreen();
		
		//set universal wait time in case web page is slow
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);  // This will wait 10 second, different than Thread.sleep(), if the page is open in
		// 2 second, we do not have to 10 second but for thread we have to wait 10 second
		
		
		String url = "https://dice.com";
		driver.get(url);
		
		String actualTitle = driver.getTitle();
		String expectedTitle = "Job Search for Technology Professionals | Dice.com";  // will pass
//		String expectedTitle = "Job Search for Technology Professionals | Dice."; // will fail and throw exception
		
		if(actualTitle == expectedTitle) {
			System.out.println("Step PASS. Dice homepage succeccfully loaded");
		}else {
			System.out.println("Step FAIL. Dice homepage did not load  succeccfully");
			throw new RuntimeException("Step Fail. Dice homepage did not load  succeccfully "); // We wanted to use our java knowledge and throwed an exception
			// because we do not want our program to continue, if fails.
		}
		
		String keyword = "java developer";
		driver.findElement(By.id("search-field-keyword")).clear();
		driver.findElement(By.id("search-field-keyword")).sendKeys(keyword);
		
		String location = "22102";
		driver.findElement(By.id("search-field-location")).clear();
		driver.findElement(By.id("search-field-location")).sendKeys(location);
		
		driver.findElement(By.id("findTechJobs")).click();
		
		String count = driver.findElement(By.id("posiCountId")).getText();  // Since it's the total number of jobs found on the page, even it's number
		// still it's a string we use getText() method to call it
		System.out.println(count);
		
		//to ensure count is more than 0
		
        int countResult = Integer.parseInt(count.replace(",", ""));
		
		if(countResult > 0) {
			System.out.println( "Step PASS: Keyword : " + keyword +" search returned " +
			countResult +" results in " + location);
		}else {
			System.out.println( "Step FAIL: Keyword : " + keyword +" search returned " +
					countResult +" results in " + location);
		}
		
		driver.close();	
		
	}

}