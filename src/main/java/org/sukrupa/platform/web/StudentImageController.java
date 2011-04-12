package org.sukrupa.platform.web;

import com.google.common.io.ByteStreams;
import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.ConfigurableWebApplicationContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.sukrupa.app.services.ImageLoaderService;
import org.sukrupa.app.students.StudentImageView;

import javax.imageio.stream.FileImageInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.plaf.metal.MetalIconFactory;
import java.io.*;

import static java.lang.String.format;

@Controller
@RequestMapping("/application/images")
public class StudentImageController {

    private ImageLoaderService imageLoaderService;

    @Autowired
    public StudentImageController(ImageLoaderService imageLoaderService){
        this.imageLoaderService = imageLoaderService;
    }

    @RequestMapping(value = "{id}")
    public View getImage(@PathVariable String id) throws FileNotFoundException{
        return new StudentImageView(imageLoaderService.load(id));
    }
}
