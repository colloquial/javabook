package com.colloquial.applucene;

import java.util.Arrays;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.TokenStream;

import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.LowerCaseFilter;
import org.apache.lucene.analysis.core.StopFilter;

import org.apache.lucene.analysis.miscellaneous.PerFieldAnalyzerWrapper;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardTokenizer;

import org.apache.lucene.analysis.util.CharArraySet;

import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;

import org.apache.lucene.index.Term;
import org.apache.lucene.index.FieldInfo.IndexOptions;

import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TermQuery;

import org.apache.lucene.search.BooleanClause.Occur;

import org.apache.lucene.util.BytesRef;
import org.apache.lucene.util.Version;

import java.io.Reader;

import java.util.HashMap;
import java.util.Map;

class FragmentsLucene {

    static final Version VERSION = Version.LUCENE_45;

    private FragmentsLucene() { /* no instances */ }

    void frag1() {
        
        /*x FragmentsLucene.1 */
        BooleanQuery bq1 = new BooleanQuery();
        bq1.add(new TermQuery(new Term("text","red")), Occur.MUST);
        bq1.add(new TermQuery(new Term("text","blue")), Occur.SHOULD);
        
        BooleanQuery bq2 = new BooleanQuery();
        bq2.add(new TermQuery(new Term("text","green")), Occur.SHOULD);
        bq2.add(bq1,Occur.MUST);
        /*x*/
    }


    void frag2() {
        /*x FragmentsLucene.2 */
        Analyzer analyzer = new Analyzer() {
            @Override protected TokenStreamComponents 
            createComponents(String fieldName, Reader reader) {
                Tokenizer source = 
                    new StandardTokenizer(VERSION,reader);
                TokenStream filter = 
                    new LowerCaseFilter(VERSION,source);
                return new TokenStreamComponents(source, filter);
        /*x*/
            }
        };
    }

    void frag3() {
        /*x FragmentsLucene.3 */
        FieldType myFieldType = new FieldType();
        myFieldType.setIndexed(true);
        myFieldType.setOmitNorms(true);
        myFieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS);
        myFieldType.setStored(false);
        myFieldType.setTokenized(true);
        myFieldType.freeze();
        Field myField = new Field("field name",
                                  "field value",
                                  myFieldType);
        /*x*/
    }


    void frag4() {
        /*x FragmentsLucene.4 */
        Map<String,Analyzer> fieldAnalyzerMap 
            = new HashMap<String,Analyzer>();
        fieldAnalyzerMap.put("id", new KeywordAnalyzer());
        PerFieldAnalyzerWrapper perFieldAnalyzer =
            new PerFieldAnalyzerWrapper(new StandardAnalyzer(VERSION), 
                                        fieldAnalyzerMap);
        /*x*/
        
    }


    /*x FragmentsLucene.5 */
    /** An unmodifiable set containing some common English words that are not usually useful
        for searching. */
    public static final CharArraySet ENGLISH_STOP_WORDS_SET;
    static {
        final List<String> stopWords = Arrays.asList(
                                                     "a", "an", "and", "are", "as", "at", "be", "but", "by",
                                                     "for", "if", "in", "into", "is", "it",
                                                     "no", "not", "of", "on", "or", "such",
                                                     "that", "the", "their", "then", "there", "these",
                                                     "they", "this", "to", "was", "will", "with"
                                                     );
        final CharArraySet stopSet = new CharArraySet(VERSION,
                                                      stopWords, false);
        ENGLISH_STOP_WORDS_SET = CharArraySet.unmodifiableSet(stopSet); 
        /*x*/


    }

    void frag6() {
        
        /*x FragmentsLucene.6 */
        BytesRef brI = new BytesRef("I");
        BytesRef brJ = new BytesRef("J");
        Query termRangeQuery
            = new TermRangeQuery("author",brI,brJ,true,true);

        Query numericRangeQuery
            = NumericRangeQuery.newIntRange("number",1,5,true,true);
        /*x*/
    }



}
