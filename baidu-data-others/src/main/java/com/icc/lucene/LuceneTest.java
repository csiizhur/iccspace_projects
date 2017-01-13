package com.icc.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;

/**
 * 基于Lucene自身的分词
 * @description
 * @author zhurun
 * @date 2016年10月27日上午9:19:18
 */
public class LuceneTest {

	@Test
    public void cutWords() throws IOException {
//        StandardAnalyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
//        CJKAnalyzer analyzer = new CJKAnalyzer(Version.LUCENE_46);
        SimpleAnalyzer analyzer = new SimpleAnalyzer(Version.LUCENE_46);
        String text = "Spark是当前最流行的开源大数据内存计算框架，采用Scala语言实现，由UC伯克利大学AMPLab实验室开发并于2010年开源。";
        TokenStream tokenStream = analyzer.tokenStream("content", new StringReader(text));
        CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
        try {
            tokenStream.reset();
            while (tokenStream.incrementToken()) {
                System.out.println(charTermAttribute.toString());
            }
            tokenStream.end();
        } finally {
            tokenStream.close();
            analyzer.close();
        }
    }
}
