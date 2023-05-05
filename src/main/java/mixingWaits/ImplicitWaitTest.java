package mixingWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class ImplicitWaitTest {
    WebDriver driver;
    LocalDateTime start;
    LocalDateTime end;

    @Test
    public void mixingWaitsForLocatingElement()
    {
        driver = new ChromeDriver();
        // Setting up implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get("https://demoqa.com/");
        // Timer starts
        start = LocalDateTime.now();
        System.out.println("Wait starts at: " + start);
        // Locating wrong element
        driver.findElement(By.id("SomeWrongId"));
    }

    @AfterMethod
    public void printExitTime()
    {
        end = LocalDateTime.now();
        System.out.println("Wait ends at: " + end);
        System.out.println("Duration: " + DateTimeUtils.calculateDuration(start, end));
        if (driver != null) {
            driver.quit();
        }
    }
}
