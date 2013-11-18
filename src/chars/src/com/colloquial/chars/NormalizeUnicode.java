package com.colloquial.chars;

import com.ibm.icu.text.Normalizer2;
import com.ibm.icu.text.Normalizer2.Mode;

public class NormalizeUnicode {

    public static void main(String[] args) {
        /*x NormalizeUnicode.1 */
        Normalizer2 normalizer
            = Normalizer2.getInstance(null,"nfkc",Mode.COMPOSE);
        String s1 = "\u00E0"; // a composed with grave
        String s2 = "a\u0300"; // a followed by grave

        String n1 = normalizer.normalize(s1);
        String n2 = normalizer.normalize(s2);
        /*x*/
        dump("s1 (a composed with grave)",s1); 
        dump("s2 (a followed by grave)",s2); 
        dump("n1 (normalized s1)",n1); 
        dump("n2 (normalized s2)",n2);
         System.out.println();
        System.out.println("s1.equals(s2)=" + s1.equals(s2));
        System.out.println("s1.equals(n1)=" + s1.equals(n1));
        System.out.println("s2.equals(n2)=" + s2.equals(n2));
        System.out.println("n1.equals(n2)=" + n1.equals(n2));
    }

    static void dump(String msg, String s) {
        System.out.print(msg + " char values=");
        for (int i = 0; i < s.length(); ++i) {
            System.out.printf("%4h",s.charAt(i));
            if ((i + 1) < s.length())
                System.out.print(",");
        }
        System.out.println();
    }

}