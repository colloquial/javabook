package com.colloquial.chars;

import java.util.Arrays;

public class UnicodeProperties {

    public static void main(String[] args) {
        String lowHex = args[0];
        String highHex = args[1];
        
        int low = Integer.valueOf(lowHex,16);
        int high = Integer.valueOf(highHex,16);
        displayKey();
        displayCharacterCodes(low,high);
    }

    static void displayCharacterCodes(int low, int high) {
        System.out.println("\n\nVALUES\n");
        System.out.println("CHAR CD TYPE");
        System.out.println("---- -- ----");
        for (int i = low; i <= high; ++i) {
            char c = (char) i; // prevents overflow on all chars
            int charType = Character.getType(c);
            System.out.printf("%4s %s\n",
                              Integer.toHexString(c).toUpperCase(),
                              TYPE_TO_NAME[charType]);
        }
    }

    static void displayKey() {
        System.out.println("\nKEY\n");
        System.out.println("ID CD TYPE");
        System.out.println("-- -- ----");
        for (int i = 0; i < TYPE_TO_NAME.length; ++i)
            if (!"".equals(TYPE_TO_NAME[i]))
                System.out.printf("%2d %s\n",i,TYPE_TO_NAME[i]);
    }

    static final String[] TYPE_TO_NAME = new String[256];
    static {
        Arrays.fill(TYPE_TO_NAME,"");
        TYPE_TO_NAME[Character.COMBINING_SPACING_MARK] = "Mc COMBINING_SPACING_MARK";
        TYPE_TO_NAME[Character.CONNECTOR_PUNCTUATION] = "Pc CONNECTOR_PUNCTUATION";
        TYPE_TO_NAME[Character.CONTROL] = "Cc CONTROL";
        TYPE_TO_NAME[Character.CURRENCY_SYMBOL] = "Sc CURRENCY_SYMBOL";
        TYPE_TO_NAME[Character.DASH_PUNCTUATION] = "Pd DASH_PUNCTUATION";
        TYPE_TO_NAME[Character.DECIMAL_DIGIT_NUMBER] = "Nd DECIMAL_DIGIT_NUMBER";
        TYPE_TO_NAME[Character.ENCLOSING_MARK] = "Me ENCLOSING_MARK";
        TYPE_TO_NAME[Character.END_PUNCTUATION] = "Pe END_PUNCTUATION";
        TYPE_TO_NAME[Character.FINAL_QUOTE_PUNCTUATION] = "Pf FINAL_QUOTE_PUNCTUATION";
        TYPE_TO_NAME[Character.FORMAT] = "Cf FORMAT";
        TYPE_TO_NAME[Character.INITIAL_QUOTE_PUNCTUATION] = "Pi INITIAL_QUOTE_PUNCTUATION";
        TYPE_TO_NAME[Character.LETTER_NUMBER] = "Nl LETTER_NUMBER";
        TYPE_TO_NAME[Character.LINE_SEPARATOR] = "Zl LINE_SEPARATOR";
        TYPE_TO_NAME[Character.LOWERCASE_LETTER] = "Ll LOWERCASE_LETTER";
        TYPE_TO_NAME[Character.MATH_SYMBOL] = "Sm MATH_SYMBOL";
        TYPE_TO_NAME[Character.MODIFIER_LETTER] = "Lm MODIFIER_LETTER";
        TYPE_TO_NAME[Character.MODIFIER_SYMBOL] = "Sk MODIFIER_SYMBOL";
        TYPE_TO_NAME[Character.NON_SPACING_MARK] = "Mn NON_SPACING_MARK";
        TYPE_TO_NAME[Character.OTHER_LETTER] = "Lo OTHER_LETTER";
        TYPE_TO_NAME[Character.OTHER_NUMBER] = "No OTHER_NUMBER";
        TYPE_TO_NAME[Character.OTHER_PUNCTUATION] = "Po OTHER_PUNCTUATION";
        TYPE_TO_NAME[Character.OTHER_SYMBOL] = "So OTHER_SYMBOL";
        TYPE_TO_NAME[Character.PARAGRAPH_SEPARATOR] = "Zp PARAGRAPH_SEPARATOR";
        TYPE_TO_NAME[Character.PRIVATE_USE] = "Co PRIVATE_USE";
        TYPE_TO_NAME[Character.SPACE_SEPARATOR] = "Zs SPACE_SEPARATOR";
        TYPE_TO_NAME[Character.START_PUNCTUATION] = "Ps START_PUNCTUATION";
        TYPE_TO_NAME[Character.SURROGATE] = "Cs SURROGATE";
        TYPE_TO_NAME[Character.TITLECASE_LETTER] = "Lt TITLECASE_LETTER";
        TYPE_TO_NAME[Character.UNASSIGNED] = "Cn UNASSIGNED";
        TYPE_TO_NAME[Character.UPPERCASE_LETTER] = "Lu UPPERCASE_LETTER";
    }

    /*
        TYPE_TO_NAME[Character.DIRECTIONALITY_ARABIC_NUMBER] = "AN DIRECTIONALITY_ARABIC_NUMBER";
        TYPE_TO_NAME[Character.DIRECTIONALITY_BOUNDARY_NEUTRAL] = "BN DIRECTIONALITY_BOUNDARY_NEUTRAL";
        TYPE_TO_NAME[Character.DIRECTIONALITY_COMMON_NUMBER_SEPARATOR] = "CS DIRECTIONALITY_COMMON_NUMBER_SEPARATOR";
        TYPE_TO_NAME[Character.DIRECTIONALITY_EUROPEAN_NUMBER] = "EN DIRECTIONALITY_EUROPEAN_NUMBER";
        TYPE_TO_NAME[Character.DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR] = "ES DIRECTIONALITY_EUROPEAN_NUMBER_SEPARATOR";
        TYPE_TO_NAME[Character.DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR] = "ET DIRECTIONALITY_EUROPEAN_NUMBER_TERMINATOR";
        TYPE_TO_NAME[Character.DIRECTIONALITY_LEFT_TO_RIGHT] = "L DIRECTIONALITY_LEFT_TO_RIGHT";
        TYPE_TO_NAME[Character.DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING] = "LRE DIRECTIONALITY_LEFT_TO_RIGHT_EMBEDDING";
        TYPE_TO_NAME[Character.DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE] = "LRO DIRECTIONALITY_LEFT_TO_RIGHT_OVERRIDE";
        TYPE_TO_NAME[Character.DIRECTIONALITY_NONSPACING_MARK] = "NSM DIRECTIONALITY_NONSPACING_MARK";
        TYPE_TO_NAME[Character.DIRECTIONALITY_OTHER_NEUTRALS] = "ON DIRECTIONALITY_OTHER_NEUTRALS";
        TYPE_TO_NAME[Character.DIRECTIONALITY_PARAGRAPH_SEPARATOR] = "B DIRECTIONALITY_PARAGRAPH_SEPARATOR";
        TYPE_TO_NAME[Character.DIRECTIONALITY_POP_DIRECTIONAL_FORMAT] = "PDF DIRECTIONALITY_POP_DIRECTIONAL_FORMAT";
        TYPE_TO_NAME[Character.DIRECTIONALITY_RIGHT_TO_LEFT] = "R DIRECTIONALITY_RIGHT_TO_LEFT";
        TYPE_TO_NAME[Character.DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC] = "AL DIRECTIONALITY_RIGHT_TO_LEFT_ARABIC";
        TYPE_TO_NAME[Character.DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING] = "RLE DIRECTIONALITY_RIGHT_TO_LEFT_EMBEDDING";
        TYPE_TO_NAME[Character.DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE] = "RLO DIRECTIONALITY_RIGHT_TO_LEFT_OVERRIDE";
        TYPE_TO_NAME[Character.DIRECTIONALITY_SEGMENT_SEPARATOR] = "S DIRECTIONALITY_SEGMENT_SEPARATOR";
        // -1, can't go into array and no name 
        // TYPE_TO_NAME[Character.DIRECTIONALITY_UNDEFINED] = "?? DIRECTIONALITY_UNDEFINED"; 
        TYPE_TO_NAME[Character.DIRECTIONALITY_WHITESPACE] = "WS DIRECTIONALITY_WHITESPACE";
    */

}