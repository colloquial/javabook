package com.colloquial.io;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;

import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileZipView {

    public static void main(String[] args)
        throws IOException {

        String fileZipIn = args[0];
        /*x FileZipView.1 */
        InputStream in = new FileInputStream(fileZipIn);
        ZipInputStream zipIn = new ZipInputStream(in);
        byte[] bs = new byte[8192];
        while (true) {
            ZipEntry entry = zipIn.getNextEntry();
            if (entry == null) break;
            printEntryProperties(entry);
            for (int n; (n = zipIn.read(bs)) >= 0; )
                System.out.write(bs,0,n);
        }
        zipIn.close();
        /*x*/
    }

    static void printEntryProperties(ZipEntry entry) {

        System.out.println("\n---------------------------");
        System.out.println("getName()=" + entry.getName());
        System.out.println("isDirectory()=" + entry.isDirectory());
        System.out.println("getSize()=" + entry.getSize());
        System.out.println("getCompressedSize()=" + entry.getCompressedSize());
        System.out.println("getTime()=" + entry.getTime());
        if (entry.getTime() != -1)
            System.out.println("new Date(getTime())=" 
                               + new java.util.Date(entry.getTime()));
        System.out.println("getCrc()=" + entry.getCrc());
        System.out.println("getComment()=" + entry.getComment());

    }

}