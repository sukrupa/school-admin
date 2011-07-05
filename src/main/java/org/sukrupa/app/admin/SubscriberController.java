package org.sukrupa.app.admin;

import com.sun.org.apache.bcel.internal.generic.GETFIELD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;


@Controller
@RequestMapping("/admin/subscribers")
public class SubscriberController {


    private SubscriberRepository subscriberRepository;
    @Autowired
    public SubscriberController(SubscriberRepository subscriberRepository) {
              this.subscriberRepository=subscriberRepository;
    }


    @RequestMapping(value = "viewSubscribers", method = RequestMethod.GET)
    public String listsubscribers() {
        return "admin/subscribers/viewSubscribers";

    }
}
