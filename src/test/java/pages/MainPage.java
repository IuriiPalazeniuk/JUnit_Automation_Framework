package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainPage extends BasePage {

    @FindBy(xpath = "//div[@data-testid='shopping-list-nav-link']")
    private WebElement shoppingListTab;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToSoppingTab(){
        clickOnElement(shoppingListTab);
    }

}
