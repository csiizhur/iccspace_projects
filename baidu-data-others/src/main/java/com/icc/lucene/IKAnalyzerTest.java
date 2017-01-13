package com.icc.lucene;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.util.Version;
import org.junit.Test;

/**
 * 基于Lucene自身的分词
 * @description
 * @author zhurun
 * @date 2016年10月27日上午9:19:18
 */
public class IKAnalyzerTest {

	/**
	 * 常见分词器
	 */
	@Test
	public void testAnalyzer(){
		
		final String str = "利用Lucene 实现全文检索";
		Analyzer analyzer = null;
		
		analyzer = new StandardAnalyzer(Version.LUCENE_43); 	// 标准分词
		print(analyzer, str);
		//analyzer = new IKAnalyzer();							// 第三方中文分词
		//print(analyzer, str);
		analyzer = new WhitespaceAnalyzer(Version.LUCENE_43); 	// 空格分词
		print(analyzer, str);
		analyzer = new SimpleAnalyzer(Version.LUCENE_43); 		// 简单分词
		print(analyzer, str);
		analyzer = new CJKAnalyzer(Version.LUCENE_43);			// 二分法分词
		print(analyzer, str);
		analyzer = new KeywordAnalyzer();						// 关键字分词
		print(analyzer, str);
		analyzer = new StopAnalyzer(Version.LUCENE_43); 		//被忽略词分词器
		print(analyzer, str);

	}

	/**
	 * 该方法用于打印分词器及其分词结果
	 * @param analyzer 分词器
	 * @param str 需要分词的字符串
	 */
	public void print(Analyzer analyzer,String str){
		
		StringReader stringReader = new StringReader(str);
		try {
			TokenStream tokenStream = analyzer.tokenStream("", stringReader); 			// 分词
			tokenStream.reset();
			
			CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class); // 获取分词结果的CharTermAttribute
			System.out.println("分词技术:" + analyzer.getClass());
			while (tokenStream.incrementToken()) {
				System.out.print(term.toString() + "|");
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
