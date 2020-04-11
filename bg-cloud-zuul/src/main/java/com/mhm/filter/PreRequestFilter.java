package com.mhm.filter;

import com.alibaba.fastjson.JSONObject;
import com.mhm.entity.ResultCode;
import com.mhm.entity.ResultCodeEnum;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

/**
 * 接口调用前的拦截器，可以限流、黑白名单过滤
 * Created by MHm on 2020-4-4.
 */
@Component
public class PreRequestFilter  extends ZuulFilter {

    @Value("#{'${auth.ignorePatterns}'.split(',')}")
    private List<String> ignorePatterns;
    @Value("${auth.onOff}")
    private boolean onOff;

    @Override
    public String filterType() {
        return FilterConstants.PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        // 判断当前请求的url是否跳过登录过滤器
        if (ignorePatterns != null && ignorePatterns.size() > 0) {
            for (String pattern : ignorePatterns) {
                if (Pattern.matches(pattern, path)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // 登录鉴权开关关闭，直接跳过
        if (!onOff) {
            return null;
        }
        RequestContext ctx = RequestContext.getCurrentContext();
        try {
            doSomething(ctx);
        } catch (Exception e) {
        }
        return null;
    }

    private void doSomething(RequestContext requestContext) throws IOException, ServletException {
        HttpServletRequest request = requestContext.getRequest();
        String path = request.getRequestURI().substring(request.getContextPath().length()).replaceAll("[/]+$", "");
        ResultCode result = new ResultCode();
        if (path.indexOf("/api/") > -1) {
            //从请求头中取出token
//            String token = request.getHeader("token");
//            //未携带token或token在黑名单内
//            if (token == null || token.isEmpty()) {
//                requestContext.setSendZuulResponse(false);//不需要进行路由，也就是不会调用api服务提供者
//                requestContext.setResponseStatusCode(200);
//                requestContext.set("isOK", false);//可以把一些值放到ctx中，便于后面的filter获取使用
//                //返回内容给客户端
//                result.setMessage(ResultCodeEnum.SYSTEMERROR.getCode(), ResultCodeEnum.SYSTEMERROR.getName());
//                requestContext.setResponseBody(JSONObject.toJSONString(result));// 返回错误内容
//                return;
//            }
            requestContext.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者
            requestContext.set("isOK", true);//可以把一些值放到ctx中，便于后面的filter获取使用
            return;
        }
        // 判断当前请求的url是否跳过登录过滤器
        if (ignorePatterns != null && ignorePatterns.size() > 0) {
            for (String pattern : ignorePatterns) {
                if (Pattern.matches(pattern, path)) {
                    return;
                }
            }
        }
        // 对token进行校验
        String userId = (String) request.getSession().getAttribute("userId");
        if (userId != null && !userId.isEmpty()) {
            requestContext.setSendZuulResponse(true);//会进行路由，也就是会调用api服务提供者
            requestContext.set("isOK", true);//可以把一些值放到ctx中，便于后面的filter获取使用
            return;
        }
        //验证失败
        requestContext.setSendZuulResponse(false);//不需要进行路由，也就是不会调用api服务提供者
        requestContext.setResponseStatusCode(500);
        requestContext.set("isOK", false);//可以把一些值放到ctx中，便于后面的filter获取使用
        //返回内容给客户端
        result.setMessage(ResultCodeEnum.SYSTEMERROR.getCode(), ResultCodeEnum.SYSTEMERROR.getName());
        requestContext.setResponseBody(JSONObject.toJSONString(result));// 返回错误内容
        return;
    }

}
