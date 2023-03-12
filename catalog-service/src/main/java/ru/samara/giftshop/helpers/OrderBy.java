package ru.samara.giftshop.helpers;

public enum OrderBy {
    ID("id"),
    NAME("name"),
    PRICE("price"),
    CATEGORY_NAME("categoryName"),
    CREATION("creation"),
    VIEWS("views");

    String column;

    OrderBy(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
