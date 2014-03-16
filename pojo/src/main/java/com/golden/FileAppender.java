package com.golden;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public enum FileAppender {

    APACHE("C:/golden/conf/httpd.conf");

    private File local;

    FileAppender(String path) {
        try {
            this.local = new File(path);
            local.delete();
            local.createNewFile();
        } catch (IOException ex) {
            System.out.println("Error Writing/Appending to output File");
        }
    }

    public void append() {
        try {

            FileWriter writer = new FileWriter(local, true);
            writer.append("\nListen 80");
            writer.append("\nNameVirtualHost *:80");
            writer.append("\n<VirtualHost *:80>");
            writer.append("\n\tServerName mhaworks");
            writer.append("\n\tDocumentRoot C:\\golden\\www\n");
            writer.append("\t<Directory \"C:\\golden\\www\">\n");
            writer.append("\t\tOptions Indexes FollowSymLinks\n");
            writer.append("\t\tAllowOverride None\n");
            writer.append("\t\tOrder allow,deny\n");
            writer.append("\t\tAllow from all\n");
            writer.append("\t</Directory>\n");
            writer.append("</VirtualHost>\n");
            writer.close();
        } catch (IOException ex) {
            System.out.println("Failure to 'write' the 'read'");
        }
    }

}
