package api;

import java.util.HashMap;
import java.util.Map;

public class ScenarioContext {

    private static Map<ContextEnum, Object> context = new HashMap<>();

    public static void setContext(ContextEnum key, Object value) {
        context.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getContext(ContextEnum key) {
        return (T) context.getOrDefault(key, null);
    }

    public enum ContextEnum {
        HTTP_REQUEST,
        HTTP_RESPONSE,
        HTTP_RESPONSE_STATUS_CODE,
        ADDED_PRODUCTS_TO_LIST,
        POSTED_ID
    }
}
