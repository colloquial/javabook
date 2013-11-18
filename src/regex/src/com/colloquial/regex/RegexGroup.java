package com.colloquial.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexGroup {

    public static void main(String[] args) {
        String regex = args[0];
        String text = args[1];

        System.out.println("Regex=|" + regex + "|");
        System.out.println("Text=|" + text + "|");

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        /*x RegexGroup.1 */
        if (matcher.matches()) {
            int ct = matcher.groupCount();
            for (int i = 0; i<=ct; i++) {
                String found = matcher.group(i);
                int start = matcher.start(i);
                int end = matcher.end(i);
                /*x*/
                System.out.println("Group: " + i 
                                   + " captures: |"+ found + "|"
                                   + " at (" + start + "," + end + ")");
            }
        } else {
            System.out.println("Not matched");
        }
    }

}