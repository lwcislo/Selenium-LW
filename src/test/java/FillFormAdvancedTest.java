import Base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormAdvancedTest extends BaseTest {

    private Logger log = LoggerFactory.getLogger(FillFormAdvancedTest.class);


    @Test
    void fillFormAdvancedScenario() {

        //2. navigate to http://www.automationpractice.pl/index.php?controller=contact

        WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));
        contactUs_link.click();
        log.info("contact page opened");

        //3. Fill subject - droplist

        WebElement subjectHandling_select = driver.findElement(By.id("id_contact"));
        Select select = new Select(subjectHandling_select);
        select.selectByVisibleText("Customer service");
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

        assertThat(expected_msg).withFailMessage("Niezgodnosc asercji wiadomosci potwierdzajacej wyslanie zgloszenia").isEqualTo(actual_msg);
        log.info("Assertion passed successfully");


    }


}

