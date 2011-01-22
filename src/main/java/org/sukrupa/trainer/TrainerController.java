package org.sukrupa.trainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/*
 * Sample urls: - http://localhost:8080/sukrupa/app/trainers
 *              - http://localhost:8080/sukrupa/app/trainers/pat
 */
@Controller
@RequestMapping("/trainers")
public class TrainerController {

    private TrainerService service;

    @Autowired
    public TrainerController(TrainerService service) {
        this.service = service;
    }

    @RequestMapping()
    public String all(Map<String, Object> model) {
        model.put("trainers", service.findAll());
        return "trainers";
    }

    @RequestMapping("/{trainer}")
    public String single(@PathVariable String trainer, Map<String, Object> model) {
        model.put("trainer", service.find(trainer));
        return "trainer";
    }
}
