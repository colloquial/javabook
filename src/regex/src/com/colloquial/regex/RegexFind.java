package com.colloquial.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.text.*;

public class RegexFind {

    public static void main(String[] args) {
        String regex = args[0];
        String text = args[1];

        /*x RegexFind.1 */
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String found = matcher.group();
            int start = matcher.start();
            int end = matcher.end();
        /*x*/
            System.out.println("Found |" + found + "|"
                               + " at (" + start + "," + end + ")");
        }
    }

}