package com.filter;

import org.apache.log4j.Logger;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LoginFilter implements Filter {
    private static Logger logger = Logger.getLogger(LoginFilter.class);
    protected FilterConfig filterConfig;
    static Map<Integer, String> canMap = new HashMap<Integer, String>();
    static Map<Integer, String> mapTest = new HashMap<Integer, String>();
    static Map<Integer, String> authMap = new HashMap<Integer, String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init....");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter...");
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        WebApplicationContext webApplicationContext =
                org.springframework.web.context.ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        mapTest = (Map<Integer, String>) servletContext.getAttribute("map");
        authMap = (Map<Integer, String>) req.getSession().getAttribute("urls");
        canMap.put(123456, "/user/toLogin");
        canMap.put(1234567, "/user/login");
        if (canPass(req.getRequestURI(), canMap)) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        if (canPass(req.getRequestURI(), mapTest)) {
            if (req.getSession().getAttribute("urls") == null) {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                String url = req.getRequestURL().toString();
                String f = url.substring(0, url.indexOf(req.getContextPath()));
                String p = f + req.getContextPath() + "/user/toLogin";
                res.sendRedirect(p);
                return;
            } else if (req.getSession().getAttribute("urls") != null && canPass(req.getRequestURI(), authMap)) {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            } else {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                String url = req.getRequestURL().toString();
                String f = url.substring(0, url.indexOf(req.getContextPath()));
                String p = f + req.getContextPath() + "/user/toFailed";
                res.sendRedirect(p);
                return;
            }
        } else {
            if (req.getServletPath().startsWith("/user") && req.getSession().getAttribute("urls") == null) {
                HttpServletResponse res = (HttpServletResponse) servletResponse;
                String url = req.getRequestURL().toString();
                String f = url.substring(0, url.indexOf(req.getContextPath()));
                String p = f + req.getContextPath() + "/user/toLogin";
                res.sendRedirect(p);
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        logger.info("destroy...");
    }
    private boolean canPass(String servletPath,Map<Integer,String> map) {
        for (Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator(); it.hasNext(); ) {
            Map.Entry<Integer, String> entry = it.next();
            if (servletPath.indexOf(entry.getValue()) != -1) {
                return true;
            }
        }
        return false;
    }
}