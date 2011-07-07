package org.sukrupa.app.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.sukrupa.app.admin.subscribers.Subscriber;
//import org.sukrupa.app.admin.subscribers.SubscriberRepository;


import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/subscribers")
public class SubscriberController {


    //private SubscriberRepository subscriberRepository;
    //@Autowired
    //public SubscriberController(SubscriberRepository subscriberRepository) {
    //          this.subscriberRepository=subscriberRepository;
    //}


    @RequestMapping
    public String listsubscribers(Map<String, Object> model) {
//        List<Subscriber> viewSubscribers = subscriberRepository.findAllSubscribers();
//        model.put("viewsubscribers",viewSubscribers);
        return "admin/subscribers/viewsubscribers";

    }
}
