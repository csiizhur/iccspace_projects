package com.icc.aliyun.opensearch;

import org.junit.Test;

import com.aliyun.opensearch.CloudsearchSearch;

/**
 * Opensearch Client
 * @description
 * @author zhurun
 * @date 2016年11月8日下午2:37:36
 */
public class OpenSearchClientQuery {

	//应用名称

    public static final String  INDEXNAME = "opensearch_iccspace";

    //分页条数

    public static final int PAGESIZE = 10;
    
	private Integer pageNo;
	private String content;
	@Test
	public void queryOpenSearch() throws Exception{

        CloudsearchSearch search = new CloudsearchSearch(OpensearchUtil.getInstance());

        // 添加指定搜索的应用：

        search.addIndex(INDEXNAME);    

        // 索引字段名称是您在您的数据结构中的“索引到”字段。

        //search.setQueryString(content);
        search.setQueryString("'搞网站运营已有6年时间了'");
        search.setQueryString("default:'搞网站运营已有6年时间了'");
        //search.setQueryString("index_name:'搞网站运营已有6年时间了'");

        //参数一 要飘红的字段 、参数二 数据截取字数100、参数三 多余部分...显示、参数四飘红显示几段 、参数五六为html标签可以自定义

        //search.addSummary("content", 200, "......", 3, "<font color='red'>", "</font>");

        //分页查询

        //search.setStartHit((pageNo-1)*PAGESIZE > 0 ? (pageNo-1)*PAGESIZE : 0);

        //每页显示10条

        //search.setHits(PAGESIZE);

        //指定搜索返回的格式为json

        search.setFormat("json");

        //返回搜索结果。

        System.err.println(search.search());

    }
	
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
