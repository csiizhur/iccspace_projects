package com.icc.icc_wechat_spider.work;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jsoup.Jsoup;

import com.github.luohaha.jlitespider.core.MessageQueue;
import com.github.luohaha.jlitespider.core.Processor;

import us.codecraft.xsoup.Xsoup;

//解析页面数据，将结果放入main消息队列。同时，后面页面的url信息同样需要放入队列，以便迭代抓取。
public class DoubanProcessor implements Processor {
//url去重复
    private Set<String> urlset = new HashSet<>();
    @Override
    public void process(Object page, Map<String, MessageQueue> mQueue) throws IOException {
        // TODO Auto-generated method stub
        String path = "//[@id=content]/div/div[1]/div[2]/table/tbody/tr/td[1]/a/@title";
        List<String> result = Xsoup.compile(path).evaluate(Jsoup.parse(page.toString())).list();
        //将结果放入main消息队列
        mQueue.get("main").sendResult(result);
        path = "//[@id=content]/div/div[1]/div[3]/a/@href";
        List<String> url = Xsoup.compile(path).evaluate(Jsoup.parse(page.toString())).list();
        for (String each : url) {
            if (!urlset.contains(each)) {
            //如果url之前并未抓取过，则加入main队列，作为接下来要抓取的url
                mQueue.get("main").sendUrl(each);
                urlset.add(each);
            }
        }
    }

}