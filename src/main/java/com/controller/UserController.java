package com.controller;

import com.dao.JsonData;
import com.entity.User;
import com.entity.UserRolePermission;
import com.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/")
public class UserController {
    @Resource
    private UserService userService;
    @RequestMapping(value = "/tolist",method =  RequestMethod.GET)
    @ResponseBody
    public JsonData AllUrls(){
        List<UserRolePermission> list = userService.selectUrl("user1");
        return new JsonData(list);
    }
    @RequestMapping(value = "/list",method =  RequestMethod.GET)
    @ResponseBody
    public JsonData showAllUrls(HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user.getUsername().equals("admin")){
            List<UserRolePermission> list = userService.selectAllUrl();
            return new JsonData(list);
        }else{
            List<UserRolePermission> list = userService.selectUrl(user.getUsername());
            return new JsonData(list);
        }
    }
    @RequestMapping(value="/toLogin")
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView("login");
        return modelAndView;
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    @ResponseBody
    public JsonData login(HttpServletRequest request, HttpSession session){
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");

        int i=0;
        User users = userService.selectUserByName(userName);
        if (users == null || !users.getPassword().equals(password)) {
            System.out.println("密码错误");
            return null;
        }
        session.setAttribute("user", users);
        List<UserRolePermission> urls = userService.selectUrl(userName);
        for(UserRolePermission user:urls){
            System.out.println(user);
        }
        Map<Integer,String> umap = new HashMap<Integer,String>();
        for(UserRolePermission user:urls){
            umap.put(i++,user.getUrl());
        }
        session.setAttribute("urls", umap);
        return new JsonData();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public JsonData logout(HttpSession session) {
        session.setAttribute("user", null);
        return new JsonData();
    }

    @RequestMapping(value="/toAdd",method = RequestMethod.GET)
    public ModelAndView toAddUser(){
        ModelAndView modelAndView = new ModelAndView("list");
        return modelAndView;
    }

    @RequestMapping(value="/toUpdate")
    public ModelAndView toUpdate(){
        ModelAndView modelAndView = new ModelAndView("list");
        return modelAndView;
    }

    @RequestMapping(value="/toDel")
    public ModelAndView toDel(){
        ModelAndView modelAndView = new ModelAndView("list");
        return modelAndView;
    }

    @RequestMapping(value="/toSelect", method = RequestMethod.GET)
    public ModelAndView toFind(){
        ModelAndView modelAndView = new ModelAndView("list");
        return modelAndView;
    }

    @RequestMapping(value="/toFailed")
    public ModelAndView toFailed(){
        ModelAndView modelAndView = new ModelAndView("failed");
        return modelAndView;
    }
}
