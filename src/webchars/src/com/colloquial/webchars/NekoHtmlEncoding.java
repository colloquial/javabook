package com.colloquial.webchars;

import java.io.IOException;
import java.io.PrintStream;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class NekoHtmlEncoding {
    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out,true,"UTF-8"));

        DOMParser parser = new DOMParser();
        if (args.length >1 && !("".equals(args[1]))) {
            /*x NekoHtmlEncoding.1 */
            String encoding = args[1];
            parser.setFeature("http://cyberneko.org/html/features/scanner/"
                              +"ignore-specified-charset", 
                              true);
            parser.setProperty("http://cyberneko.org/html/properties/"
                               +"default-encoding", 
                               encoding);
            /*x*/
            System.out.println("Using encoding: " + encoding);
        }
        parser.parse(args[0]);
        NekoHtmlParse.printTextNodes(parser.getDocument());
    }
}
