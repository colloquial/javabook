package com.colloquial.chars;

import java.nio.CharBuffer;

import java.text.DateFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;

import java.util.Comparator;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

class FragmentsChars {

    void frag1frag2() {
        /*x FragmentsChars.1 */
        String s = new String("abc");
        String t = new String("abc");
        /*x*/

        /*x FragmentsChars.2 */
        String sIn = s.intern();
        String tIn = t.intern();
        /*x*/
    }

    void frag3() {
        /*x FragmentsChars.3 */
        int n = 7;    String s = "ABC";    boolean b = true;  
        char c = 'C';    Object z = null;
        String t = new StringBuilder().append(n).append(s).append(b)
            .append(c).append(z).toString();
        /*x*/
    }

    void frag4() {
        /*x FragmentsChars.4 */
        String u = 7 + "ABC";
        String v = String.valueOf(7) + "ABC";
        /*x*/
    }

    void frag5() {
        /*x FragmentsChars.5 */
        String[] xs = new String[] { "a", "b", "c" };
        CharBuffer cb = CharBuffer.allocate(1000);
        for (String s : xs)
            cb.put(s);
        cb.flip();
        String s = cb.toString();
        /*x*/
    }

    void frag6() {
        int codepoint = 0;
        /*x FragmentsChars.6 */
        int LEAD_OFFSET = 0xD800 - (0x10000 >> 10);
        int SURROGATE_OFFSET = 0x10000 - (0xD800 << 10) - 0xDC00;

        int lead = LEAD_OFFSET + (codepoint >> 10);
        int trail = 0xDC00 + (codepoint & 0x3FF);
        
        int byte1 = lead >>> 8;
        int byte2 = lead & 0xFF;
        int byte3 = trail >>> 8;
        int byte4 = trail & 0xFF;
        /*x*/

    }

    void frag7() {
        int byte1 = 0;
        int byte2 = 0;
        int byte3 = 0;
        int byte4 = 0;
        int SURROGATE_OFFSET = 0x10000 - (0xD800 << 10) - 0xDC00;

        /*x FragmentsChars.7 */
        int lead = byte1 << 8 | byte2;
        int trail = byte3 << 8 | byte4;
        /*x*/

        int codepoint = (lead << 10) + trail + SURROGATE_OFFSET;
    }

    void frag8() {
        /*x FragmentsChars.8 */
        int n = 1;
        /*x*/
    }

    void frag9() {
        /*x FragmentsChars.9 */
        \u0069\u006E\u0074\u0020\u006E\u0020\u003D\u0020\u0031\u003B
        /*x*/
    }

    void frag10() {
        /*x FragmentsChars.10 */
        char c = 'a'; 
        /*x*/
    }

    void frag11() {
        /*x FragmentsChars.11 */
        char c = '\u00E0'; 
        /*x*/
    }

    void frag12() {
        /*x FragmentsChars.12 */
        char c = 0x00E0;
        /*x*/
    }

    void frag13() {
        /*x FragmentsChars.13 */
        int n = 'a';
        /*x*/
    }

    void frag14() {
        CharSequence cs = null;
        /*x FragmentsChars.14 */
        for (int i = 0; i < cs.length(); ++i) {
            char c = cs.charAt(i);
            // do something
            /*x*/
        }
    }

    void frag15() {
        /*x FragmentsChars.15 */
        String name = "Fred";
        /*x*/
    }


    void frag16() {
        /*x FragmentsChars.16 */
        String name = "\u0046r\u0065d";
        /*x*/
    }


    int abc17() {
        String s = null;
        String t = null;
        /*x FragmentsChars.17 */
        for (int i = 0; i < Math.min(s.length(),t.length()); ++i)
            if (s.charAt(i) != t.charAt(i))
                return s.charAt(i) - t.charAt(i);
        return s.length() - t.length();
        /*x*/
    }


    void frag18() {
        String s = null;
        /*x FragmentsChars.18 */
        int hashCode = 0;
        for (int i = 0; i < s.length(); ++i)
            hashCode = 31 * hashCode + s.charAt(i);
        /*x*/
    }

    void frag19() {
        String[] xs = null;
        /*x FragmentsChars.19 */
        StringBuilder sb = new StringBuilder();
        for (String x : xs)
            sb.append(x);
        String s = sb.toString();
        /*x*/
    }


    void frag20() {
        /*x FragmentsChars.20 */
        String s = "abc";
        for (int i = 0; i < s.length(); ) {
            int codepoint = s.codePointAt(i);
            // Do something with codepoint.
            i += Character.charCount(codepoint);
        }
        /*x*/
    }

    /*x FragmentsChars.21 */
    public static final char DONE = '\uFFFF';
    /*x*/


    // String.CASE_INSENSITIVE_ORDER 
    /*x FragmentsChars.22 */
    public static final Comparator<String> CASE_INSENSITIVE_ORDER
        = new CaseInsensitiveComparator();
    private static class CaseInsensitiveComparator
        implements Comparator<String>, java.io.Serializable {
        // use serialVersionUID from JDK 1.2.2 for interoperability
        private static final long serialVersionUID 
            = 8575799808933029326L;

        public int compare(String s1, String s2) {
            int n1 = s1.length();
            int n2 = s2.length();
            int min = Math.min(n1, n2);
            for (int i = 0; i < min; i++) {
                char c1 = s1.charAt(i);
                char c2 = s2.charAt(i);
                if (c1 != c2) {
                    c1 = Character.toUpperCase(c1);
                    c2 = Character.toUpperCase(c2);
                    if (c1 != c2) {
                        c1 = Character.toLowerCase(c1);
                        c2 = Character.toLowerCase(c2);
                        if (c1 != c2) {
                            // No overflow
                            // because of numeric promotion
                            return c1 - c2;
                        }
                    }
                }
            }
            return n1 - n2;
        }
    }
    /*x*/

    void frag23() {
        /*x FragmentsChars.23 */
        String pattern = "EEEEE, MMMMM dd, yyyy";
        SimpleDateFormat format 
            = new SimpleDateFormat(pattern,Locale.ENGLISH);
        Date date = format.parse("Sunday, February 29, 2032",
                                 new ParsePosition(0));
        /*x*/
    }

    void frag24() {
        /*x FragmentsChars.24 */
        Date now = new Date();
        DateFormat df 
            = DateFormat.getDateTimeInstance(DateFormat.FULL,
                                             DateFormat.FULL);
        System.out.println("time rounded, default TZ:\n" 
                           + df.format(now));
        df.setTimeZone(TimeZone.getTimeZone("GMT"));
        System.out.println("time rounded, GMT:\n" 
                           + df.format(now));
        /*x*/
    }


 
}
