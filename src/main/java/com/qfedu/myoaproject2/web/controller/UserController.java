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
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@Controller
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    //删除此用户，实际是修改flag状态为2
    @RequestMapping("/deleteuser.do")
    public @ResponseBody
    QueryVo update(int id) {
        return new QueryVo(this.userService.updateFlagById(id) ? 0 : 1);
    }

    //分页查询
    @RequestMapping(value = "/userlist.do")
    public @ResponseBody
    QueryVo list(int page, int limit) {
        return this.userService.queryAllByPage(page, limit);
    }

    //添加（内部）
    @RequestMapping(value = "/usersave.do", method = RequestMethod.POST)
    public Mono<String> save(User user) {
        return userService.add(user) ? Mono.just("userlist.html") : Mono.just("useradd.html");
    }

    //检查名字是否存在
    @RequestMapping(value = "/checkname.do", method = RequestMethod.GET)
    public void checkName(String username, HttpServletResponse response) throws IOException {
        if (Objects.nonNull(this.userService.checkName(username))) {
            response.getWriter().print(0);
        } else {
            response.getWriter().print(1);
        }

    }

    //用户信息编辑
    @RequestMapping(value = "/useredit.do", method = {RequestMethod.POST, RequestMethod.GET})
    public void useredit(User user, HttpServletResponse response) throws IOException {
        try {
            if (this.userService.editUserByID(user)) {
                response.getWriter().print(0);
            } else {
                //未作出修改
                response.getWriter().print(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(2);
        }
    }

    //添加（注册，暂时开放）
    @RequestMapping("/register.do")
    public Mono<String> register(User user) {
        return this.userService.register(user) ? Mono.just("login.html") : Mono.just("error.html");
    }

    //登录
    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public Mono<String> login(String username, String password, @RequestParam(defaultValue = "") String remberme) {
        User user = this.userService.queryByName(username, password);
        if (user != null) {
            //获取主题
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(username, user.getPassword(), remberme != null & remberme.length() > 0);
            //尝试登录
            subject.login(token);
            //存入shiro的session
            subject.getSession().setAttribute("user", user);
            return Mono.just("redirect:index.html");
        } else {
            return Mono.just("redirect:login.html");
        }
    }
}
