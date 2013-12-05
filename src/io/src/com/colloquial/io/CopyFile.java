package com.colloquial.io;

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


}