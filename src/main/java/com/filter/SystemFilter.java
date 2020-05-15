package com.filter;

import org.apache.log4j.Logger;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class SystemFilter implements Filter {
    private static Logger logger = Logger.getLogger(SystemFilter.class);
    protected FilterConfig filterConfig;
    static Map<String,String> validMap = new HashMap<String, String>();
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("init.....");
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("doFilter...");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = context.getServletContext();
        validMap.put("userLogin","/user/toLogin");
        validMap.put("success","/user/login");
        if(validPass(request.getRequestURI())){
            filterChain.doFilter(servletRequest,servletResponse);
            return;
        }
        String[] arr1 = (String[]) servletContext.getAttribute("arr");
        String[] arr2 = (String[]) request.getSession().getAttribute("urls");
        if(inAuth(request.getRequestURI(),arr1)){
            if(request.getSession().getAttribute("urls") == null){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                String url = request.getRequestURI().toString();
                String f = url.substring(0,url.indexOf(request.getContextPath()));
                String p = f + request.getContextPath()+"/user/toLogin";
                response.sendRedirect(p);
                return;
            }else if(request.getSession().getAttribute("urls") != null && inAuth(request.getRequestURI(),arr2)){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }else {
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                String url = request.getRequestURI().toString();
                String f = url.substring(0,url.indexOf(request.getContextPath()));
                String p = f + request.getContextPath()+"/user/toFailed";
                response.sendRedirect(p);
                return;
            }
        }else {
            if(request.getServletPath().startsWith("/user") && request.getSession().getAttribute("urls") == null){
                HttpServletResponse response = (HttpServletResponse) servletResponse;
                String url = request.getRequestURI().toString();
                String f = url.substring(0,url.indexOf(request.getContextPath()));
                String p = f + request.getContextPath()+"/user/toLogin";
                response.sendRedirect(p);
                return;
            }
            filterChain.doFilter(servletRequest,servletResponse);


        }
    }

    @Override
    public void destroy() {
        logger.info("destroy.....");

    }
    private boolean validPass(String path){
        for(Iterator<Map.Entry<String,String>> it = validMap.entrySet().iterator();it.hasNext();){
            Map.Entry<String,String> entry = it.next();
            if(path.indexOf(entry.getValue())!= -1){
                return true;
            }
        }
        return false;
    }
    private boolean inAuth(String path,String[] arrs) {
        for (String url: arrs){
            if (path.equals(url)){
                return true;
            }
        }
        return false;
    }
}
