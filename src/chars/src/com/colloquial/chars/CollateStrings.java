package com.colloquial.chars;

import com.ibm.icu.text.Collator;

import java.io.UnsupportedEncodingException;
import java.io.PrintStream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class CollateStrings {

    public static void main(String[] args) throws UnsupportedEncodingException {
        System.setOut(new PrintStream(System.out,true,"UTF-8"));



        /*x CollateStrings.1 */
        ArrayList<String> words = new ArrayList<String>();
        String foo = "foo";
        words.add(foo);
        String foo_metal = "fo\u00F6";
        words.add(foo_metal);
        /*x*/
        String bar = "bar";
        words.add(bar);
        String BAZ = "BAZ";
        words.add(BAZ);
        String bar_metal = "b\u00E4r";
        words.add(bar_metal);
        String FOOT = "FOOT";
        words.add(FOOT);
        String oy = "oy";
        words.add(oy);
        String OOF_metal = "\u00D6OF";
        words.add(OOF_metal);
        String baz_acute = "b\u00E1z";
        words.add(baz_acute);
        String bar_acute = "b\u00E1r";
        words.add(bar_acute);
        String bar_grave = "b\u00E0r";
        words.add(bar_grave);


        printItems("unsorted items:", words);

        /*x CollateStrings.2 */
        Collections.sort(words);
        printItems("lexical sort order:",words);
        Collections.sort(words,
                         Collator.getInstance(Locale.FRENCH));
        printItems("Locale.FRENCH sort order:",words);
        Collections.sort(words,
                         Collator.getInstance(new Locale("sv")));
        printItems("Locale.SWEDISH sort order:",words);
        /*x*/
        
    }


    // print header followed by items in list, max 6 per line
    private static void printItems(String header, List<String> items) {
        System.out.println(header);
        for (int i=1; i<=items.size();i++) {
            System.out.print(" " + items.get(i-1));
            if ((i>0 && i%6==0) || i==items.size()) System.out.println();
            else if (i<items.size()) System.out.print(",");
        }
        System.out.println();
    }


}
