package org.sukrupa.app.admin.subscribers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.sukrupa.platform.RequiredByFramework;

import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/admin")
public class SubscriberController {

    @RequiredByFramework
    public SubscriberController(){
        
    }

    private SubscriberRepository subscriberRepository;
    
    @Autowired
    public SubscriberController(SubscriberRepository subscriberRepository) {
              this.subscriberRepository=subscriberRepository;
    }

    @RequestMapping("subscribers")
    public String shouldDisplaySubscribers(Map<String,Object> model){
        List<Subscriber> subscriberList = subscriberRepository.getList();
        model.put("subscriberList", subscriberList);
        return "admin/subscribers/viewsubscribers";
    }
    @RequestMapping("deletesubscriber")
    @Transactional
    public String shouldDeleteSubscriber(Map<String,Object> model, @RequestParam int subid){
        Subscriber subscriberToBeDeleted = subscriberRepository.findById(subid);
        this.subscriberRepository.deleteSubscriber(subscriberToBeDeleted);
        model.clear();
        return "redirect:/admin/subscribers";
    }
}
