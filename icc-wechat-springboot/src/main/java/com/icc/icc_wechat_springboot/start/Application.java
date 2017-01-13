package com.icc.icc_wechat_springboot.start;

import javax.servlet.Filter;
import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.ErrorPage;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

//@EnableWebMvc
//@Configuration
//@EnableAutoConfiguration
@ComponentScan("com.icc.icc_wechat_springboot.controller")
@SpringBootApplication
public class Application extends WebMvcConfigurerAdapter {

    // dataSource这里使用的是Hikari,你也可以使用其他的
    /*@Bean
    public DataSource dataSource() {
        HikariConfig config = new HikariConfig(getClass().getClassLoader().getResource("db.properties").getPath());
        return new HikariDataSource(config);
    }*/

    // 用于处理编码问题
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
    /**
     * 直接设置TomcatEmbeddedServletContainerFactory的相应属性值也可以修改tomcat端口
     * @return
     */
    @Bean
    public EmbeddedServletContainerFactory servletContainer(){
    	TomcatEmbeddedServletContainerFactory tomcatFac=new TomcatEmbeddedServletContainerFactory();
    	tomcatFac.setPort(8088);
    	tomcatFac.setSessionTimeout(10);
    	tomcatFac.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND,"/notfound.html"));
    	return tomcatFac;
    }
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

}
