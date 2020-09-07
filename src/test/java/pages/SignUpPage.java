package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class SignUpPage extends BasePage {

    @FindBy(id = "_input-1")
    private WebElement userNameInputField;
    @FindBy(id = "_input-2")
    private WebElement passwordInputField;
    @FindBy(xpath = "//button[@data-testid='auth-continue-button']")
    private WebElement continueButton;
    @FindBy(xpath = "//button[@data-testid='auth-login-button']")
    private WebElement logInButton;
    @FindBy(xpath = "//button[@data-testid='community-onboarding-continue']")
    private WebElement letsCoockingButton;


    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void fillInCredentials(String username, String password) {
        userNameInputField.sendKeys(username);
        clickOnElement(continueButton);
        passwordInputField.sendKeys(password);
        clickOnElement(logInButton);
        clickOnElement(letsCoockingButton);
    }

}
