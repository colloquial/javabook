package com.colloquial.diags;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Toast {

    /*x Toast.1*/
    public static String CHEERS = "cheers";
    public static String SANTE = "sant\u00e9";
    public static String SKOL = "sk\u00e5l";
    public static String GAMBAE = "\uac74\ubc30";
    /*x*/
    public static String NAZDROV = "\u041d\u0430 \u0437\u0434\u043e\u0440\u0432\u044c";
    public static String KANPAI = "\u4e73\u676f";

    public static void main(String[] args) 
        throws IOException {

        System.out.println("encoding: " 
                           + System.getProperty("file.encoding"));
        System.out.println("Before we drink, we say a toast");
        System.out.println("in English: " + CHEERS);
        System.out.println("in French:  " + SANTE);
        System.out.println("in Norwegian: " + SKOL);
        System.out.println("in Russian: " + NAZDROV);
        System.out.println("in Japanese: " + KANPAI);
        System.out.println("in Korean: " + GAMBAE);
    }
}
