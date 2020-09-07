import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.openqa.selenium.WebElement;

import java.util.List;

import static driverFactory.LocalDriverFactory.closeDriver;
import static driverFactory.LocalDriverFactory.getDriver;
import static helpers.PropertiesFileReader.getSystemPropertyByName;
import static org.junit.Assert.assertTrue;

public class UITestSuite {

    private final String URL = getSystemPropertyByName("url");
    private final String USER_NAME = getSystemPropertyByName("username");
    private final String PASSWORD = getSystemPropertyByName("password");
    private int countOfProducts = Integer.parseInt(getSystemPropertyByName("countOfProducts"));

    private pages.SignUpPage signUpPage = new pages.SignUpPage(getDriver());
    private pages.MainPage mainPage = new pages.MainPage(getDriver());
    private pages.ShoppingListPage shoppingListPage = new pages.ShoppingListPage(getDriver());

    @BeforeEach
    public void before() {
        getDriver().get(URL);
        signUpPage.fillInCredentials(USER_NAME, PASSWORD);
        mainPage.navigateToSoppingTab();
    }

    @AfterEach
    public void tearDown() {
        closeDriver();
    }

    @ParameterizedTest
    @ValueSource(strings = {"Bread", "Eggs", "Milk", "Onions", "Potatoes"})
    @DisplayName("Creating Shopping List")
    public void shoppingListCreatingTest(String product) {
        shoppingListPage.createShoppingListWithDefaultName();
        shoppingListPage.addProductsToCreatedShoppingList(countOfProducts);
        List<WebElement> expectedProduct = shoppingListPage.checkAddedProductToShoppingList(product);
        assertTrue(String.format("There is no product %s", product), !expectedProduct.isEmpty());
    }

    @Test
    @DisplayName("Deleting Shopping List")
    public void deleteShoppingListTest() {
        shoppingListPage.openShoppingListMenu();
        shoppingListPage.deleteShoppingList();
        assertTrue("Shopping list wasn't deleted", shoppingListPage.getDefaultShoppingList().size() == 1);
    }


}
