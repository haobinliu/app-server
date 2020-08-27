package com.example.appserver.Interceptor;

import com.example.appserver.common.TokenManager;
import com.google.gson.Gson;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Logger;

/**
 * @Author: 13005
 * @DATE: 2020/8/27 14:38
 */
public class LoginInterceptor implements HandlerInterceptor {

    private static final Logger logger = Logger.getLogger(LoginInterceptor.class.getName());

    private static final String TOKEN = "token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler) throws  Exception{
        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        //从请求头中获取token，进行解析
        String token = request.getHeader((TOKEN));
        Boolean hsaToken = TokenManager.checkToken(token);

        if(hsaToken == false){
            logger.info("token不存在或者token已过期，请重新登录");
            reSetResponse(response);
            return false;
        }
        logger.info("token校验通过，放行");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

    @Override
    public void afterCompletion(HttpServletRequest request,HttpServletResponse response,Object handler,Exception ex){};

    public void reSetResponse(HttpServletResponse response) throws IOException{
        response.reset();
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter printWriter = response.getWriter();
        printWriter.write(new Gson().toJson(printWriter));
        printWriter.flush();
        printWriter.close();
    }
}
