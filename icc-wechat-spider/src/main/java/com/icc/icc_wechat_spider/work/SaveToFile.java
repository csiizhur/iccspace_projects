package com.icc.icc_wechat_spider.work;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import com.github.luohaha.jlitespider.core.Freeman;
import com.github.luohaha.jlitespider.core.MessageQueue;


//接收data消息队列中的数据，写入txt
public class SaveToFile implements Freeman {
    @Override
    public void doSomeThing(String key, Object msg, Map<String, MessageQueue> mQueue) throws IOException {
        // TODO Auto-generated method stub
        File file = new File("F:/company/down/douban.txt");
        FileWriter fileWriter = new FileWriter(file, true);
        fileWriter.write(msg.toString() + "\n");
        fileWriter.flush();
        fileWriter.close();
    }
}