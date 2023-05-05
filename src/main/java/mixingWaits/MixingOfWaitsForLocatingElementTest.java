package mixingWaits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class MixingOfWaitsForLocatingElementTest {
    WebDriver driver;
    LocalDateTime start;
    LocalDateTime prev;
    LocalDateTime end;

    @Test
    public void mixingWaitsForLocatingElement()
    {
        driver = new ChromeDriver();

        // Setting up implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

        // Load URL
        driver.get("https://demoqa.com/");

        // Setting up explicit wait and polling interval
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
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
            prev = now;
            // Implicit wait will be applicable here
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
