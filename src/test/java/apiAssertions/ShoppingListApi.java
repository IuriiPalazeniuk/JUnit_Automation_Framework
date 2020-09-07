package apiAssertions;

import api.ApiOjects.DeletedShoppingListResponse;
import api.ApiOjects.ShoppingList;
import api.ApiOjects.ShoppingListResponse;
import api.RestApiShoppingList;
import api.ScenarioContext;

public class ShoppingListApi {

    private RestApiShoppingList rest = new RestApiShoppingList();
    private final String LIST_NAME = "My new List";


    public void postNewShoppingList() {
        ShoppingList list = new ShoppingList(LIST_NAME, false);
        rest.postNewShoppingListRequest(list);
        ShoppingListResponse response = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        ScenarioContext.setContext(ScenarioContext.ContextEnum.POSTED_ID, response.getList().getId());
    }

    public void getShoppingListById() {
        String posted_id = ScenarioContext.getContext(ScenarioContext.ContextEnum.POSTED_ID);
        rest.getCreatedShoppingListById(posted_id, ShoppingListResponse.class);
    }

    public void deleteShoppingListById() {
        ShoppingListResponse response = ScenarioContext.getContext(ScenarioContext.ContextEnum.HTTP_RESPONSE);
        rest.deleteCreatedShoppingListById(response.getList().getId());
    }

    public void getShoppingListByIdAfterDeletingShoppingList() {
        String posted_id = ScenarioContext.getContext(ScenarioContext.ContextEnum.POSTED_ID);
        rest.getCreatedShoppingListById(posted_id, DeletedShoppingListResponse.class);
    }


}
