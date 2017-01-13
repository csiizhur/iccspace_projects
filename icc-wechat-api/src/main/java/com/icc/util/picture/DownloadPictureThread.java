package com.icc.util.picture;

import java.util.ArrayList;

public class DownloadPictureThread implements Runnable {

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			System.out.println("运行  :  " + i);
			DownloadPicture downloadPicture = new DownloadPicture();
			ArrayList<DownloadPictureDto> urlList = downloadPicture.readUrlList();
			downloadPicture.downloadPicture(urlList);
			try {
				Thread.sleep((int) Math.random() * 10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}
}
