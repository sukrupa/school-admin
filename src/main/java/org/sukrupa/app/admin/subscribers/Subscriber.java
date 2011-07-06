package org.sukrupa.app.admin.subscribers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;



@Entity
public class Subscriber {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "SUBSCRIBERNAME")
    private String subscriberName;

    @Column(name = "EMAIL")
    private String subscriberEmail;


    public Subscriber(String subscriberName, String subscriberEmail){
        this.subscriberName=subscriberName;
        this.subscriberEmail=subscriberEmail;
    }

    public String getSubscriberName(){
        return this.subscriberName;
    }

    public String getSubscriberEmail(){
        return this.subscriberEmail;
    }
}
