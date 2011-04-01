package org.sukrupa.app.services;

import java.io.File;
import java.io.FileNotFoundException;


public class FileFactory {
    public File create(String fileName) throws FileNotFoundException {
        return new File(fileName);
    }
}
