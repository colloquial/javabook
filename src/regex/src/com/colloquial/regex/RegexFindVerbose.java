package com.colloquial.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.*;
import java.text.*;

public class RegexFindVerbose {

    public static void main(String[] args) {
        String regex = args[0];
        String text = args[1];
        System.out.println("Regex=|" + regex + "|");
        System.out.println("Text=|" + text + "|");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            int start = matcher.start();
            int end = matcher.end();
            String found = matcher.group();
	    System.out.println("matched: |" + found + "|"
                               + " at (" + start + "," + end + ")");
            /*x RegexFindVerbose.1 */
            matcher.appendReplacement(sb,"");
            sb.setLength(0);
            matcher.appendTail(sb);
            /*x*/
	    System.out.println("remaining: |" + sb.toString() + "|");
        }
    }

}