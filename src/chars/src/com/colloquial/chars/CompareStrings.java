package com.colloquial.chars;

public class CompareStrings {
    public static void main(String[] args) {
        String s1 = args[0];
        String s2 = args[1];
        /*x CompareStrings.1 */
        if (s1.compareTo(s2) == 0) 
            System.out.println(s1 + " is equal to " + s2);
        else if (s1.compareTo(s2) < 0)
            System.out.println(s1 + " is less than " + s2);
        else if (s1.compareTo(s2) > 0)
            System.out.println(s1 + " is greater than " + s2);
        /*x*/
    }
}
