import api.ApiOjects.DeletedShoppingListResponse;
import api.ApiOjects.ShoppingListResponse;
import api.ScenarioContext;
import apiAssertions.ShoppingListApi;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static api.ScenarioContext.ContextEnum.HTTP_RESPONSE_STATUS_CODE;
import static helpers.PropertiesFileReader.getSystemPropertyByName;
import static org.junit.Assert.assertEquals;

public class APITestSuite {

    private ShoppingListApi shoppingListApi = new ShoppingListApi();
    private final String DELETED_SHOPPING_LIST_MESSAGE = getSystemPropertyByName("empty_shoppingList");

    @BeforeEach
    public void beforeTestsMethod(){
        shoppingListApi.postNewShoppingList();
    }

    @Test
    @DisplayName("check creating Shopping list functionality")
    public void checkCreatingShoppingListFunctionality(){
        shoppingListApi.getShoppingListById();
        ShoppingListResponse response = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        String post_id = ScenarioContext.getContext(ScenarioContext.ContextEnum.POSTED_ID);
        String get_id = response.getList().getId();
        Assert.assertEquals("Get request returned wrong id number", post_id, get_id);
        ShoppingListResponse shoppingList = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        Assert.assertEquals("Content is not null", shoppingList.getContent().getItems(), null);
    }

    @Test
    @DisplayName("check deleting Shopping list functionality")
    public void checkDeletingShoppingListFunctionality(){
        shoppingListApi.deleteShoppingListById();
        shoppingListApi.getShoppingListByIdAfterDeletingShoppingList();
        int code = ScenarioContext.getContext(HTTP_RESPONSE_STATUS_CODE);
        assertEquals(String.format("Wrong status code! Current code is: %s", code), code, 400);
        DeletedShoppingListResponse messageR = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        String actualMessage = messageR.getCode();
        Assert.assertEquals("Response message code is wrong!", actualMessage, DELETED_SHOPPING_LIST_MESSAGE);
    }
}
