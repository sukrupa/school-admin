package org.sukrupa.app.students;


import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

public class StudentImageView implements View{

    private final File imageFile;

    public StudentImageView(File imageFile){
        this.imageFile = imageFile;
    }

    @Override
    public String getContentType() {
        return "application/octet-binary";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        DataInputStream imageStream = new DataInputStream(new FileInputStream(imageFile));
        byte[] imageAsBytes = new byte[imageStream.available()];
        imageStream.read(imageAsBytes);
        ServletOutputStream stdOut = response.getOutputStream();
        response.setStatus(HttpServletResponse.SC_OK);
        stdOut.write(imageAsBytes);
        stdOut.flush();
        stdOut.close();
    }
}
