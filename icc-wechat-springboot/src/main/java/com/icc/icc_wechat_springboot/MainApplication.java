package com.icc.icc_wechat_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;

/**
 * Hello world!
 *
 */
@SpringBootApplication
public class MainApplication implements EmbeddedServletContainerCustomizer
{
    /*public static void main( String[] args )
    {
        SpringApplication.run(MainApplication.class, args);
    }*/

    /**
     * EmbeddedServletContainerCustomizer接口来修改8080接口
     */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		container.setPort(8088);	
	}
}
