package com.colloquial.webchars;

import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.net.URLDecoder;

public class EncodeDecodeUrl {

    public static void main(String[] args)  
        throws IOException, UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out,true,"UTF-8"));

        /*x EncodeDecodeUrl.1 */
        String s1 = "\u00c0 votre sant\u00e9!";
        String s2 = URLEncoder.encode(s1,"UTF-8");
        String s3 = URLDecoder.decode(s2,"UTF-8");
        /*x*/
        System.out.println("string: " + s1);
        System.out.println("url encoded: " + s2);
        System.out.println("decoded: " + s3);
    }
}