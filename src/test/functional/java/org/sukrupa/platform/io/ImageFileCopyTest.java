package org.sukrupa.platform.io;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;

import static junit.framework.Assert.assertTrue;
import static org.apache.commons.io.IOUtils.closeQuietly;
import static org.apache.commons.io.IOUtils.copy;


public class ImageFileCopyTest {
    @Test
    public void createOutputOnDiskFromServer() throws IOException {

        File photoFile = new File(System.getProperty("user.home") + "/.sukrupa/images/placeholderImage");

        byte[] originalBytes = readBytesFrom(photoFile);


        File copyOfPhotoFile = new File(System.getProperty("user.home") + "/.sukrupa/images/copyOfplaceholderImage");

        writeBytesTo(originalBytes, copyOfPhotoFile);

        byte[] copiedBytes = readBytesFrom(copyOfPhotoFile);

        System.out.println(Arrays.toString(originalBytes));
        System.out.println(Arrays.toString(copiedBytes));


        assertTrue(Arrays.equals(originalBytes, copiedBytes));


    }

    private static void writeBytesTo(byte[] bytes, File outputFile) {

        BufferedInputStream in = new BufferedInputStream(new ByteArrayInputStream(bytes));
        BufferedOutputStream out = null;
        try {
            if (!outputFile.exists()) {
                outputFile.createNewFile();
            }
            out = new BufferedOutputStream(new FileOutputStream(outputFile));
            copy(in, out);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(in);
            closeQuietly(out);
        }


    }

    private static byte[] readBytesFrom(File f) {
        InputStream in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            in = new FileInputStream(f);

            copy(in, out);

            return out.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            closeQuietly(in);
            closeQuietly(out);
        }
    }
}
