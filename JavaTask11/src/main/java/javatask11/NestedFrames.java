package javatask11;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class NestedFrames {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
       
            driver.get("http://the-internet.herokuapp.com/nested_frames");
            driver.manage().window().maximize();            

            // Switch to the top frame
            driver.switchTo().frame("frame-top");
            

            // Verify there are three frames
            List<WebElement> topFrames = driver.findElements(By.tagName("frame"));
            System.out.println("Number of frames: " + topFrames.size());            

            // Switch to the left frame 
            driver.switchTo().frame(topFrames.get(0));
            WebElement leftFrame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            String leftText = leftFrame.getText();
            System.out.println("Left frame text: " + leftText);           

            // Switch back to the parent frame 
            driver.switchTo().parentFrame();

            // Switch to the middle frame
            driver.switchTo().frame(topFrames.get(1));
            WebElement middleBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            String middleText = middleBody.getText();
            System.out.println("Middle frame text: " + middleText);            

            // Switch back to top frame
            driver.switchTo().parentFrame();

            // Switch to the right frame
            driver.switchTo().frame(topFrames.get(2));
            WebElement rightBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            String rightText = rightBody.getText();
            
            // Switch back to top frame
            driver.switchTo().defaultContent();

            // Switch to bottom frame 
            driver.switchTo().frame("frame-bottom");
            WebElement bottomBody = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));
            String bottomText = bottomBody.getText();
            System.out.println("Bottom frame text: " + bottomText);
            

            // Switch back to main content
            driver.switchTo().defaultContent();

       
            driver.quit();
        
    }
}
