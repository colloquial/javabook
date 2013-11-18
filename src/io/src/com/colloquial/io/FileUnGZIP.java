package com.colloquial.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.zip.GZIPInputStream;

public class FileUnGZIP {

    public static void main(String[] args) 
        throws IOException {

        String fileIn = args[0];
        String fileOut = args[1];
        /*x FileUnGZIP.1 */
        InputStream in = new FileInputStream(fileIn);
        InputStream zipIn = new GZIPInputStream(in);
        OutputStream out = new FileOutputStream(fileOut);
        /*x*/
        byte[] bs = new byte[8192];
        for (int n; (n = zipIn.read(bs)) >= 0; )
            out.write(bs,0,n);

        zipIn.close();
        out.close();
        System.out.println("Original Size=" + new File(fileIn).length());
        System.out.println("Expanded Size=" + new File(fileOut).length());
    }

}