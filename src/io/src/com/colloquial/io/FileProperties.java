package com.colloquial.io;

import java.io.File;
import java.io.IOException;

import java.util.Arrays;
import java.util.Date;

public class FileProperties {

    static public void main(String[] args) throws IOException {
        for (String arg : args) {
            System.out.println("arg=" + arg);
            File file = new File(arg);
            System.out.println("toString()=" + file.toString());
            System.out.println("getcanonicalFile()=" + file.getCanonicalFile());
            System.out.println("getName()=" + file.getName());
            System.out.println("getParent()=" + file.getParentFile());
            
            System.out.println("toURI()=" + file.toURI());
            System.out.println("toURI().toURL()=" + file.toURI().toURL());

            System.out.println("exists()=" + file.exists());
            System.out.println("isAbsolute()=" + file.isAbsolute());
            System.out.println("isDirectory()=" + file.isDirectory());
            System.out.println("isFile()=" + file.isFile());
            System.out.println("isHidden()=" + file.isHidden());

            System.out.println("hashCode()=" + file.hashCode());

            System.out.println("lastModified()=" + file.lastModified());
            System.out.println("new Date(lastModified())=" + new Date(file.lastModified()));

            System.out.println("length()=" + file.length());

            System.out.println("canRead()=" + file.canRead());
            System.out.println("canExecute()=" + file.canExecute());
            System.out.println("canWrite()=" + file.canWrite());

            System.out.println("getFreeSpace()=" + file.getFreeSpace());
            System.out.println("getTotalSpace()=" + file.getTotalSpace());
            System.out.println("getUsableSpace()=" + file.getUsableSpace());

            System.out.print("    listFiles()={");
            printFiles(file.listFiles());
            System.out.println("    }");
        }
    }

    static void printFiles(File[] files) {
        if (files == null) return;
        for (File file : files)
            System.out.print("\n          " + file);
    }

}