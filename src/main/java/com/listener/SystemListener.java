package com.listener;

import com.entity.Permission;
import com.service.PermissionService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SystemListener implements InitializingBean, ServletContextAware {
    private static Logger logger = Logger.getLogger(SystemListener.class);
    @Resource
    private PermissionService permissionService;
    @Override
    public void afterPropertiesSet() throws Exception {

    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        List<Permission> list = permissionService.getAllPermission();
        int i = 0;
        Map<Integer,String> map = new HashMap<Integer,String>();
        for(Permission permission:list){
            map.put(i++,permission.getUrl());
        }
        servletContext.setAttribute("map",map);
        logger.info("把map放到了session中");
    }
}
