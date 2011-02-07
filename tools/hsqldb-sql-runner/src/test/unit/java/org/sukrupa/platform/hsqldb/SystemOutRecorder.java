package org.sukrupa.platform.hsqldb;

import java.io.*;

public class SystemOutRecorder {
    private static final boolean AUTO_FLUSH = true;

    private PrintStream out;
    private ByteArrayOutputStream bos;
    private PrintStream previousOut;

    public void attatch() throws UnsupportedEncodingException {
        bos = new ByteArrayOutputStream();
        out = new PrintStream(bos, AUTO_FLUSH, "UTF-8");
        previousOut = System.out;
        System.setOut(out);
    }

    public String getOutput() throws UnsupportedEncodingException {
        return bos.toString("UTF-8");
    }

    public void detatch() {
        System.setOut(previousOut);
    }
}