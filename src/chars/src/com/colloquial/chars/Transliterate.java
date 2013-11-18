package com.colloquial.chars;

import com.ibm.icu.text.Transliterator;

import java.io.IOException;
import java.io.PrintStream;

import java.util.Enumeration;

public class Transliterate {

    public static void main(String[] args) throws IOException {
        /*x Transliterate.1 */
        String text = args[0];
        String scheme = args[1];
        Transliterator trans = Transliterator.getInstance(scheme);
        String out = trans.transliterate(text);
        /*x*/

        /*x Transliterate.3 */
        System.setOut(new PrintStream(System.out,true,"UTF-8"));
        /*x*/

        System.out.println("Scheme=" + scheme);
        System.out.println("Input=" + text);
        /*x Transliterate.2 */
        System.out.println("Output=" + out);
        System.out.println("Output char values");
        for (char c : out.toCharArray())
            System.out.printf("%5h",c);
        /*x*/
    }
    
}