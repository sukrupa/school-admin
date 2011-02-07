package org.sukrupa.platform.hsqldb;

import org.junit.*;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConsoleOutputTest {

    private final SystemOutRecorder systemOutRecorder = new SystemOutRecorder();

    @Before
    public void setUp() throws Exception {
        systemOutRecorder.bos = new ByteArrayOutputStream();
        systemOutRecorder.out = new PrintStream(systemOutRecorder.bos, SystemOutRecorder.AUTO_FLUSH, "UTF-8");
        systemOutRecorder.previousOut = System.out;
        System.setOut(systemOutRecorder.out);
    }

    @After
    public void tearDown() {
        System.setOut(systemOutRecorder.previousOut);
    }

    @Test
    public void writes_a_line_to_std_out() throws Exception {
        new ConsoleOutput().println("Hello world");

        String output = systemOutRecorder.getOutput();

        assertThat(output, is("Hello world\n"));
    }

    private String getOutput() throws UnsupportedEncodingException {
        return systemOutRecorder.getOutput();
    }
}