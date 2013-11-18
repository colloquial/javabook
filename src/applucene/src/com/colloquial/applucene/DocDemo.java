package com.colloquial.applucene;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.IndexableFieldType;

public class DocDemo {
    
    public static void main(String[] args) {
        /*x DocDemo.1 */
        Document doc = new Document();

        doc.add(new TextField("title", "The Federalist Papers",
                              Store.YES));
        doc.add(new IntField("etextId",1404,
                                Store.NO));
        doc.add(new StoredField("downloadUrl",
                         "www.gutenberg.org/ebooks/1404.txt.utf-8"));
        doc.add(new StringField("releaseDate","198807",
                                Store.YES));

        for (IndexableField f : doc.getFields()) {
            String name = f.name();
            String value = f.stringValue();
            IndexableFieldType fieldType = f.fieldType();
            boolean isIndexed = fieldType.indexed();
            boolean isStored = fieldType.stored();
            boolean isTokenized = fieldType.tokenized();
        /*x*/
            System.out.println("name=" + name
                               + "\n\tvalue=" + value + "\n\t"
                               + (isIndexed ? "indexed, " : "")
                               + (isTokenized ? "tokenized, " : "")
                               + (isStored ? "stored, " : ""));

        }
    }

}