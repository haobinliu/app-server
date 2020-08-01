package com.example.appserver.common.config;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author HX
 * @date 2019/11/6 10:04
 */
public class ContextFilter implements Filter {
    private final int time = 20*24*60*60;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setHeader("Access-Control-Allow-Origin", "*");
        resp.setHeader("Access-Control-Allow-Headers", "*");
        resp.setHeader("Access-Control-Allow-Methods","PUT,POST,GET,DELETE,OPTIONS");
        resp.setHeader("Access-Control-Max-Age", time+"");
        chain.doFilter(request, resp);
    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub
    }
}