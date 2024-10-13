package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ContactUsPage {

    private Logger log = LoggerFactory.getLogger(ContactUsPage.class);

    @FindBy(id = "id_contact")
    WebElement subjectHeading_select;

    @FindBy(css = "#email")
    WebElement email_input;

    @FindBy(css = "#id_order")
    WebElement order_reference;

    @FindBy(css = "#fileUpload")
    WebElement attach_input_file;

    @FindBy(xpath = "//*[@id='message']")
    WebElement message;

    @FindBy(id = "submitMessage")
    WebElement send_button;

    public ContactUsPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public ContactUsPage select_object_from_subject_heading(String customerService) {
        Select select = new Select(subjectHeading_select); //selenium ma klasę select
        select.selectByIndex(1); //Customer service
        select.selectByVisibleText(customerService); //Customer service
        log.info("Subject Heandling selected  ");
        return this;
    }

    public ContactUsPage send_email(String mail) {
        email_input.clear();
        email_input.sendKeys(mail); //
        log.info("email submitted properly:" + mail);
        return this;
    }

    public ContactUsPage send_order_reference(String number) {
        order_reference.clear();
        order_reference.sendKeys(number); //
        log.info("order submitted ");
        return this;
    }

    public ContactUsPage attach_input_file(String string) {
        attach_input_file.clear();
        attach_input_file.sendKeys(string); //
        log.info("file submitted");
        return this;
    }

    public ContactUsPage prepare_message(String string) {
        message.clear();
        message.sendKeys(string); //
        log.info("message submitted");
        return this;
    }

    public ContactUsPage click_sen_button() {
        send_button.click();
        log.info("button clicked");
        return this;
    }
}