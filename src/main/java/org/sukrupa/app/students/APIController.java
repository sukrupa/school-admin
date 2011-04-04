package org.sukrupa.app.students;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping(value="/api")
public class APIController {

    @RequestMapping
    public void helloWorld(OutputStream output)
    {
        try {
            output.write("Hello".getBytes());
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
