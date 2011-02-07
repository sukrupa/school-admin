package org.sukrupa.platform.hsqldb;

import java.io.*;

public class SystemOutRecorder {
    static final boolean AUTO_FLUSH = true;
    PrintStream previousOut;
    PrintStream out;
    ByteArrayOutputStream bos;

    public SystemOutRecorder() {
    }

    String getOutput() throws UnsupportedEncodingException {
        return bos.toString("UTF-8");
    }

    void attatch() throws UnsupportedEncodingException {
        bos = new ByteArrayOutputStream();
        out = new PrintStream(bos, AUTO_FLUSH, "UTF-8");
        previousOut = System.out;
        System.setOut(out);
    }

    void detatch() {
        System.setOut(previousOut);
    }
}