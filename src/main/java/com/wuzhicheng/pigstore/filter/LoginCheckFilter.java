package com.wuzhicheng.pigstore.filter;

import com.alibaba.fastjson.JSON;
import com.wuzhicheng.pigstore.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author wuzhicheng
 * @create 2022-07-03 10:45
 * 检查用户是否登录的过滤器
 */
//使用注解声明过滤器，所有请求都拦截
@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    //支持通配符的路径匹配器
    public static final AntPathMatcher PATH_MATCHER=new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        HttpServletResponse resp= (HttpServletResponse) servletResponse;
        //获取本次请求的URI
        String requestURI = req.getRequestURI();
        //定义不需要被拦截的url
        String[] urls=new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",      //后台的静态资源
                "/front/**"         //前台的静态资源
        };
        //判断本次请求是否需要处理
        boolean check = check(urls, requestURI);
        //不需要处理
        if(check){
            filterChain.doFilter(req,resp);
            return;
        }
        //判断是否登录，从而判断是否放行
        if(req.getSession().getAttribute("employee")!=null){
            filterChain.doFilter(req,resp);
            return;
        }
        resp.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;
    }

    /**
     * 判断本次请求是否需要处理
     * @param url
     * @param urls 不需要处理的url
     * @return
     */
    public boolean check(String[] urls, String url){
        for(String u:urls){
            boolean match = PATH_MATCHER.match(u, url);
            if(match) return true;
        }
        return false;
    }
}
