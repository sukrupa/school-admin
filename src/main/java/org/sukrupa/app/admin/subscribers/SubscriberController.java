package org.sukrupa.app.admin.subscribers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class SubscriberController {


    private SubscriberRepository subscriberRepository;
    
    @Autowired
    public SubscriberController(SubscriberRepository subscriberRepository) {
              this.subscriberRepository=subscriberRepository;
    }

    @RequestMapping("subscribers")
       public String shouldDisplaySubscribers(Map<String,Subscriber> model){
            return "admin/subscribers/viewsubscribers";
        }



}
