package com.qfedu.myoaproject2.web.controller;

import com.qfedu.myoaproject2.base.controller.BaseController;
import com.qfedu.myoaproject2.pojo.Resource;
import com.qfedu.myoaproject2.service.ResourceService;
import com.qfedu.myoaproject2.vo.MenuVo;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ResourceController extends BaseController {
    @Autowired
    private ResourceService resourceService;

    //左菜单数据准备
    @GetMapping("/usermenus.do")
    public @ResponseBody
    List<MenuVo> leftList() {
        return resourceService.queryLeftMenus();
    }

    //分页查询
    @RequestMapping("/resourcelist.do")
    public @ResponseBody
    QueryVo<Resource> reList(int page, int limit) {
        return resourceService.queryByPage(page, limit);
    }

    //保存
    @RequestMapping("/resourcesave.do")
    public Mono<String> save(Resource resource) {
        return resourceService.save(resource) ? Mono.just("resourcelist.html") : Mono.just("resourceadd.html");
    }

    //加载一级菜单
    @RequestMapping("/resourcerootmenu.do")
    public @ResponseBody
    List<Resource> initFirstMenu() {
        return resourceService.initFirstMenu();
    }

    //初始化资源列表
    @RequestMapping("/resourceall.do")
    public @ResponseBody
    List<Resource> all() {
        return resourceService.selectAll();
    }

    //根据uid查询权限
    @RequestMapping("/resourcelistbyrid.do")
    public @ResponseBody
    List<Resource> listByRid(int rid) {

        return resourceService.selectByRid(rid);
    }

    //更改角色权限
    // roleresourceedit.do
    @RequestMapping("/roleresourceedit.do")
    public void edit(int rid, Integer[] resid, HttpServletResponse response) throws IOException {
        try {

            int i = resourceService.updateResourcebyRid(rid, resid);
            if (i > 0) {
                //修改成功
                response.getWriter().print(0);
            } else {
                //已去掉所有权限
                response.getWriter().print(1);
            }
        } catch (Exception e) {
            //服务器异常，修改失败
            e.printStackTrace();
            response.getWriter().print(2);
        }

    }

    //修改权限信息
    @RequestMapping("/resourceedit.do")
    public void editRes(Resource resource, HttpServletResponse response) throws IOException {
        try {
            int i = resourceService.updateResourceById(resource);
            if (i > 0) {
                //修改成功
                response.getWriter().print(0);
            } else {
                //修改失败
                response.getWriter().print(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(2);
        }
    }

    //删除权限
    @RequestMapping("/deleteres.do")
    public void deleteRes(int id, HttpServletResponse response) throws IOException {
        try {
            int i = resourceService.deleteResById(id);
            if (i > 0) {
                //修改成功
                response.getWriter().print(0);
            } else {
                //修改失败
                response.getWriter().print(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().print(2);
        }
    }

}
