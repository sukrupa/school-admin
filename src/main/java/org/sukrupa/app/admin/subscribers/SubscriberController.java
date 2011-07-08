package org.sukrupa.app.admin.subscribers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin/subscribers/")
public class SubscriberController {


    private SubscriberRepository subscriberRepository;
    @Autowired
    public SubscriberController(SubscriberRepository subscriberRepository) {
              this.subscriberRepository=subscriberRepository;
    }


    @RequestMapping
    public String listsubscribers(Map<String, List<Subscriber>> model) {
        //List<Subscriber> viewSubscribersList = subscriberRepository.findAllSubscribers();
        //model.put("subscribers",viewSubscribersList);
        return "admin/subscribers/";

    }
}
