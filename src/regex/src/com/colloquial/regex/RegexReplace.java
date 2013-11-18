package com.colloquial.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexReplace {

    public static void main(String[] args) {
        String regex = args[0];
        String replacement = args[1];
        String text = args[2];

        /*x RegexReplace.1 */
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        String result = matcher.replaceAll(replacement);
        /*x*/

        System.out.println("regex=|" + regex + "|");
        System.out.println("replacement=|" + replacement + "|");
        System.out.println("text=|" + text + "|");
        System.out.println("result=|" + result + "|");
    }

}