package com.golden;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public enum FileAppender {

    APACHE("C:/golden/bin/httpd/conf/httpd.conf");

    private String path;

    FileAppender(String path) {
        this.path = path;
    }

    public void append() {
        try {
            FileWriter writer = new FileWriter(new File(this.path), true);
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
