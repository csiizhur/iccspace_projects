package com.icc.icc_wechat_spider.work;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import com.github.luohaha.jlitespider.core.Downloader;
import com.github.luohaha.jlitespider.core.MessageQueue;
import com.github.luohaha.jlitespider.extension.Network;

//下载页面数据，并存入main队列。
public class DoubanDownloader implements Downloader {
    private Logger logger = Logger.getLogger("DoubanDownloader");
    @Override
    public void download(Object url, Map<String, MessageQueue> mQueue) throws IOException {
        // TODO Auto-generated method stub
        String result = "";
        try {
            result = Network.create()
                            .setUserAgent("...")
                            .setCookie("...")
                            .downloader(url.toString());
        } catch (IOException e) {
            logger.info("本次下载失败！重新下载！");
            //因为下载失败，所以将url重新放入main队列中
            mQueue.get("main").sendUrl(url);
        }
        //下载成功，将页面数据放入main消息队列
        mQueue.get("main").sendPage(result);
    }

}