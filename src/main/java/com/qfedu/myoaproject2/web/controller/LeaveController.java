package com.qfedu.myoaproject2.web.controller;

import com.qfedu.myoaproject2.base.controller.BaseController;
import com.qfedu.myoaproject2.pojo.Leave;
import com.qfedu.myoaproject2.pojo.LeaveLog;
import com.qfedu.myoaproject2.pojo.User;
import com.qfedu.myoaproject2.service.LeaveService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LeaveController extends BaseController {
    @Autowired
    private LeaveService leaveService;

    //创建
    @RequestMapping("/leaveadd.do")
    public String add(Leave leave, String rname) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        leave.setUid(user.getId());
        leaveService.createPro(leave, user.getUsername(), rname);
        return "leavelist.html";
    }

    //审批
    @RequestMapping("leaveupdate.do")
    public void update(String tid, int flag, int id, HttpServletResponse response) throws IOException {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        leaveService.comTask(tid, id, flag, user.getId());
        response.getWriter().print(0);
    }

    //我的待办事项
    @RequestMapping("leavelistbyuid.do")
    @ResponseBody
    public QueryVo<Leave> list() {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return leaveService.doingTask(user.getId());
    }
    //所有流程
    @RequestMapping("leaveall.do")
    @ResponseBody
    public QueryVo<Leave> all() {
        return leaveService.allTask();
    }


    //我的历史
    @RequestMapping("leavehislist.do")
    @ResponseBody
    public QueryVo<HistoricTaskInstance> hislist(int page, int limit) {
        User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        return leaveService.queryHistory(user.getUsername(), page, limit);
    }

    //我的日志
    @RequestMapping("leaveloglist.do")
    @ResponseBody
    public QueryVo<LeaveLog> loglist(int page, int limit) {
        return leaveService.queryLog(page, limit);
    }
}