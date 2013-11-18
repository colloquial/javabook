package com.colloquial.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexMatch {

    public static void main(String[] args) {
        /*x RegexMatch.1 */
        String regex = args[0];
        String text = args[1];

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        boolean matches = matcher.matches();
        /*x*/

        System.out.println("Regex=|" + regex + "|");
        System.out.println("Text=|" + text + "|");
        System.out.println("Matches=" + matches);
    }

}