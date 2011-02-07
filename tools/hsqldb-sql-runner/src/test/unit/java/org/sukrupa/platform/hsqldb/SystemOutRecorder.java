package org.sukrupa.platform.hsqldb;

import java.io.*;

public class SystemOutRecorder {
    private static final boolean AUTO_FLUSH_ON = true;

    private final String encoding = "UTF8";

    private PrintStream out;
    private ByteArrayOutputStream buffer;
    private PrintStream previousOut;

    public void attatch() throws UnsupportedEncodingException {
        buffer = new ByteArrayOutputStream();
        out = new PrintStream(buffer, AUTO_FLUSH_ON, getEncoding());
        previousOut = System.out;
        System.setOut(out);
    }

    public String getOutput() throws UnsupportedEncodingException {
        return buffer.toString(getEncoding());
    }

    private String getEncoding() {
        return encoding;
    }

    public void detatch() {
        System.setOut(previousOut);
    }
}