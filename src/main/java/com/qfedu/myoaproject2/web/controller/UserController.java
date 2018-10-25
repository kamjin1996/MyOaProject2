package com.qfedu.myoaproject2.web.controller;


import com.qfedu.myoaproject2.base.controller.BaseController;
import com.qfedu.myoaproject2.pojo.User;
import com.qfedu.myoaproject2.service.UserService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    //删除此用户，实际是修改flag状态为2
    @RequestMapping("/deleteuser.do")
    public @ResponseBody
    QueryVo update(int id){
         QueryVo vo = new QueryVo();
           try {
               boolean b = userService.updateFlagById(id);
               if(b){
                   //修改成功0
                   vo.setCode(0);
               }

           } catch (Exception e){
               //服务器异常1
               vo.setCode(1);
           }
        return vo;
    }

    //分页查询
    @RequestMapping(value = "/userlist.do")
    public @ResponseBody
    QueryVo<User> list(int page, int limit) {
        return userService.queryAllByPage(page, limit);
    }

    //添加（内部）
    @RequestMapping(value = "/usersave.do",method = RequestMethod.POST)
    public String save(User user) {
        return userService.add(user)?"userlist.html":"useradd.html";
    }
    //检查名字是否存在
    @RequestMapping(value = "/checkname.do",method = RequestMethod.GET)
    public void checkName(String username, HttpServletResponse response) throws IOException {
        if(userService.checkName(username)){
            //可用
            response.getWriter().print(0);
        } else {
            //不可用
            response.getWriter().print(1);
        }

    }
    //用户信息编辑
    @RequestMapping(value = "/useredit.do",method = {RequestMethod.POST,RequestMethod.GET})
    public void useredit(User user,HttpServletResponse response) throws IOException {
       try {
           if( userService.editUserByID(user)){
               //修改成功
               response.getWriter().print(0);
           } else {
               //未作出修改
               response.getWriter().print(1);
           }
       }catch (Exception e){
           //服务器异常
           e.printStackTrace();
           response.getWriter().print(2);
       }
    }

    //添加（注册，暂时开放）
    @RequestMapping("/register.do")
    public String register(User user) {
        userService.register(user);
        return "login.html";
    }
    //登录
    @RequestMapping(value = "/login.do",method = RequestMethod.POST)
    public String login(String username, String password, @RequestParam(defaultValue = "") String remberme, HttpServletResponse response) throws IOException {
        User user = userService.queryByName(username, password);
        if (user != null) {
            //获取主题
            Subject subject = SecurityUtils.getSubject();
            //记住我
            boolean rem = false;
            if (remberme != null & remberme.length() > 0) {
                rem = true;
            }
            UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword(), rem);
            //尝试登录
            subject.login(token);
            subject.getSession().setAttribute("user", user);//存入shiro的session
            return "redirect:index.html";
        } else {
            return "redirect:login.html";
        }
    }
}
