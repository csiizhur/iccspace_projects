package com.icc.icc_wechat_spider.work;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.github.luohaha.jlitespider.core.MessageQueue;
import com.github.luohaha.jlitespider.core.Saver;

//把最终的数据放入data消息队列
public class DoubanSaver implements Saver {

    @Override
    public void save(Object result, Map<String, MessageQueue> mQueue) throws IOException {
        // TODO Auto-generated method stub
        List<String> rList = (List<String>) result;
        for (String each : rList) {
        //把数据发往data消息队列
            mQueue.get("data").send("cc", each);
        }
    }

}