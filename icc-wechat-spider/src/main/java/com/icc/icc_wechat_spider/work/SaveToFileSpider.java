package com.icc.icc_wechat_spider.work;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.github.luohaha.jlitespider.core.Spider;
import com.github.luohaha.jlitespider.exception.SpiderSettingFileException;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;

//第二个worker的启动主程序
public class SaveToFileSpider {
    public static void main(String[] args) {
        try {
            Spider.create().setFreeman(new SaveToFile())
                           .setSettingFile("./conf/setting2.json")
                           .begin();
        } catch (ShutdownSignalException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ConsumerCancelledException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TimeoutException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SpiderSettingFileException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}