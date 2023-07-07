package seleniumproject;

import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;

public class FirstcryProject {

	public static void main(String[] args) {
		//navigate to web page    
		System.setProperty("webdriver.chrome.driver", "C:\\webdriver\\latest\\chromedriver\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://www.firstcry.com/");
		//driver.navigate().to("https://www.firstcry.com/");
		
		//getting current url
		String url=driver.getCurrentUrl();
		System.out.println("Current Url of WebPage: "+url);
		
		//getting current title
		String title=driver.getTitle();
	    System.out.println("Current Url of Title: "+title);
	    
		//Perform Mouse Hover 
		Actions actions=new Actions(driver);
		WebElement menuOption=driver.findElement(By.xpath("/html/body/div[1]/div[8]/div/div/div[1]/ul/li[2]"));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		actions.moveToElement(menuOption).perform();
		System.out.println("Done Mouse hover on 'Boy Fashion' from Menu");
		WebElement submenuOption=driver.findElement(By.xpath("/html/body/div[1]/div[8]/div/div/div[2]/div[3]/div/div/ul[1]/li[9]/a"));
		actions.moveToElement(submenuOption);
		System.out.println("Done Mouse hover on 'Party wear' from Menu");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
        submenuOption.click();
		
    	
		//Alert Handle
		   // driver.switchTo().window(ParentWindow);
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
			try {
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
				driver.findElement(By.xpath("//*[@id=\"geoLocation\"]/span/div[1]/span")).click();
					//WebDriverWait wait=new WebDriverWait(driver,10);
					Wait<WebDriver> wait=new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(10)).pollingEvery(Duration.ofSeconds(10));
					wait.until(ExpectedConditions.alertIsPresent());
					Alert simpleAlert=driver.switchTo().alert();	
					simpleAlert.dismiss();
					System.out.println("Unexcepected alert accepted");
			}	
					catch(Exception e)
			{
						System.out.println("Unexcepected alert not accepted");
						driver.findElement(By.xpath("//*[@id=\"NonLoginLocation\"]/div/div[1]/span[2]")).click();
		     }
			
			
		//locating web Element and sending text
	    driver.findElement(By.id("search_box")).click();
		driver.findElement(By.id("search_box")).sendKeys("toy for kids");
		driver.findElement(By.className("search-button")).click();
		driver.findElement(By.xpath("//*[@id=\"maindiv\"]/div[1]/div/div[1]/div[2]/a")).click();
		driver.navigate().back();
		
		//Window Handles
				Set<String> allwindowhandles=driver.getWindowHandles();
				System.out.println("Count of open Windows:" +allwindowhandles.size());
			    driver.findElement(By.xpath("//*[@id=\"maindiv\"]/div[1]/div/div[1]/div[1]/a/img")).click();   //(//a[text()='Party Wear'])[1]
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
	            Set<String> newallwindowhandles=driver.getWindowHandles();
				System.out.println("New count of open Windows:" +newallwindowhandles.size());
				String ParentHandler=driver.getWindowHandle();
				System.out.println("Parent window:" +ParentHandler);
				                                                              
				Iterator<String> iterator=newallwindowhandles.iterator();   
				String ParentWindow=iterator.next();            
				String ChildWindow=iterator.next();    
				System.out.println("Parent window: " +ParentWindow);
				System.out.println("Child window: " +ChildWindow);
				driver.switchTo().window(ChildWindow);
				String text=driver.getTitle();
				System.out.println("Child Window Title:"+ text);
				driver.findElement(By.xpath("//*[@id=\"p_breadcrumb\"]/div[2]/div/div[2]/div[6]/div/div[2]/div[2]/div[1]/div/span[1]/span")).click();
				driver.findElement(By.className("go-icon")).click();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(40));
				driver.findElement(By.xpath("//*[@id=\"showcart\"]/div[2]/div[2]/div[4]/label")).click();
				
				//Negative testing to login
				driver.findElement(By.id("lemail")).sendKeys("poonamairen91@gmail.com");
				driver.findElement(By.xpath("//*[@id=\"login\"]/div[3]")).click();
				driver.findElement(By.id("notp0")).sendKeys("123456");
				driver.findElement(By.id("verfiedbtn")).click();
				String mesg=driver.findElement(By.xpath("//*[@id=\"verifyotp\"]/div[1]/p[2]")).getText();
                System.out.println("Error message: "+mesg);
                String Emesg="Error: Invalid OTP. Please try again.";
                if(Emesg.equalsIgnoreCase(mesg)) {
                	System.out.println("User should not able to login and geeting proper message");
                }
                else{
                	System.out.println("something went wrong please check");
                }
                driver.close();
				
			
		
	
			
		//close all the WebPage
		driver.quit();
	}

}
