package mixingWaits;

import aquality.selenium.browser.AqualityServices;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.time.LocalDateTime;

public class MixingOfWaitsForLocatingElementWithAqualityTest {
    LocalDateTime start;
    LocalDateTime prev;
    LocalDateTime end;

    @Test
    public void mixingWaitsForLocatingElement() {
        AqualityServices.getBrowser().setImplicitWaitTimeout(Duration.ofSeconds(2));

        WebDriverWait wait = new WebDriverWait(AqualityServices.getBrowser().getDriver(), Duration.ofSeconds(20));
        wait.pollingEvery(Duration.ofSeconds(6));

        start = LocalDateTime.now();
        System.out.println("Wait starts at: " + start);

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

    @BeforeMethod
    public void setUp() {
        AqualityServices.getBrowser().goTo("https://demoqa.com/");
    }

    @AfterMethod
    public void tearDown() {
        end = LocalDateTime.now();
        System.out.println("Wait ends at: " + end);
        System.out.println("Duration: " + DateTimeUtils.calculateDuration(start, end));
        if (AqualityServices.isBrowserStarted()) {
            AqualityServices.getBrowser().quit();
        }
    }
}
