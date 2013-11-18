package com.colloquial.casestudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;

import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;

import org.apache.lucene.document.DateTools;
import org.apache.lucene.document.DateTools.Resolution;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.Field.Store;


import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;

import org.apache.lucene.index.FieldInfo.IndexOptions;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import org.apache.lucene.util.Version;

public class IndexPapers {

    /*x IndexPapers.1 */
    public static void main(String[] args) 
        throws CorruptIndexException, LockObtainFailedException,
               IOException {

        if (args.length < 2) {
            System.err.println("usage: IndexPapers "
                               + "<etext> <index>");
            System.exit(-1);
        }
        File etextFile = new File(args[0]);
        File indexDir = new File(args[1]);

        Directory fsDir = FSDirectory.open(indexDir);

        Analyzer stdAn 
            = new StandardAnalyzer(Version.LUCENE_42);
        IndexWriterConfig iwConf 
            = new IndexWriterConfig(Version.LUCENE_42,stdAn);

        iwConf.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        IndexWriter indexWriter
            = new IndexWriter(fsDir,iwConf);
    /*x*/
        /*x IndexPapers.2 */        
        FederalistParser parser = new FederalistParser();
        List<Paper> papers = parser.parsePapers(etextFile);
        for (Paper paper : papers) {
            Document d = new Document();
            d.add(new IntField("number",
                               paper.getNumber(),Store.YES));
            d.add(new TextField("title",
                                paper.getTitle(),Store.YES));
            d.add(new StringField("author",
                                  paper.getAuthor1(),Store.YES));
            if (paper.getAuthor2() != null) {
                d.add(new StringField("author",
                                      paper.getAuthor2(),Store.YES));
            }
            d.add(new StringField("pubName",
                                  paper.getPubName(),Store.YES));

            Date date = paper.getPubDate();
            long roundDate = DateTools.round(date.getTime(),
                                             Resolution.DAY);
            d.add(new LongField("pubDate",
                                roundDate,Store.YES));
            d.add(new TextField("text",
                                paper.getTitle(),Store.YES));
            for (String paragraph : paper.getParagraphs()) {
                d.add(new TextField("text",
                                    paragraph,Store.YES));
            }
            indexWriter.addDocument(d);
        }
        int numDocs = indexWriter.numDocs();
        indexWriter.commit();
        indexWriter.close();
        /*x*/
        System.out.println("index=" + indexDir.getName());
        System.out.println("num docs=" + numDocs);
    }
}
