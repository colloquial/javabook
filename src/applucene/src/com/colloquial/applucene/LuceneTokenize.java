package com.colloquial.applucene;

import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;

import org.apache.lucene.analysis.en.PorterStemFilter;

import org.apache.lucene.analysis.standard.StandardTokenizer;

import org.apache.lucene.analysis.util.CharArraySet;

import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.analysis.tokenattributes.OffsetAttribute;
import org.apache.lucene.analysis.tokenattributes.PositionIncrementAttribute;

import org.apache.lucene.util.Version;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import java.util.ArrayList;

public class LuceneTokenize {

    private LuceneTokenize() { }

    public static Version VERSION = Version.LUCENE_45;

    /*x LuceneTokenize.3 */
    public static ArrayList<String> STOPLIST;
    static {
        STOPLIST = new ArrayList<String>();
        STOPLIST.add("a");
        STOPLIST.add("of");
        STOPLIST.add("the");
        STOPLIST.add("to");
    }
    public static CharArraySet STOPWORDS
            = new CharArraySet(VERSION,STOPLIST,true);
    /*x*/

    public static void main(String[] args) throws IOException {
        String text = args[0];
        System.out.println(textPositions(text));

        /*x LuceneTokenize.1 */
        Reader textReader = new StringReader(text);
        StandardTokenizer standardTokenizer
            = new StandardTokenizer(VERSION,textReader);
        LowerCaseFilter lowercaseFilter 
            = new LowerCaseFilter(VERSION,standardTokenizer);
        StopFilter stopFilter 
            = new StopFilter(VERSION,lowercaseFilter,STOPWORDS);
        PorterStemFilter stemFilter
            = new PorterStemFilter(stopFilter);
        /*x*/

        System.out.printf("\n%s %s (%s, %s)\n","TERM","POSITION", "START","END");
        try {
            /*x LuceneTokenize.2 */
            stemFilter.reset();
            CharTermAttribute terms = 
                stemFilter.addAttribute(CharTermAttribute.class);
            OffsetAttribute offsets 
                = stemFilter.addAttribute(OffsetAttribute.class);
            PositionIncrementAttribute positions
                = stemFilter
                .addAttribute(PositionIncrementAttribute.class);
            int position = 0;
            while (stemFilter.incrementToken()) {
                String term = terms.toString();
                position += positions.getPositionIncrement();
                int start = offsets.startOffset();
                int end = offsets.endOffset();
                System.out.printf("%s \t%d (%2d, %2d)\n",
                                  term,position,start,end);
            }
            stemFilter.end();
            /*x*/
        } finally {
            stemFilter.close();
            textReader.close();
        }
    }

   public static String textPositions(CharSequence in) {
        StringBuilder sb = new StringBuilder();
        sb.append(in);
        for (int base = 1; base <= in.length(); base *= 10) {
            sb.append('\n');
            for (int i = 0; i < in.length(); ++i)
                sb.append(i % base == 0 
                          ? Integer.toString((i/base)%10)
                          : " ");
        }
        return sb.toString();
    }


}