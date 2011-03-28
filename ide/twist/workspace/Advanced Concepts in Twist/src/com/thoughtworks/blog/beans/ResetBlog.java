package com.thoughtworks.blog.beans;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.codec.binary.Base64;

public class ResetBlog {

    private final String blogUrl;
    private final String user;
    private final String pass;

    public ResetBlog(String blogUrl, String user, String pass) {
        this.blogUrl = blogUrl;
        this.user = user;
        this.pass = pass;
    }

    public void resetBlog() throws Exception {
        System.out.println("** Resetting blog...");
        stopBlog();
        deletePebbleConfiguration();
        startBlog();
    }

    private void startBlog() throws Exception {
        System.out.println(" * Starting blog...");
        manageContext("/manager/start?path=/blog");
    }

    private void stopBlog() throws Exception {
        System.out.println(" * Stopping blog...");
        manageContext("/manager/stop?path=/blog");
    }

    private void deletePebbleConfiguration() {
        System.out.println(" * Deleting blog configuration...");

        String homeDir = System.getProperty("user.home");
        String pebbleDataDir = homeDir + "/pebble";
        System.out.println("  * Deleting directory " + pebbleDataDir + " recursively...");
        deleteDir(new File(pebbleDataDir));
    }

    private boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        // The directory is now empty so delete it
        return dir.delete();
    }

    private void manageContext(String suffix) throws Exception {
        HttpURLConnection hconn = (HttpURLConnection) new URL(blogUrl + suffix).openConnection();
        hconn.setAllowUserInteraction(false);
        hconn.setDoInput(true);
        hconn.setUseCaches(false);
        hconn.setDoOutput(false);

        String output = user + ":" + pass;
        output = new String(Base64.encodeBase64(output.getBytes()));

        hconn.setRequestProperty("Authorization", (new StringBuilder()).append("Basic ").append(output).toString());
        hconn.connect();
        InputStreamReader reader = new InputStreamReader(hconn.getInputStream());

        BufferedReader bufferedReader = new BufferedReader(reader);
        boolean ok = false;
        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            if (line.startsWith("OK -")) {
                ok = true;
                System.out.println("  * " + line);
            }
        }
        if (!ok) {
            System.err.println("** Could not talk to tomcat using the specified username and password.");
            throw new RuntimeException("There was an error talking to tomcat.");
        }
    }
}
