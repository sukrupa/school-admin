package org.sukrupa.app.admin.subscribers;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by IntelliJ IDEA.
 * User: Thoughtworks
 * Date: 7/5/11
 * Time: 4:14 PM
 * To change this template use File | Settings | File Templates.
 */

@Entity
public class Subscriber {
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "SUBSCRIBER_NAME")
    private String subscriberName;

    @Column(name = "Email")
    private String subscriberEmail;


    public Subscriber(String subscribeName, String subscriberEmail){
        this.subscriberName=subscribeName;
        this.subscriberEmail=subscriberEmail;
    }

    public String getSubscriberName(){
        return this.subscriberName;
    }

    public String getSubscriberEmail(){
        return this.subscriberEmail;
    }
}
