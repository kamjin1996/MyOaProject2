package com.qfedu.myoaproject2.web.controller;

import com.qfedu.myoaproject2.base.controller.BaseController;
import com.qfedu.myoaproject2.pojo.Role;
import com.qfedu.myoaproject2.service.RoleService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleadd.do")
    public String save(Role role) {
        return roleService.save(role) ? "rolelist.html" : "roleadd.html";
    }

    //初始化用户列表查看按钮的角色列表
    @RequestMapping("/roleall.do")
    public @ResponseBody
    List<Role> list() {
        return roleService.queryAll();
    }

    @RequestMapping("/rolelistbyuid.do")
    public @ResponseBody
    List<Role> queryByUid(int uid) {
        return roleService.queryByUid(uid);
    }

    //分页查询
    @RequestMapping("/rolelist.do")
    public @ResponseBody
    QueryVo<Role> list(int page, int limit) {
        QueryVo<Role> vo = roleService.queryAllByPage(page, limit);
        return vo;
    }

    //用户角色编辑
    @RequestMapping("/userroleedit.do")
    public void updateUserRole(int uid, Integer[] rid, HttpServletResponse response) throws IOException {
       try {
           int i = roleService.updateUserRoleByUid(uid, rid);
           if(i>0){
               //修改成功
               response.getWriter().print(0);
           } else {
               //已去掉所有用户角色
               response.getWriter().print(1);
           }
       } catch (Exception e){
           //服务器发生异常
           e.printStackTrace();
           response.getWriter().print(2);
       }

    }

    //角色信息编辑
    @RequestMapping("/roleedit.do")
    public void editRole(Role role,HttpServletResponse response) throws IOException {
       try {
           int i = roleService.updateRoleById(role);
           if(i>0){
               //编辑成功
               response.getWriter().print(0);
           } else {
               //未做修改
               response.getWriter().print(1);
           }
       } catch (Exception e){
           //服务器异常
           e.printStackTrace();
           response.getWriter().print(2);
       }
    }

    //删除角色实际是更改其状态，删除则将flag改为1。
    @RequestMapping(value = "/deleterole.do",method = RequestMethod.GET)
    public void updataFlag(int id,HttpServletResponse response) throws IOException {
       try {
           int i = roleService.updataFlagById(id);
           if(i>0){
               //修改成功
               response.getWriter().print(0);

           } else {
               //本来就无效
               response.getWriter().print(1);
           }
       } catch (Exception e){
           //服务器异常
           e.printStackTrace();
           response.getWriter().print(2);
       }

    }

}
