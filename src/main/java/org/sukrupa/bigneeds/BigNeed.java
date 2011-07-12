package org.sukrupa.bigneeds;

import org.sukrupa.platform.RequiredByFramework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class BigNeed {

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "COST")
    private double cost;

    @Column(name = "PRIORITY")
    private int priority;

    public BigNeed(String itemName, double cost,int priority) {
        this.itemName = itemName;
        this.cost = cost;
        this.priority=priority;
    }

    @RequiredByFramework
    public BigNeed() {
    }

    public BigNeed(int id, String itemName, int cost, int priority) {
        this(itemName, cost, priority);
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCost() {
        return cost;
    }

    public long getId() {
        return id;
    }

    public int getPriority(){
        return priority;
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

    public void setCost(double cost){
        System.out.println(cost);
        this.cost = cost;
    }


    public void setPriority(int priority){
        this.priority=priority;
    }


}
