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
    private int ID;

    @Column(name = "ITEM_NAME")
    private String itemName;

    @Column(name = "COST")
    private long cost;

    @Column(name = "COMMENT")
    private String comments;


    @RequiredByFramework
    public SmallNeed() {
    }

    public SmallNeed(String itemName, long cost, String comments) {
        this.itemName=itemName;
        this.cost=cost;
        this.comments=comments;
    }

    public String getItemName() {
             return itemName;
    }

    public long getCost() {
        return cost;
    }

    public String getComment() {
        return comments;
    }
}
