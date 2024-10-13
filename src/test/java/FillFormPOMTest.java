import Base.BaseTest;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pages.ContactUsPage;
import pages.ContactUsVerificationPage;
import pages.HomePage;

import static org.assertj.core.api.Assertions.assertThat;

public class FillFormPOMTest extends BaseTest {


    private String drop_option ="Customer service";
    private String mail = "email@email.com";
    private String number = "12345";
    private String path = "/home/ANT.AMAZON.COM/lwcislol/plik.txt";
    private String mesage = "Some message";

    @Test
    void shouldSendFormWithPOM() {

        HomePage HomePage = new HomePage(driver);
        ContactUsPage ContactUsPage = new ContactUsPage(driver);
        ContactUsVerificationPage ContactUsVerificationPage = new ContactUsVerificationPage(driver);

        HomePage.contactUsClick();
        ContactUsPage.select_object_from_subject_heading(drop_option);
        ContactUsPage.send_email(mail);
        ContactUsPage.send_order_reference(number);
        ContactUsPage.attach_input_file(path);
        ContactUsPage.prepare_message(mesage);
        ContactUsPage.click_sen_button();

        String actual_msg = ContactUsVerificationPage.checkAlertSuccess();

        String expected_msg = "Your message has been successfully sent to our team.";
        assertThat(expected_msg).withFailMessage("Niezgodnosc asercji wiadomosci potwierdzajacej wyslanie zgloszenia").isEqualTo(actual_msg);


    }

}
