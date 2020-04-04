package com.mhm.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Enumeration;

/**
 * web请求参数拦截
 * Created by MaHuiming on 2020-4-4.
 */
@Component
public class WebLogHandler implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(WebLogHandler.class);

    /**
     * 请求开始时间
     */
    ThreadLocal<Long> stThreadLocal = new ThreadLocal<Long>();
    /**
     * 处理入参信息
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
    throws Exception {
        long st = System.currentTimeMillis();
        stThreadLocal.set(st);

        // 接收到请求，记录请求内容
        StringBuilder sb = new StringBuilder();

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        sb.append("本次请求信息如下:"+ File.pathSeparator);
        sb.append("RequestId:" + st +  File.pathSeparator);
        sb.append("RequestURL:" + request.getRequestURL().toString() + File.pathSeparator);
        sb.append("Method:" + handlerMethod.getBean().getClass().getName() + "." + handlerMethod.getMethod().getName() +  File.pathSeparator);
        //获取所有请求参数：
        Enumeration<String> enu = request.getParameterNames();
        sb.append("RequestParams:[");
        while (enu.hasMoreElements()) {
            String paraName = enu.nextElement();
            sb.append(paraName + ": " + request.getParameter(paraName));
            if (enu.hasMoreElements()) {
                sb.append(",");
            }
        }
        sb.append("]");

        logger.info(sb.toString());
        return false;
    }

    /**
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
    @Nullable ModelAndView modelAndView) throws Exception {

    }

    /**
     * 处理出参信息
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
    @Nullable Exception ex) throws Exception {
        long st = stThreadLocal.get();
        long speedTime = System.currentTimeMillis() - st;
        logger.info("RequestId:{}, 本次请求耗时SpeedTime:{}ms ", st, speedTime);
    }
}
