package ru.samara.giftshop.helpers;

public enum OrderBy {
    ID("id"),
    CATEGORY_NAME("categoryName");

    String column;

    OrderBy(String column) {
        this.column = column;
    }

    public String getColumn() {
        return column;
    }
}
