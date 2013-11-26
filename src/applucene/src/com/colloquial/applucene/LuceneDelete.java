package com.colloquial.applucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.miscellaneous.LimitTokenCountAnalyzer;

import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;

import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;

import org.apache.lucene.util.Version;

import java.io.File;
import java.io.IOException;

public class LuceneDelete {

    private LuceneDelete() { /* no instances */ }

    public static void main(String[] args) 
        throws CorruptIndexException, IOException {

        File indexDir = new File(args[0]);
        String field = args[1];
        String token = args[2];

        System.out.println("index=" + indexDir.getName());
        System.out.println("field=" + field);
        System.out.println("token=" + token);

        /*x LuceneDelete.1 */
        Directory fsDir = FSDirectory.open(indexDir);
        Analyzer stdAn 
            = new StandardAnalyzer(Version.LUCENE_45);
        IndexWriterConfig iwConf 
            = new IndexWriterConfig(Version.LUCENE_45,stdAn);
        iwConf.setOpenMode(IndexWriterConfig.OpenMode.APPEND);

        IndexWriter indexWriter
            = new IndexWriter(fsDir,iwConf);
        int numDocsBefore = indexWriter.numDocs();
        /*x*/
        /*x LuceneDelete.2 */
        Term term = new Term(field,token);
        indexWriter.deleteDocuments(term);
        /*x*/
        /*x LuceneDelete.3 */
        int numDocsAfterDeleteBeforeCommit = indexWriter.numDocs();
        indexWriter.commit();
        indexWriter.close();

        IndexWriterConfig iwConf2 
            = new IndexWriterConfig(Version.LUCENE_45,stdAn);
        iwConf2.setOpenMode(IndexWriterConfig.OpenMode.APPEND);
        IndexWriter indexWriter2
            = new IndexWriter(fsDir,iwConf2);
        int numDocsAfter = indexWriter2.numDocs();
        indexWriter2.close();
        /*x*/

        System.out.println("num docs before delete=" + numDocsBefore);
        System.out.println("num docs after delete before commit=" + numDocsAfterDeleteBeforeCommit);
        System.out.println("num docs after commit=" + numDocsAfter);
    }

}