package com.colloquial.io;

import com.aliasi.util.Streams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.IOException;

public class CopyFile {

    public static void main(String[] args)
        throws FileNotFoundException, IOException {

        File fileIn = new File(args[0]);
        File fileOut = new File(args[1]);
        /*x CopyFile.1 */
        InputStream in = new FileInputStream(fileIn);
        OutputStream out = new FileOutputStream(fileOut);
        byte[] buf = new byte[8192];
        int n;
        while ((n = in.read(buf)) >= 0)
            out.write(buf,0,n);
        out.close();
        in.close();
        /*x*/
    }

    public static void main2(String[] args)
        throws FileNotFoundException, IOException {

        File fileIn = new File(args[0]);
        File fileOut = new File(args[1]);
        /*x CopyFile.2 */
        InputStream in = null;
        try {
            in = new FileInputStream(fileIn);
            OutputStream out = null;
            try {
                out = new FileOutputStream(fileOut);
                byte[] buf = new byte[8192];
                int n;
                try {
                    while ((n = in.read(buf)) >= 0)
                        out.write(buf,0,n);
                } catch (IOException e) {
                    fileOut.delete();
                    throw e;
                }
            } finally {
                Streams.closeQuietly(out);
            }
        } finally {
            Streams.closeQuietly(in);
        }
        /*x*/
    }

}