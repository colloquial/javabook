package com.colloquial.applucene;


import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.document.Document;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.DirectoryReader;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;

import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class LuceneSearch {

    /*x LuceneSearch.1 */
    public static void main(String[] args) 
        throws ParseException, CorruptIndexException,
               IOException {

        File indexDir = new File(args[0]);
        String query = args[1];
        int maxHits = Integer.parseInt(args[2]);
    /*x*/        

        System.out.println("index=" + indexDir.getName());
        System.out.println("query=" + query);
        System.out.println("max hits=" + maxHits);
        
        /*x LuceneSearch.2 */
        Directory fsDir = FSDirectory.open(indexDir);
        DirectoryReader reader = DirectoryReader.open(fsDir);
        IndexSearcher searcher = new IndexSearcher(reader);
        Analyzer stdAn 
            = new StandardAnalyzer(Version.LUCENE_45);
        QueryParser parser 
            = new QueryParser(Version.LUCENE_45,"text",stdAn);
        Query q= parser.parse(query);
        System.out.println("parsed query=" + q.toString());
        /*x*/
        /*x LuceneSearch.3 */
        TopDocs hits = searcher.search(q,maxHits);
        ScoreDoc[] scoreDocs = hits.scoreDocs;
        System.out.println("hits=" + scoreDocs.length);
        System.out.println("Hits (rank,score,paper)");
        for (int n = 0; n < scoreDocs.length; ++n) {
            ScoreDoc sd = scoreDocs[n];
            float score = sd.score;
            int docId = sd.doc;
            Document d = searcher.doc(docId);
            String number = d.get("number");
         /*x*/
            System.out.printf("%3d %4.2f  %s\n",
                              n, score, number);
        }
        reader.close();
    }

}