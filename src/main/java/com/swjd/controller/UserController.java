package com.swjd.controller;

import com.swjd.bean.User;
import com.swjd.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Resource
    UserService userService;

    //去登录
    @RequestMapping("/toLogin")
    public String toLogin(Model model){
        User user=new User();
        model.addAttribute("user",user);
        return "login";
    }

    //做登录
    @RequestMapping("/doLogin")
    public String doLogin(User user, Model model, HttpSession session){
        User u=userService.login(user);
        if (u!=null){
            //账号密码正确
            if (u.getFlag().equals("1")){
                //登入成功把用户名存到session
                session.setAttribute("activeName",u.getUname());
                return "redirect:/CustomerController/tomain";
            }else {
                //账号是禁用状态
                model.addAttribute("user",user);
                model.addAttribute("error","该账号为禁用状态");
                return "login";
            }
        }else {
            //账号密码错误
            model.addAttribute("user",user);
            model.addAttribute("error","账号或密码错误");
            return "login";
        }
    }
    @RequestMapping("/tomain")
    public String tomain(){
        return "main";
    }
    @RequestMapping("/tologout")
    public String tologout(HttpSession session,Model model){
        session.invalidate();//让session过期
        User user=new User();
        model.addAttribute("user",user);
        return "redirect:/tologin";
    }
    @RequestMapping("/togwzx")
    public String togowc(HttpSession session,Model model){
        session.invalidate();//让session过期
        User user=new User();
        model.addAttribute("user",user);
        return "redirect:/grzx";
    }
    @RequestMapping("/togwc")
    public String togrzx(HttpSession session,Model model){
        return "redirect:/gwc";
    }
}
