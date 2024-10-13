import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormBasicTest {

    private static WebDriver driver;
    private final String APP_URL = "http://www.automationpractice.pl/index.php";
    private Logger log = LoggerFactory.getLogger(FillFormBasicTest.class);

    @Test
    void shouldFillFormWithSuccess() {

        //kod selenium
        //1. open url http://www.automationpractice.pl/index.php
        //chrome
        //2. navigate to http://www.automationpractice.pl/index.php?controller=contact
        //3. subject
        //4. email
        //5. order reference
        //6. attach file
        //7. message
        //8. submit
        //9. assert


        //1. open url http://www.automationpractice.pl/index.php
        driver = getDriver();
        driver.get(APP_URL);
        log.info("URL opened");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        //driver.manage().timeouts(). explicityWait ?? fluentWait ??

        //2. navigate to http://www.automationpractice.pl/index.php?controller=contact
        // konsola devtools
        // $x("xpath")
        // $$("css")
        // znalezione selektory:
        // $x("//div[@id= 'contact-link']/a")
        // $$("#contact-link a")

        WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
        contactUs_link.click();
        log.info("contact page opened");
        //System.out.println("ok"); <- zeby zapauzowac z break pointem

        //3. Fill subject - droplist


        // WebElement subjectHandling_select = driver.findElement(By.cssSelector("#id_contact"));
        // WebElement subjectHandling_select = driver.findElement(By.xpath("#id_contact")); <- z prawdziwym xpathem ofc
        WebElement subjectHandling_select = driver.findElement(By.id("id_contact"));
        Select select = new Select(subjectHandling_select);
        //select.selectByIndex(1);
        select.selectByVisibleText("Customer service");
        // System.out.println("ok");
        log.info("Subject selected");

        //4. Fill email - text

        WebElement email_input = driver.findElement(By.cssSelector("#email"));
        email_input.clear();
        email_input.sendKeys("jakis.mail@mail.com");
        log.info("email submitted");

        //5. order reference - text

        WebElement id_order_input = driver.findElement(By.cssSelector("#id_order"));
        id_order_input.clear();
        id_order_input.sendKeys("1234");
        log.info("order submitted");

        //6. attach file

        WebElement attachFileInput = driver.findElement(By.cssSelector("#fileUpload"));
        attachFileInput.clear();
        attachFileInput.sendKeys("/home/ANT.AMAZON.COM/lwcislol/plik.txt");
        log.info("file send");

        //7. message

        WebElement message = driver.findElement(By.xpath("//*[@id='message']"));
        message.clear();
        message.sendKeys("Jakies uzasadnienie");
        log.info("message submitted");

        //8. submit

        WebElement send = driver.findElement(By.id("submitMessage"));
        send.click();
        log.info("button clicked");

        //9. assert

        String expected_msg = "Your message has been successfully sent to our team.";
        log.info("Expected message = " + expected_msg);
        WebElement success_msg = driver.findElement(By.cssSelector(".alert-success"));
        String actual_msg = success_msg.getText();
        log.info("Actual message = " + actual_msg);

        //assertThat(expected_msg).contains(actual_msg); < -  soft assert
        assertThat(expected_msg).withFailMessage("Niezgodnosc asercji wiadomosci potwierdzajacej wyslanie zgloszenia").isEqualTo(actual_msg);
        log.info("Assertion passed successfully");

        //10. zamykanie

        driver.quit();
        log.info("Driver closed");

        //driver.close();

    }

    private WebDriver getDriver() {

        String browserName = "chrome";

        switch (browserName) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                WebDriverManager.chromedriver().setup();
                chromeOptions.addArguments("--start-maximized");
                chromeOptions.addArguments("--remote-allow-origins=*");
                chromeOptions.addArguments("--disable-search-engine-choice-screen");
                chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                log.info("Driver initialized properly");

            }
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                //firefoxOptions.addArguments("--start-maximized");
                //firefoxOptions.addArguments("--remote-allow-origins=*");
                //firefoxOptions.addArguments("--disable-search-engine-choice-screen");

            }
            case "edge" -> {
                WebDriverManager.edgedriver().setup();
                //edgeOptions.addArguments("--start-maximized");
                //edgeOptions.addArguments("--remote-allow-origins=*");
                //edgeOptions.addArguments("--disable-search-engine-choice-screen");

            }
            default -> throw new UnsupportedOperationException("Unsupported Browser Selected");


        }

        return driver;

    }

}




