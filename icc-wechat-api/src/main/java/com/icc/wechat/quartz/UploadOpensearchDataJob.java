package com.icc.wechat.quartz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.icc.aliyun.opensearch.OpensearchTask;
/**
 * 
 * @description
 * @author zhurun
 * @date 2016年11月9日下午5:13:22
 */
//@Component
public class UploadOpensearchDataJob {

protected final Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private OpensearchTask opensearchTask;
	public void uploadDataByJob(){
		
		try{
			//OpensearchTask timer=new OpensearchTask();
			opensearchTask.updateDataJSON();
			logger.info("opensearch 数据更新完成");;
		}catch(Exception e){
			
		}
	}
}
