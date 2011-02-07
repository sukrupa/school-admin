package org.sukrupa.platform.hsqldb;

import org.junit.*;

import java.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConsoleOutputTest {

    private static final boolean AUTO_FLUSH = true;
    private PrintStream previousOut;
    private PrintStream out;
    private ByteArrayOutputStream bos;

    @Before
    public void setUp() throws Exception {
        bos = new ByteArrayOutputStream();
        out = new PrintStream(bos, AUTO_FLUSH, "UTF-8");
        previousOut = System.out;
        System.setOut(out);
    }

    @After
    public void tearDown() {
        System.setOut(previousOut);
    }

    @Test
    public void writes_a_line_to_std_out() throws Exception {
        new ConsoleOutput().println("Hello world");

        String output = getOutput();

        assertThat(output, is("Hello world\n"));
    }

    private String getOutput() throws UnsupportedEncodingException {
        return bos.toString("UTF-8");
    }
}