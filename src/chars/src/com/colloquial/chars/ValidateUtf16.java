package com.colloquial.chars;

public class ValidateUtf16 {

    public static void validate(String s) {
        System.out.print("UTF-16 chars in s: [ ");
        for (char c : s.toCharArray()) 
            System.out.printf("%4s ",Integer.toHexString(c));
        System.out.println("], s is valid string: " + isValidUtf16(s));
    }

    /*x ValidateUtf16.1 */
    public static boolean isValidUtf16(CharSequence cs) {
        for (int i = 0; i < cs.length(); ++i) {
            char c = cs.charAt(i);
            if (Character.isLowSurrogate(c)) 
                return false;
            if (!Character.isHighSurrogate(c)) {
                int codePoint = Character.codePointAt(cs,i);
                if (!Character.isValidCodePoint(codePoint))
                    return false;
                continue;
            }
            ++i;
            if (i >= cs.length()) return false;
            char c2 = cs.charAt(i);
            if (!Character.isLowSurrogate(c2))
                return false;
            int codePoint = Character.toCodePoint(c,c2);
            if (!Character.isValidCodePoint(codePoint))
                return false;
        }
        return true;
    }
    /*x*/

    public static void main(String[] args) {

        /*x ValidateUtf16.2 */
        int codepoint = 0x2070E;
        char[] chars = Character.toChars(codepoint);
        String s1 = new String(chars);
        validate(s1);
        /*x*/

        /*x ValidateUtf16.3 */
        char tmp = chars[0];
        chars[0] = chars[1];
        chars[1] = tmp;
        String s2 = new String(chars);
        validate(s2);
        /*x*/
    }
}
