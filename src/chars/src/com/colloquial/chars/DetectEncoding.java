package com.colloquial.chars;

import com.ibm.icu.text.CharsetDetector;
import com.ibm.icu.text.CharsetMatch;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import java.util.Arrays;

public class DetectEncoding {

    public static void main(String[] args) 
        throws IOException, UnsupportedEncodingException {

        /*x DetectEncoding.1 */
        String[] detectableCharsets
            = CharsetDetector.getAllDetectableCharsets();
        /*x*/
        System.out.println("Detectable Charsets=" + Arrays.asList(detectableCharsets));

        /*x DetectEncoding.2 */
        String declared = "ISO-8859-1";
        String s = "D\u00E9j\u00E0 vu.";
        String[] encodings = { "UTF-8", "UTF-16", "ISO-8859-1" };
        for (String encoding : encodings) {
            byte[] bs = s.getBytes(encoding);
            CharsetDetector detector = new CharsetDetector();
            detector.setDeclaredEncoding(declared);
            detector.setText(bs);
            CharsetMatch[] matches = detector.detectAll();
        /*x*/
            System.out.printf("\nencoding=%s # matches=%d\n",encoding,matches.length);
            /*x DetectEncoding.3 */
            for (CharsetMatch match : matches) {
                String name = match.getName();
                int conf = match.getConfidence();
                String lang = match.getLanguage();
                String text = match.getString();
                /*x*/
                System.out.printf("     guess=%s conf=%d lang=%s\n         chars=%s\n",
                                  name,conf,lang,toChars(text));
            }
        }
    }

    static String toChars(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); ++i) {
            sb.append(' ');
            sb.append(Integer.toHexString(text.charAt(i)));
        }
        return sb.toString();
    }

}