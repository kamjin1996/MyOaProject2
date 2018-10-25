package com.qfedu.myoaproject2.web.controller;

import com.qfedu.myoaproject2.base.controller.BaseController;
import com.qfedu.myoaproject2.pojo.Department;
import com.qfedu.myoaproject2.service.DepartmentService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentService departmentService;

    //新增部门
    @RequestMapping("/departmentadd.do")
    public String save(Department department) {
        return departmentService.save(department) ? "departmentlist.html" : "departmentadd.html";
    }

    //修改用户所在部门
    @RequestMapping("/userdepartmentedit.do")
    public void update(Integer[] did,int uid,HttpServletResponse response) throws IOException {
        System.out.println(did);
        System.out.println(uid);
        try {
            int i = departmentService.updateUserDepartmentByUid(did, uid);
            if(i>0){
                response.getWriter().print(0);
            } else {
                response.getWriter().print(1);
            }
        } catch (Exception e){
            e.printStackTrace();
            response.getWriter().print(2);
        }

    }

    //部门列表
    //分页查询
    @RequestMapping("/departmentlist.do")
    public @ResponseBody
    QueryVo<Department> list(int page, int limit) {
        QueryVo<Department> vo = departmentService.queryAllByPage(page, limit);
        return vo;
    }

    //查询所有部门
    @RequestMapping("/departmentall.do")
    public @ResponseBody
    List<Department> initDep(){
        return departmentService.queryAll();
    }

    //根据用户id查询所属部门
    @RequestMapping("/departmentbyuid.do")
    public @ResponseBody
    List<Department> listByUid(int uid){
        return departmentService.queryByUid(uid);
    }

    //编辑部门
    @RequestMapping("/departmentedit.do")
    public void editDepart(Department department,HttpServletResponse response) throws IOException {
        try {
            int i = departmentService.updateDepartmentById(department);
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

}
