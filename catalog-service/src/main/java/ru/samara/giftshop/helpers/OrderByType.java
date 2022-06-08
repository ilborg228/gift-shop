package ru.samara.giftshop.helpers;

public enum OrderByType {
    ASC("ASC"), DESC("DESC");

    private String direction;

    private OrderByType(String order) {
        this.direction = order;
    }

    public String getDirection() {
        return direction;
    }
}
