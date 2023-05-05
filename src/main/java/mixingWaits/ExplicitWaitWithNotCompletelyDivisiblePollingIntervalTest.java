package mixingWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

// Explicit wait = 20 seconds and polling interval = 6 seconds

// Defined Explicit wait timeout was 20 seconds,
// but actually it took 24 seconds.

// Explicit wait with polling interval and
// time taken to evaluate expected condition may
// make actual explicit timeout longer.
public class ExplicitWaitWithNotCompletelyDivisiblePollingIntervalTest {
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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Custom polling interval
        wait.pollingEvery(Duration.ofSeconds(6));

        // Timer starts
        start = LocalDateTime.now();
        System.out.println("Wait starts at: " + start);
        // Locating wrong element
        prev = start;
        wait.until(driver -> {
            LocalDateTime now = LocalDateTime.now();
            System.out.println(now + ", " +
                    "interval: " + DateTimeUtils.calculateDuration(prev, now) + ", " +
                    "since start: " + DateTimeUtils.calculateDuration(start, now));
            // Setting up custom polling interval
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
