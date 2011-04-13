package org.sukrupa.app.services;

import org.springframework.stereotype.Component;

import java.io.*;

import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copy;


@Component
public class FileHandler {
    public File create(String fileName) throws FileNotFoundException {
        File file = new File(fileName);
        if (!file.exists()) {
            throw new FileNotFoundException("Image not found");
        }
        return file;
    }

    public boolean save(String path, String name, InputStream inputStream) {
        File file = new File(getQualifiedPath(path) + name);
        FileOutputStream fout = null;
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            fout = new FileOutputStream(file);
            copy(inputStream, fout);
        }catch(IOException e){
            return false;
        }finally {
            closeQuietly(fout);
        }
        return true;
    }

    private String getQualifiedPath(String path) {
        return (path.endsWith("/")) ? path : path + "/";
    }
}
