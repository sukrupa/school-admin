package org.sukrupa.bigneeds;

import org.eclipse.jdt.core.dom.ThisExpression;
import org.sukrupa.platform.RequiredByFramework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BigNeed {

    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "COST")
    private int cost;

    public BigNeed(String itemName, int cost) {
        this.itemName = itemName;
        this.cost = cost;
    }

    @RequiredByFramework
    public BigNeed() {
    }

    public BigNeed(int id, String itemName, int cost) {
        this(itemName, cost);
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public Integer getCost() {
        return cost;
    }

    public long getId() {
        return id;
    }

    public boolean equals(BigNeed object){
        return itemName.equals(object.getItemName()) && (cost == object.getCost());
    }

    public int hashCode(){
        return itemName.hashCode();
    }

    public void setItemName(String itemName){
        this.itemName = itemName;
    }

    public void setCost(int cost){
        System.out.println(cost);
        this.cost = cost;
    }


}
