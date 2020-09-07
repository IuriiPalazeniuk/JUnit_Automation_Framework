package api.ApiOjects;

import lombok.Getter;

@Getter
public class Content {

    java.util.List<Content.Items> items;

    @Getter
    class Items {
        String id;
        Item item;
    }

    @Getter
    class Item {
        String name;
    }
}
