package mixingWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class ExplicitWaitWithDefaultPollingIntervalTest {
    WebDriver driver;
    LocalDateTime start;
    LocalDateTime prev;
    LocalDateTime end;

    @Test
    public void mixingWaitsForLocatingElement()
    {
        // Initializing browser
        driver = new ChromeDriver();

        // Loading URL
        driver.get("https://demoqa.com/");

        // Setting up explicit wait with default polling interval
        // 500ms
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Custom polling interval
        wait.pollingEvery(Duration.ofSeconds(2));

        // Timer starts
        start = LocalDateTime.now();
        System.out.println("Wait starts at: " + start);
        // Locating wrong element
        prev = start;
        wait.until(driver -> {
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now + ", interval: " +
                    DateTimeUtils.calculateDuration(prev, now));
            prev = now;
            // Locating element again
            return driver.findElement(By.id("SomeWrongId"));
        });
    }

    @AfterMethod
    public void printExitTime()
    {
        end = LocalDateTime.now();
        System.out.println("Wait ends at: " + end);
        System.out.println("Duration: " + DateTimeUtils.calculateDuration(start, end));
        driver.quit();
    }
}
