package com.colloquial.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.IOException;

public class FileByteCount {

    /*x FileByteCount.1 */
    public static void main(String[] args)
        throws FileNotFoundException, IOException {

        File file = new File(args[0]);
        InputStream in = new FileInputStream(file);
        long[] counts = new long[256];
        int b;
        while ((b = in.read()) != -1)
            ++counts[b];
    /*x*/
        
        System.out.println("File=" + file.getCanonicalPath());
        /*x FileByteCount.2 */
        System.out.printf("%4s %4s %10s\n","Dec","Hex","Count");
        for (int i = 0; i < counts.length; ++i)
            if (counts[i] > 0L)
                System.out.printf("%4d %4h %10d\n",i,i,counts[i]);
        /*x*/        
    }

}
