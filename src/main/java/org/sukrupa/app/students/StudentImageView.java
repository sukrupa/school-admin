package org.sukrupa.app.students;


import org.springframework.web.servlet.View;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

public class StudentImageView implements View{
    @Override
    public String getContentType() {
        return "application/octet-binary";
    }

    @Override
    public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
      File image = new File("/home/balaji/sukrupa/src/main/java/images/");
        DataInputStream imageStream = new DataInputStream(new FileInputStream(image));
        byte[] imageAsBytes = new byte[imageStream.available()];
        imageStream.read(imageAsBytes);
        ServletOutputStream stdOut = response.getOutputStream();
        response.setContentType("image/png");
        response.setStatus(HttpServletResponse.SC_OK);
        stdOut.write(imageAsBytes);
        stdOut.flush();
        stdOut.close();
    }
}
