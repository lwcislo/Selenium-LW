import Base.BaseTestWebTable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;



public class WebTableSeleniumTest extends BaseTestWebTable {

    private Logger log = LoggerFactory.getLogger(Base.BaseTestWebTable.class);

    @Test
    @DisplayName("Basic test of web table")
    void shouldCheckCapitalWebTable(){

        String searchCountry = "Burkina Faso";
        String expectedCapital = "Ouagadougou";

        String retrievedCapital = getCapitalForTheCountry(searchCountry);

        assertThat(retrievedCapital).withFailMessage("Bledna Stolica").isEqualTo(expectedCapital);

    }

    private String getCapitalForTheCountry(String country) {

        List<WebElement> rows= driver.findElements(By.cssSelector("#countries tbody tr"));
        int size = rows.size();
        log.info("Otwieramy Tabele z " + size + " rzedami.");

        int rowCount = 0;

        for (WebElement row: rows) {

            if(row.getText().contains(country)){
                break;
            }
            rowCount ++;
        };

        int rowIndex = rowCount +1;
        log.info("Linia=" + rowIndex);

        WebElement CapitalOfCountry = driver.findElement(By.cssSelector("#countries tbody tr:nth-child("+rowIndex+") td:nth-child(3)"));

        //scrollToWebElement(CapitalOfCountry);

        scrollToWebElement(rows.get(rowIndex+5));
        log.info("Capital of " + country + " is " + CapitalOfCountry.getText());

        return CapitalOfCountry.getText();

    }

    @Test
    @DisplayName("Drugi fajny test z checkboxem tym razem")
    void shouldSelectCheckbox(){

        // Select checkbox for Poland

        List<WebElement> checkboxes = driver.findElements(By.cssSelector(".hasVisited[type='checkbox']"));

        // Idziemy na skroty

        WebElement checkbox = findCheckbox(checkboxes, 139);
        scrollToWebElement(checkboxes.get(145));
        clickCheckbox(checkbox);
        assertThat(checkbox.isSelected()).isTrue();

    }

    private void scrollToWebElement(WebElement element){

        new Actions(driver).scrollToElement(element).perform();

    }
}



