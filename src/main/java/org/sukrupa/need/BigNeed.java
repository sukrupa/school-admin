package org.sukrupa.need;

import com.google.common.collect.Sets;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.Years;
import org.joda.time.format.DateTimeFormat;
import org.sukrupa.event.Event;
import org.sukrupa.platform.RequiredByFramework;

import javax.persistence.*;
import java.util.*;

@Entity
public class BigNeed {



    @Id
    @GeneratedValue
    private int priority;

    @Column(name = "PRIORITY")
    private int item_priority;

     @Column(name = "ITEM")
    private String item_name;

     @Column(name = "COST")
    private String item_cost;



    @RequiredByFramework
    public BigNeed() {
    }

    public BigNeed(int item_priority,String item_name,String item_cost) {

        this.item_priority=item_priority;
        this.item_name=item_name;
        this.item_cost=item_cost;

    }


    private int setItemPriority(int itemPriority) {
        return itemPriority;
    }



    public String getItemName() {
        return item_name;
    }

    public String getItemCost() {
        return item_cost;
    }






}
