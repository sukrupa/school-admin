package org.sukrupa.student;


import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.*;

public class Image {

    private Serializable source;

    public Image(CommonsMultipartFile source){
        this.source = source;
    }

    public Image(File source) {
        this.source = source;
    }

    public InputStream getInputStream() throws IOException {
        if(source instanceof CommonsMultipartFile){
            return ((CommonsMultipartFile)source).getInputStream();
        }
        else{
            return new FileInputStream((File)source);
        }
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Image image = (Image) o;

        if (source != null ? !source.equals(image.source) : image.source != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return source != null ? source.hashCode() : 0;
    }

}
