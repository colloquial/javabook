package com.colloquial.diags;

public class VerifyChars {
    /*x VerifyChars.1*/
    public static void main(String[] args) {
        System.out.println("a string of question marks");
        hexForChars("???");
        System.out.println("a string of japanese characters");
        hexForChars("\u4eca\u65e5\u306f");
    }

    static void hexForChars(String s) {
        for (char c : s.toCharArray())
            System.out.printf("%4s  ",Integer.toHexString(c));
        System.out.println();
    }
    /*x*/
}

