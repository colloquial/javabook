package com.colloquial.chars;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class ByteToString {

    /*x ByteToString.1 */
    public static void main(String[] args) 
        throws UnsupportedEncodingException {
        String s = "D\u00E9j\u00E0 vu";
        String toBytes = args[0];
        String toString = args[1];
        byte[] bs = s.getBytes(toBytes);
        String t = new String(bs,toString);
    /*x*/

        System.out.println("char[] from string s");
        dumpString(s);
        System.out.println("byte[] from string s using encoding: " + toBytes);
        dumpBytes(bs);
        System.out.println("convert bytes to string t using encoding: " + toString);
        System.out.println("char[] from string t");
        dumpString(t);
    }

    /*x ByteToString.3 */
    static void dumpBytes(byte[] bs) {
        for (byte b : bs)
            System.out.printf("%4h  ",b >= 0 ? b : 256+b);
        System.out.println();
    }
    /*x*/
    /*x ByteToString.2 */
    static void dumpString(String s) {
        for (char c : s.toCharArray())
            System.out.printf("%4s  ",Integer.toHexString(c));
        System.out.println();
    }
    /*x*/
}