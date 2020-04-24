package com.wlp;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.search.*;
import org.apache.lucene.store.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

//全局检索
public class LuenceFirst {


    @Test
    public void createIndex() throws Exception {
        //3.1．创建一个Director对象，指定索引库保存的位置
        //会把索引库保存到内存中
        // Directory directory = new RAMDirectory();
        Directory directory = FSDirectory.open(new File("D:\\LuceneIndex").toPath());
        //3.2. 基于Director对象创建一个IndexWriter对象
        IndexWriter indexWriter = new IndexWriter(directory,new IndexWriterConfig());
        //3.3. 读取磁盘上的文件，对应每个文件创建一个文档对象
        File dir = new File("D:\\LuceneIndex\\doc");
        File[] files = dir.listFiles();
        for(File f : files ){
            //取文件名
            String fileName = f.getName();
            //文件路径
            String path = f.getPath();
            //文件内容
            String content = FileUtils.readFileToString(f,"utf-8");
            //文件大小
            long size = FileUtils.sizeOf(f);

            //创建域Filed
            //参数1：域的名称 参数2：域的内容，参数3：是否存储
            Field fieldName = new TextField("name",fileName,Field.Store.YES);
            Field fieldPath = new TextField("path",path,Field.Store.YES);
            Field fieldContent = new TextField("content",content,Field.Store.YES);
            Field fieldSize = new TextField("size",String.valueOf(size),Field.Store.YES);
            //创建文档对象
            Document document = new Document();
            //3.4. 向文档对象中添加域
            document.add(fieldName);
            document.add(fieldPath);
            document.add(fieldContent);
            document.add(fieldSize);
            //3.5 把文档对象写入索引库
            indexWriter.addDocument(document);
        }
        //3.6． 关闭IndexWriter对象
        indexWriter.close();
    }

    @Test
    public void searchindex() throws IOException {
        //创建一个Director对象，指定索引库的位置
        Directory directory = FSDirectory.open(new File("D:\\LuceneIndex").toPath());
        //创建一个IndexReader对象
        IndexReader indexReader = DirectoryReader.open(directory);
        //创建一个IndexSearcher对象，构造方法中的参数indexReader对象
        IndexSearcher indexSearcher = new IndexSearcher(indexReader);
        //创建一个Query对象，TermQuery
//        Query query = new TermQuery(new Term("content","china"));
        Query query = new TermQuery(new Term("name","hello.txt"));
        //执行查询，得到一个TopDocs对象
        TopDocs topDocs = indexSearcher.search(query,5);
        //取查询结果的总记录数
        TotalHits totalHits = topDocs.totalHits;
        System.out.println("查询到的记录数是："+totalHits);
        //取文档列表
        ScoreDoc[] scoreDocs = topDocs.scoreDocs;
        //打印文档中的内容
        for (ScoreDoc doc : scoreDocs ) {
            //文档id
            int docId = doc.doc ;
            //根据文档id取文档对象
            Document doc1 = indexSearcher.doc(docId);
            System.out.println(doc1.get("name"));
            System.out.println(doc1.get("path"));
            System.out.println(doc1.get("size"));
            System.out.println(doc1.get("content"));
            System.out.println("-------------------------------------");
        }
        //关闭IndexReade对象
        indexReader.close();
    }

    /**
     * 查看分析器的分词效果
     * @throws IOException
     */
    @Test
    public void testTokenSteam() throws IOException {
        //1.创建一个Analyzer对象，StandardAnalyzer对象
        Analyzer analyzer = new StandardAnalyzer();
        //2.使用分析器对象的tokenSteam方法获得一个TokenSteam对象
        TokenStream tokenStream = analyzer.tokenStream("", "China and Russia are poised to further deepen their energy cooperation as their top leaders both pledged on Friday to improve business environment for energy firms' cooperation");
        //3.向TokenSteam对象设置一个引用，相当于数一个指针
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        //4.调用TokenStream对象的rest方法，如果不调用，抛异常
        tokenStream.reset();
        //5.使用while循环遍历TokenSteam对象
        while (tokenStream.incrementToken()){
            System.out.println(charTermAttribute.toString());
        }
        //6.关闭TokenSteam对象
        tokenStream.close();
    }

//    @Test
//    public void testTokenSteamChinese() throws IOException {
//        //1.创建一个Analyzer对象，StandardAnalyzer对象
//        Analyzer analyzer = new IKAnalyzer();
//        //2.使用分析器对象的tokenSteam方法获得一个TokenSteam对象
//        TokenStream tokenStream = analyzer.tokenStream("", "简介：英语（English）是印欧语系-日耳曼语族下的语言，由26个字母组成，英文字母渊源于拉丁字母，拉丁字母渊源于希腊字母，而希腊字母则是由腓尼基字母演变而来的。英语是国际指定的官方语言（作为母语），也是世界上使用最广泛的语言，英语包含约49万词，外加技术名词约30万个，是词汇最多的语言");
//        //3.向TokenSteam对象设置一个引用，相当于数一个指针
//        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
//        //4.调用TokenStream对象的rest方法，如果不调用，抛异常
//        tokenStream.reset();
//        //5.使用while循环遍历TokenSteam对象
//        while (tokenStream.incrementToken()){
//            System.out.println(charTermAttribute.toString());
//        }
//        //6.关闭TokenSteam对象
//        tokenStream.close();
//
//
//    }
}

