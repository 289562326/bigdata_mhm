package com.mhm.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.apache.catalina.filters.RequestFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.util.StreamUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import static org.aspectj.weaver.tools.cache.SimpleCacheFactory.path;

/**
 * 拦截器，可以记录日志、API统计、接口权限等
 * Created by MaHuiming on 2020-4-4.
 */
@Component
public class PostFilter extends ZuulFilter {
    private static final Logger logger = LoggerFactory.getLogger(RequestFilter.class);
    public static final String REDIS_API_INFO = "api_info";


    @Override
    public String filterType() {
        return FilterConstants.POST_TYPE;//要打印返回信息，必须得用"post"
    }

    @Override
    public int filterOrder() {
        return FilterConstants.PRE_DECORATION_FILTER_ORDER + 2;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext context = RequestContext.getCurrentContext();
        Boolean isSuccess = true;//(boolean) context.get("isSuccess");
        return isSuccess;
    }

    @Override
    public Object run() throws ZuulException {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpServletResponse response = ctx.getResponse();
        InputStream in = null;
        try {
            in = request.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String method = request.getMethod();
        String interfaceMethod = request.getServletPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
        logger.debug("请求方法method={},url={}", method, interfaceMethod);
        String reqBody = null;
        try {
            reqBody = StreamUtils.copyToString(in, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("reqBody{}", e);
        }

        // 打印response
        InputStream out = ctx.getResponseDataStream();
        String outBody = null;
        try {
            outBody = StreamUtils.copyToString(out, Charset.forName("UTF-8"));
        } catch (IOException e) {
            logger.error("outBody{}", e);
        }
        if (null != ctx.getResponseBody()) {
            outBody = ctx.getResponseBody();
        }
        int status = response.getStatus();

        logger.debug("响应参数{}:={}" ,status,outBody);
        ctx.getResponse().setContentType("application/json;charset=UTF-8");
        ctx.setResponseBody(outBody);
        return null;
    }
}
