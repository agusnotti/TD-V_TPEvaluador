package DemoblazeTest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoblazeLogin {
	
	private final WebDriver driver;
	
	public DemoblazeLogin(WebDriver driver) {
		this.driver = driver;
	}
	
	public void login(String user, String pass) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		WebElement loginBtn = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login2")));
		loginBtn.click();
		
		WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(4));
		WebElement username = (WebElement) wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
		username.clear();
		username.sendKeys(user);
		
			
		WebElement password = (WebElement) wait2.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));
		password.clear();
		password.sendKeys(pass);
		
		WebElement button = (WebElement) wait2.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[2]")));
		button.click();
	}

}
