package org.sukrupa.app.httpcommunicationservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.sukrupa.app.admin.subscribers.Subscriber;
import org.sukrupa.app.admin.subscribers.SubscriberRepository;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/addsubscriberinfo")
public class HTTPSubscriberController {

    private SubscriberRepository subscriberRepository;


    @Autowired
    public HTTPSubscriberController(SubscriberRepository subscriberRepository) {

        this.subscriberRepository = subscriberRepository;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public String addSubscriberFromWebsite(@RequestParam String subscriberName, @RequestParam String subscriberEmail) {
        Subscriber subscriber = new Subscriber(subscriberName, subscriberEmail);
        subscriberRepository.addSubscriber(subscriber);
        return "Success";
    }


}
