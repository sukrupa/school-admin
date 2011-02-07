package org.sukrupa.platform.hsqldb.io;

import org.junit.*;
import org.sukrupa.platform.hsqldb.io.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class ConsoleTest {

    private final SystemOutRecorder systemOutRecorder = new SystemOutRecorder();

    @Before
    public void setUp() throws Exception {
        systemOutRecorder.attatch();
    }

    @After
    public void tearDown() {
        systemOutRecorder.detatch();
    }

    @Test
    public void writes_a_line_to_std_out() throws Exception {
        new Console().println("Hello world");

        String output = systemOutRecorder.getOutput();

        assertThat(output, is("Hello world\n"));
    }

}