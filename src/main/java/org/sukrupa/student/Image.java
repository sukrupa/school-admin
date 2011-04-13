package org.sukrupa.student;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

public class Image {

    private CommonsMultipartFile source;

    public Image(CommonsMultipartFile source){
        this.source = source;
    }

    public InputStream getInputStream() throws IOException {
        return source.getInputStream();
    }
}
