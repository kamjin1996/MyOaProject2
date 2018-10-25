package com.qfedu.myoaproject2.service.impl;

import com.qfedu.myoaproject2.base.service.impl.BaseServiceImpl;
import com.qfedu.myoaproject2.mapper.LeaveLogMapper;
import com.qfedu.myoaproject2.mapper.LeaveMapper;
import com.qfedu.myoaproject2.pojo.Leave;

import com.qfedu.myoaproject2.pojo.LeaveLog;
import com.qfedu.myoaproject2.service.LeaveService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeaveServiceImpl extends BaseServiceImpl<LeaveMapper, Leave> implements LeaveService<Leave> {

    @Autowired
    private LeaveMapper leaveMapper;
    @Autowired
    private LeaveLogMapper leaveLogMapper;
    @Autowired
    private RepositoryService repositoryService;
    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    private static final String FLOW_BPMN = "leave";
    private static final String DEPLOYMENT_NAME = "flow/leave.bpmn";
    private static final String PROCESS_NAME = "leaveProcess";
    private static final String MSG_1 = "申请请假";
    private static final String MSG_2 = "请假通过";
    private static final String MSG_3 = "请假拒绝";
    private static final String MSG_4 = "请假撤销";


    @Override
    public QueryVo<Leave> allTask() {
        List<Leave> leaves = leaveMapper.selectAll();
        return QueryVo.createPage(leaves.size(), leaves);
    }

    //加载流程图  需要做校验
    @Override
    public void initPro() {
        try {
            Deployment deployment = repositoryService.createDeploymentQuery().deploymentName(FLOW_BPMN).singleResult();
            if (deployment == null) {
                //没有--需要手动加载
                repositoryService.createDeployment().name(DEPLOYMENT_NAME).addClasspathResource(FLOW_BPMN).deploy();
            }
        } catch (Exception e) {

        }
    }

    //发起任务
    @Override
    public void createPro(Leave leave, String uname, String rname) {
        //准备参数
        Map<String, Object> param = new HashMap<>();
        param.put("days", leave.getDays());
        param.put("staff", uname);//第一个任务的审批人--自己
        param.put("group", rname);//d第二个任务的审批人--领导
        param.put("days", leave.getDays());
        //发布流程--返回实例
        ProcessInstance instance = runtimeService.startProcessInstanceByKey(PROCESS_NAME, param);
        //查询任务--根据流程实例
        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        leave.setTaskid(task.getId());

        //新增请假记录
        leaveMapper.insert(leave);

        LeaveLog log = new LeaveLog();
        log.setLid(leave.getId());
        log.setMsg("新增请假记录");
        log.setType(0);
        log.setUid(leave.getUid());
        leaveLogMapper.insert(log);

    }

    //我的待办事项
    @Override
    public QueryVo<Leave> doingTask(int uid) {
        //List<Task> tasks= taskService.createTaskQuery().taskAssignee(uname).list();
        List<Leave> leaves = leaveMapper.selectByUid(uid);
        return QueryVo.createPage(leaves.size(), leaves);
    }

    //审批
    @Override
    public void comTask(String tid, int lid, int flag, int uid) {
        //确认任务
        taskService.complete(tid);
        //
        leaveMapper.updateFlag(lid, flag);
        LeaveLog log = new LeaveLog();
        log.setLid(lid);

        switch (flag) {
            case 1://申请
                log.setMsg(MSG_1);

                break;
            case 2://通过
                log.setMsg(MSG_2);
                break;
            case 3://拒绝
                log.setMsg(MSG_3);
                break;
            case 4://申撤销
                log.setMsg(MSG_4);
                break;
        }
        log.setType(flag);
        log.setUid(uid);
        leaveLogMapper.insert(log);
    }

    @Override
    public QueryVo<HistoricTaskInstance> queryHistory(String uname, int page, int count) {
        List<HistoricTaskInstance> list = historyService.createHistoricTaskInstanceQuery().taskAssignee(uname).listPage(page, count);
        return QueryVo.createPage(list.size(), list);
    }

    @Override
    public QueryVo<LeaveLog> queryLog(int page, int count) {
        return QueryVo.createPage(leaveLogMapper.selectCount(), leaveLogMapper.selectByPage((page - 1) * count, count));
    }
}
