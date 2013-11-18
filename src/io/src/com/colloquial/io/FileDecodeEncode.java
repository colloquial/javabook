package com.colloquial.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

public class FileDecodeEncode {

    public static void main(String[] args) 
        throws UnsupportedEncodingException, IOException {

        String fileIn = args[0];
        String encIn = args[1];
        String fileOut = args[2];
        String encOut = args[3];
        /*x FileDecodeEncode.1 */
        InputStream in = new FileInputStream(fileIn);
        InputStreamReader reader = new InputStreamReader(in,encIn);
        OutputStream out = new FileOutputStream(fileOut);
        OutputStreamWriter writer = new OutputStreamWriter(out,encOut);
        char[] buf = new char[4096];
        for (int n; (n = reader.read(buf)) >= 0; )
            writer.write(buf,0,n);
        writer.close();
        reader.close();
        /*x*/
    }
}
