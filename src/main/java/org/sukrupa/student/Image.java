package org.sukrupa.student;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.InputStream;

public class Image {

    private CommonsMultipartFile source;

    public Image(CommonsMultipartFile source){
        this.source = source;
    }

    public InputStream getInputStream() {
        return null;
    }
}
