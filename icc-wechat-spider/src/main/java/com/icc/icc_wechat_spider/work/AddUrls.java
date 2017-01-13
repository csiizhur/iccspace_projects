package com.icc.icc_wechat_spider.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.github.luohaha.jlitespider.core.MessageQueueAdder;

//把入口url放入main消息队列
public class AddUrls {
    public static void main(String[] args) {
        try {
            MessageQueueAdder.create("localhost", 5672, "main")
                             .addUrl("https://movie.douban.com/tag/%E7%88%B1%E6%83%85?start=0&type=T")
                             .close();
           /* MessageQueueAdder.create("localhost", 5672, "main2")
            .addUrl("http://www.iccspace.cn/common/self/myself.html")
            .close();*/
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}