package com.icc.start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 
 * @description 项目启动初始化servlet
 * @author zhur
 * @date 2016年9月14日下午5:57:36
 */
public class InterfaceUrlInitServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		InterfaceUrlInit.init();
	}

}