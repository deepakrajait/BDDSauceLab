package StepFile;

import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.AssertJUnit;
import org.testng.AssertJUnit;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class AjaxLoaderSteps {
	WebDriver driver = null;   
	
	@Before
	public void setUp() {
		driver = new ChromeDriver();
	}
	
	@Given("^URL is launched$")
	public void url_is_launched() throws Throwable {
		driver.get("http://webdriveruniversity.com/index.html");
		driver.manage().window().maximize();
	}

	@Then("^Verify AJAX LOADER is present$")
	public void verify_AJAX_LOADER_is_present() throws Throwable {
		WebElement eleAjaxLoader = driver.findElement(By.xpath("//h1[text()='AJAX LOADER']"));

		// Scroll to Element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", eleAjaxLoader);

		// Verify
		boolean ajaxLoaderPresent = false;
		ajaxLoaderPresent = eleAjaxLoader.isDisplayed();
		AssertJUnit.assertTrue(ajaxLoaderPresent);
	}

	@When("^Click on AJAX LOADER link$")
	public void click_on_AJAX_LOADER_link() throws Throwable {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.findElement(By.xpath("//a[@id='ajax-loader']")).click();
	}

	@When("^Click on Click Me$")
	public void click_on_Click_Me() throws Throwable {
		// Switch to Other tab
		ArrayList<String> newTb = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTb.get(1));

		// explicit wait - to wait for CLICK ME button
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement btnClickMe = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@id='button1']")));
		
		// Click CLICK ME
		btnClickMe.click();
	}

	@Then("^verify the pop-up is present$")
	public void verify_the_pop_up_is_present() throws Throwable {
		// Verify Pop-up Title
		Thread.sleep(2000);
		WebElement elePopupTitle = driver.findElement(By.xpath("//h4[text()='Well Done For Waiting....!!!']"));
		// Verify
		boolean blnPopUpTitleDisplay = false;
		blnPopUpTitleDisplay = elePopupTitle.isDisplayed();
		AssertJUnit.assertTrue(blnPopUpTitleDisplay);
		Thread.sleep(2000);
	}

	@Then("^Close the browser$")
	public void close_the_browser() throws Throwable {
		driver.close();
		driver.quit();
	}
}
