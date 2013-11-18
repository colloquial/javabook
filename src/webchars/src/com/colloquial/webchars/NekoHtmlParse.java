package com.colloquial.webchars;

import java.io.IOException;
import java.io.PrintStream;

import org.cyberneko.html.parsers.DOMParser;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

public class NekoHtmlParse {

    public static void main(String[] args) throws Exception {
        System.setOut(new PrintStream(System.out,true,"UTF-8"));
        System.out.println("input file: " + args[0]);
        /*x NekoHtmlParse.1 */
        DOMParser parser = new DOMParser();
        parser.parse(args[0]);
        printTextNodes(parser.getDocument());
        /*x*/
    }

    /*x NekoHtmlParse.2 */
    public static void printTextNodes(Node node) {
        if (Node.TEXT_NODE == node.getNodeType()
            && node.getTextContent().trim().length() > 0) {
            System.out.println(node.getParentNode().getNodeName());
            System.out.println("|" + node.getTextContent() + "|");
        }
        Node child = node.getFirstChild();
        while (child != null) {
            printTextNodes(child);
            child = child.getNextSibling();
        }
    /*x*/
    }
}
