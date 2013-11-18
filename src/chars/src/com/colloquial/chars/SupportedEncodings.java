package com.colloquial.chars;

import java.nio.charset.Charset;

import java.util.Map;
import java.util.Set;

public class SupportedEncodings {

    public static void main(String[] args) {
        /*x SupportedEncodings.1*/
        Charset defaultEncoding = Charset.defaultCharset();
        /*x*/
        System.out.println("Default Encoding=" + defaultEncoding);

        /*x SupportedEncodings.2*/
        Map<String,Charset> encodings = Charset.availableCharsets();
        for (Charset encoding : encodings.values()) {
            Set<String> aliases = encoding.aliases();
        /*x*/
            System.out.println("\n" + encoding);
            for (String alias : aliases)
                System.out.println("     " + alias);
        }
    }
}