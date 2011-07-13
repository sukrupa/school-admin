package org.sukrupa.smallNeeds;


import org.sukrupa.platform.RequiredByFramework;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class SmallNeed {
    @Id
    @GeneratedValue
    private int id;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "COST")
    private double cost;

    @Column(name = "COMMENT")
    private String comments;

    @Column(name = "PRIORITY")
    private int priority;


    @RequiredByFramework
    public SmallNeed() {
    }

    public SmallNeed(String itemName, double cost, String comments, int priority) {
        this.itemName = itemName;
        this.cost = cost;
        this.comments = comments;
        this.priority = priority;
    }

    public String getItemName() {
        return itemName;
    }

    public double getCost() {
        return cost;
    }

    public String getComment() {
        return comments;
    }

    public int getPriority() {
        return priority;
    }

    public int getId() {
        return id;
    }

    public void setItemName(String itemName) {
        this.itemName=itemName;
    }

    public void setCost(double cost) {
        this.cost=cost;
    }

    public void setPriority(int priority) {
        this.priority=priority;
    }

    public void setComment(String comment) {
        this.comments=comment;
    }
}
