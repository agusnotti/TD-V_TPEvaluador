package DemoblazeTest;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class DemoblazeTest {
	private WebDriver driver;
	
	@BeforeClass
	public void launchBrowser() {
	
		System.setProperty("webdriver.gecko.driver", "./src/test/resources/chromeDriver/chromedriver.exe");
		this.driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	@Test (priority=1)
	public void loadPage() {
		driver.navigate().to("https://www.demoblaze.com/");
		//System.out.println(driver.getTitle());
		assertEquals(driver.getTitle(), "STORE");
	}
	
	
	@Test (priority=3)
	public void validLogin() {
		DemoblazeLogin logInSamsungPage = PageFactory.initElements(driver, DemoblazeLogin.class);
		logInSamsungPage.login("userTest@gmail.com", "123456");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		WebElement element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
		boolean welcomeUser = element.isDisplayed(); 
		
		assertTrue(welcomeUser);
	}
	
	@Test (priority=2)
	public void invalidLogin() {
		DemoblazeLogin logInPage = PageFactory.initElements(driver, DemoblazeLogin.class);
		logInPage.login("userTest@gmail.com", "1234");
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		
		wait.until(ExpectedConditions.alertIsPresent());
		String textoEncontrado = driver.switchTo().alert().getText();
		//System.out.println(textoEncontrado);
		driver.switchTo().alert().accept();
		
		WebElement closeBtn = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"logInModal\"]/div/div/div[3]/button[1]")));
		closeBtn.click();		
		
		String textoEsperado = "Wrong password.";
		assertEquals(textoEsperado, textoEncontrado);
	}		
	
	@Test (priority=4)
	public void searchProduct() {
		String productoBuscado = "Samsung galaxy s7";
		DemoblazeSearchProduct searchProduct = PageFactory.initElements(driver, DemoblazeSearchProduct.class);
		searchProduct.search(productoBuscado);
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(8));
		WebElement element = (WebElement) wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("name")));
		String productoEncontrado = element.getText(); 
		System.out.println(element.getText());
		
		assertEquals(productoBuscado, productoEncontrado);
	}
		
	@AfterClass
	public void tearDown() {
		if(driver!=null) {
			driver.close();
			System.out.println("Termino satisfactoriamente");
		}
	}

}
