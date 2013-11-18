package com.colloquial.chars;

import com.ibm.icu.text.Transliterator;

import java.util.Enumeration;

public class ShowTransliterations {

    public static void main(String[] args) {
        /*x ShowTransliterations.1 */
        Enumeration<String> idEnum = Transliterator.getAvailableIDs();
        while (idEnum.hasMoreElements())
            System.out.println(idEnum.nextElement());
        /*x*/        
    }

}