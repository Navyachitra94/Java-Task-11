package javatask11;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HandlingWindows {

	public static void main(String[] args) {
		
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		
		// 1. Open browser and navigate to the URL
        driver.get("https://the-internet.herokuapp.com/windows");
        driver.manage().window().maximize();
        
     // 2. Click the "Click Here" button to open a new window
        String mainWindow = driver.getWindowHandle();
        WebElement clickHereButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Click Here")));
        clickHereButton.click();
        
        // 3. Wait for the new window and switch to it
        
        Set<String> windowHandles = driver.getWindowHandles();
        for (String winHandle : windowHandles) {
            if (!winHandle.equals(mainWindow)) {
                driver.switchTo().window(winHandle);
                break;
            }
        }
        
     // 4. Verify the text "New Window" 
        WebElement newWindonwText = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));
        if (newWindonwText.getText().equals("New Window")) {
            System.out.println("PASS: 'New Window' text is present.");
        } else {
            System.out.println("FAIL: 'New Window' text not found.");
        }

        // 5. Close the new window
        driver.close();

        // 6. Switch back to the original window and verify it's active
        driver.switchTo().window(mainWindow);
        if (driver.getCurrentUrl().equals("https://the-internet.herokuapp.com/windows")) {
            System.out.println("Switched back to original window successfully.");
        }
        
        driver.quit();

	}

}
