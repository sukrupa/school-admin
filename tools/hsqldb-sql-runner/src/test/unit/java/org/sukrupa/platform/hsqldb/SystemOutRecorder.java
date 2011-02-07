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
}