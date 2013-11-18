package com.colloquial.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.zip.GZIPOutputStream;

public class FileGZIP {

    public static void main(String[] args) 
        throws IOException {

        String fileIn = args[0];
        String fileOut = args[1];
        /*x FileGZIP.1 */
        InputStream in = new FileInputStream(fileIn);
        OutputStream out = new FileOutputStream(fileOut);
        OutputStream zipOut = new GZIPOutputStream(out);

        byte[] bs = new byte[8192];
        for (int n; (n = in.read(bs)) >= 0; )
            zipOut.write(bs,0,n);

        in.close();
        zipOut.close();
        /*x*/

        System.out.println("Original Size=" + new File(fileIn).length());
        System.out.println("Compressed Size=" + new File(fileOut).length());

    }

}