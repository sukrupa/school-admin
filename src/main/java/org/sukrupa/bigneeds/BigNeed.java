package org.sukrupa.bigneeds;

public class BigNeed {

    private String itemName;
    private int cost;

    public BigNeed(String itemName, int cost) {

        this.itemName = itemName;
        this.cost = cost;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getCost() {
        return cost;
    }

}
