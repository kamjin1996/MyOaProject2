package com.qfedu.myoaproject2.web.controller;

import com.qfedu.myoaproject2.base.controller.BaseController;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 基于shiro进行细粒度权限分配
 */
@Controller
public class ShiroController extends BaseController {

    //检查一个权限
    @RequestMapping("/shiropercheck.do")
    public void check(String per, HttpServletResponse response) throws IOException {
        try {
            //这里会采用抛异常的方式进行结果告知有没有权限。
            SecurityUtils.getSubject().checkPermission(per);
            //0表示有权限，1表示没有权限
            response.getWriter().print(0);
        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().print(1);
        }
    }
    //检查多个权限
    @RequestMapping("/shiroperchecks.do")
    public void check(String[] per, HttpServletResponse response) throws IOException {
        try {
            //方法可接受string类型的数组，传入多个权限来判断
            SecurityUtils.getSubject().checkPermissions(per);
            //0表示有权限，1表示没有权限
            response.getWriter().print(0);
        } catch (Exception e){
            response.getWriter().print(1);
        }
    }
}
