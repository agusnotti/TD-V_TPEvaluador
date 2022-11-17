package DemoblazeTest;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DemoblazeSearchProduct {
	
private final WebDriver driver;
	
	public DemoblazeSearchProduct(WebDriver driver) {
		this.driver = driver;
	}
	
	public void selectCategory() {		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement categoriaBtn = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("itemc")));
		categoriaBtn.click();
	}
	
	public void search(String product) {
		selectCategory();
		scrollear(product);
	}
	
	
	public void scrollear(String productoBuscado) {
		//Scroleamos, para ello es necesario ejecutar funcionalidad Javascript
		JavascriptExecutor js = (JavascriptExecutor)driver;
		boolean findElement=false;
		while (findElement == false) {
			js.executeScript("window.scrollBy(0,350)", ""); //scrolleamos
			try {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				WebElement element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(productoBuscado)));
				findElement = true;
				element.click();
			}
			catch(Exception e){
				findElement = false;
			}
		}
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
