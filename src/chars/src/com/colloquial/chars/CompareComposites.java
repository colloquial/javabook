package com.colloquial.chars;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class CompareComposites {

    public static void main(String[] args) throws UnsupportedEncodingException {
        /*x CompareComposites.1 */
        String s1 = "\u00E0"; // a composed with grave
        String s2 = "a\u0300"; // a followed by grave
        System.setOut(new PrintStream(System.out,true,"UTF-8"));
        /*x*/
        if (s1.compareTo(s2) == 0) 
            System.out.println(s1 + " (s1) is equal to " + s2 + " (s2)");
        else if (s1.compareTo(s2) < 0) 
            System.out.println(s1 + " (s1) is less than " + s2 + " (s2)");
        else if (s1.compareTo(s2) > 0) 
            System.out.println(s1 + " (s1) is greater than " + s2 + " (s2)");

        System.out.print("s1 char values: ");
        for (char c : s1.toCharArray())
            System.out.printf("%4h",c);
        System.out.println();

        System.out.print("s2 char values: ");
        for (char c : s2.toCharArray())
            System.out.printf("%4h",c);
        System.out.println();

    }
}
