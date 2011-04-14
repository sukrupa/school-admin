package org.sukrupa.app.students;


import org.springframework.web.servlet.View;
import org.sukrupa.student.Image;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

import static org.apache.commons.io.IOUtils.copy;

public class StudentImageView implements View{

    private Image image;

    public StudentImageView(Image image){
        this.image = image;
    }

    @Override
    public String getContentType() {
        return "application/octet-binary";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ServletOutputStream stdOut = response.getOutputStream();
        response.setStatus(HttpServletResponse.SC_OK);
        copy(image.getInputStream(),stdOut);
        stdOut.flush();
        stdOut.close();
    }
}
