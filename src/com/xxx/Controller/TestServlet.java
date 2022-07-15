package com.xxx.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TestServlet extends BaseServlet{

    protected void test(HttpServletRequest req, HttpServletResponse resp) {
        String scheme = req.getScheme(); // 协议
        String serverName = req.getServerName(); // 服务器ip地址
        int serverPort = req.getServerPort(); // 服务器端号
        String contextPath = req.getContextPath(); // 项目名称
        System.out.println(scheme);
        System.out.println(serverName);
        System.out.println(serverPort);
        System.out.println(contextPath);
        String basePath = scheme + serverName + serverPort + contextPath;
        System.out.println(basePath);
    }
}
