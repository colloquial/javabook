package com.colloquial.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static java.util.regex.Pattern.DOTALL;

class FragmentsRegex {

    void one() {
        /*x FragmentsRegex.1 */
        Pattern p1 = Pattern.compile("(xyz)*");
        Pattern p2 = Pattern.compile("(xyz(xyz)*)|");
        /*x*/
    }

    void two() {
        /*x FragmentsRegex.2 */
        Pattern p = Pattern.compile("cat");
        Matcher m = p.matcher("one cat two cats in the yard");
        StringBuffer sb = new StringBuffer();
        while (m.find()) {
            m.appendReplacement(sb, "dog");
        }
        m.appendTail(sb);
        System.out.println(sb.toString());
        /*x*/
    }
    
    /*x FragmentsRegex.3 */
        public String[] split(String regex) {
            return split(regex, 0);
        }

        public String[] split(String regex, int limit) {
            return Pattern.compile(regex).split(this, limit);
        }
    /*x*/

    void four() {
        String regex = "Mr((\\s(\\p{Lu}\\p{L}*))+)";
        String text = "My name is Mr JJ John Jacob Jingleheimer Smith";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        System.out.println(text);
        while (matcher.find()) {
            /*x FragmentsRegex.4 */
            matcher.replaceAll("$3");
            /*x*/
    }

    void five() {
        /*x FragmentsRegex.5 */
        Pattern.compile(".").matcher("A").matches();
        Pattern.compile(".",DOTALL).matcher("\n").matches();
        /*x*/

        /*x FragmentsRegex.6 */
        Pattern.compile(".").matcher("\n").matches();
        /*x*/

    }

}