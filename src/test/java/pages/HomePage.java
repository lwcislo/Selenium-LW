package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class HomePage {
    private Logger log = LoggerFactory.getLogger(HomePage.class);

    @FindBy(css="#contact-link a")
    WebElement contactUs_link;
    //WebElement contactUs_link = driver.findElement(By.cssSelector("#contact-link a"));

    public HomePage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }

    public HomePage contactUsClick(){
        contactUs_link.click();
        log.info("contact page opened");
        return this;

    }



}
