package org.sukrupa.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static java.lang.String.format;

@Controller
@RequestMapping("/students/{studentId}/notes")
public class NoteController {

    @RequestMapping("/add")
    public String all(@PathVariable String studentId) {
        return format("redirect:/students/%s", studentId);
    }
}
