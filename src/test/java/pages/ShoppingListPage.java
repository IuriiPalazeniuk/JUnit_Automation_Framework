package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static api.ScenarioContext.ContextEnum.ADDED_PRODUCTS_TO_LIST;
import static api.ScenarioContext.getContext;
import static api.ScenarioContext.setContext;
import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

@Getter
public class ShoppingListPage extends BasePage {

    @FindBy(xpath = "//input[@data-testid='desktop-add-item-autocomplete']")
    private WebElement addItemDrpoDown;

    @FindBy(xpath = "//span[@class='sc-ptSuy fnmilh']")
    private List<WebElement> prodcutsList;

    @FindBy(xpath = "//span[@data-testid='shopping-list-item-name']")
    private List<WebElement> addedProductsList;

    @FindBy(xpath = "//div[@data-testid='shopping-list-name']/h2")
    private WebElement shoppingListHeader;

    @FindBy(xpath = "//div[@class='sc-AxjAm bcMPWx']")
    private WebElement createShoppingListButton;

    @FindBy(xpath = "//button[@data-testid='create-new-shopping-list-create-button']")
    private WebElement createShoppingListPopUpButton;

    @FindBy(xpath = "//div[@class='sc-pTSbw gOmWnw']")
    private WebElement productsList;

    @FindBy(xpath = "//div[@class='sc-1if7rst hNjdmK']//button")
    private List<WebElement> shoppingListMenu;

    @FindBy(xpath = "//h1[@class='sc-fzpdyU sc-psEpA sc-qYRsW htjyTI']")
    private WebElement emptyShoppingList;

    @FindBy(xpath = "//button[@data-testid='shopping-list-delete-menu-button']")
    private WebElement deleteList;

    @FindBy(xpath = "//div[@data-testid='shopping-lists-list-name']")
    private List<WebElement> defaultShoppingList;

    @FindBy(xpath = "//button[@data-testid='confirm-delete-button']")
    private WebElement confirmDeletePopUp;

    private final String EMPTY_SHOPPING_LIST = "empty_shoppingList";


    public ShoppingListPage(WebDriver driver) {
        super(driver);
    }

    public List<String> addProductsToList(int countOfProducts) {
        List<String> products = new ArrayList<>();
        wait = new WebDriverWait(driver, 5);
        wait.until(elementToBeClickable(productsList));

        for (int i = 0; i < countOfProducts; i++) {
            products.add(prodcutsList.get(i).getText());
            prodcutsList.get(i).click();
        }
        shoppingListHeader.click();
        return products;
    }

    public List<String> getAddedProductsFromList() {
        List<String> list = new ArrayList<>();
        for (WebElement element : addedProductsList) {
            String text = element.getText();
            list.add(text);
        }
        return list;
    }

    public void createShoppingListWithDefaultName() {
        if (defaultShoppingList.size() < 2) {
            clickOnElement(createShoppingListButton);
            clickOnElement(createShoppingListPopUpButton);
        }

    }

    public void addProductsToCreatedShoppingList(int countOfProducts) {
        clickOnElement(defaultShoppingList.get(0));
        if (addedProductsList.isEmpty()) {
            clickOnElement(addItemDrpoDown);
            List<String> addedProductsList = addProductsToList(countOfProducts);
            setContext(ADDED_PRODUCTS_TO_LIST, addedProductsList);
        }
    }

    public void openShoppingListMenu() {
        clickOnElement(shoppingListMenu.get(0));
    }

    public void deleteShoppingList() {
        clickOnElement(deleteList);
        clickOnElement(confirmDeletePopUp);
    }

    public List<WebElement> checkAddedProductToShoppingList(String productName) {
        return driver.findElements(org.openqa.selenium.By.xpath("//span[contains(@data-testid, 'shopping-list-item-name') and text() = '" +
                productName + "']"));
    }

    public void verifingAddedProductsInShoppingList() {
        List<String> addedProducts = getContext(ADDED_PRODUCTS_TO_LIST);
        List<String> productsFromList = getAddedProductsFromList();
        Collections.sort(addedProducts);
        Collections.sort(productsFromList);
        assertEquals(String.format("Different products are added %s", productsFromList), addedProducts, productsFromList);
    }
}
