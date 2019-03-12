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
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/roleadd.do")
    public Mono<String> save(Role role) {
        return this.roleService.save(role) ? Mono.just("rolelist.html") : Mono.just("roleadd.html");
    }

    //初始化用户列表查看按钮的角色列表
    @RequestMapping("/roleall.do")
    public @ResponseBody
    List list() {
        return this.roleService.queryAll();
    }

    @RequestMapping("/rolelistbyuid.do")
    public @ResponseBody
    List queryByUid(int uid) {
        return this.roleService.queryByUid(uid);
    }

    //分页查询
    @RequestMapping("/rolelist.do")
    public @ResponseBody
    QueryVo list(int page, int limit) {
        return this.roleService.queryAllByPage(page, limit);
    }

    //用户角色编辑
    @RequestMapping("/userroleedit.do")
    public void updateUserRole(int uid, Integer[] rid, HttpServletResponse response) throws IOException {
        //0修改成功，1已去掉所有用户角色
        response.getWriter().print(this.roleService.updateUserRoleByUid(uid, rid) > 0 ? 0 : 1);
    }

    //角色信息编辑
    @RequestMapping("/roleedit.do")
    public void editRole(Role role, HttpServletResponse response) throws IOException {
        //0成功，1未做修改
        response.getWriter().print(this.roleService.updateRoleById(role) > 0 ? 0 : 1);
    }

    //删除角色实际是更改其状态，删除则将flag改为1。
    @RequestMapping(value = "/deleterole.do", method = RequestMethod.GET)
    public void updataFlag(int id, HttpServletResponse response) throws IOException {
        //0修改成功,1本来就无效
        response.getWriter().print(this.roleService.updataFlagById(id) > 0 ? 0 : 1);
    }

}
