package api;

import api.ApiOjects.DeletedShoppingListResponse;
import api.ApiOjects.ShoppingList;
import api.ApiOjects.ShoppingListResponse;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import static helpers.PropertiesFileReader.getSystemPropertyByName;

@Slf4j
public class RestApiShoppingList extends RestAssuredHelper {
    private final String endPoint = getSystemPropertyByName("endPoint");
    private final String host = getSystemPropertyByName("host");
    private final String token = getSystemPropertyByName("token");
    private ScenarioContext scenarioContext = new ScenarioContext();

    @SneakyThrows
    public void postNewShoppingListRequest(ShoppingList model) {
        Response rawResponse = postRequest(obtainRequestSpec(), endPoint, model);
        saveResponseToContext(scenarioContext, rawResponse, ShoppingListResponse.class);
    }

    @SneakyThrows
    public <T> void getCreatedShoppingListById(String id, Class<T> clazz) {
        Response rawResponse = getRequest(obtainRequestSpec(), endPoint + "/", id);
        saveResponseToContext(scenarioContext, rawResponse, clazz);
    }

    @SneakyThrows
    public void deleteCreatedShoppingListById(String id) {
        Response rawResponse = deleteRequest(obtainRequestSpec(), endPoint + "/", id);
        saveResponseToContext(scenarioContext, rawResponse, DeletedShoppingListResponse.class);
    }

    private io.restassured.specification.RequestSpecification obtainRequestSpec() {
        log.info("Request specification is obtained");
        return setupRequestSpecification(host, token);
    }
}
