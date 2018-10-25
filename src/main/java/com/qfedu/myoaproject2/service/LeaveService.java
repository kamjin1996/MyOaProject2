package com.qfedu.myoaproject2.service;

import com.qfedu.myoaproject2.base.service.BaseService;
import com.qfedu.myoaproject2.mapper.ExcelMapper;
import com.qfedu.myoaproject2.mapper.LeaveMapper;
import com.qfedu.myoaproject2.mapper.ResourceMapper;
import com.qfedu.myoaproject2.pojo.Leave;
import com.qfedu.myoaproject2.pojo.LeaveLog;
import com.qfedu.myoaproject2.pojo.Resource;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.activiti.engine.history.HistoricTaskInstance;

public interface LeaveService<R> extends BaseService<LeaveMapper,Leave>{
    //流程列表
    QueryVo<Leave> allTask();
    void initPro();
    //发起申请
    void createPro(Leave leave, String uname, String rname);
    //我的待办事项
    QueryVo<Leave> doingTask(int uid);
    //确认申请
    void comTask(String tid, int lid, int flag, int uid);
    //查看流历史
    QueryVo<HistoricTaskInstance> queryHistory(String uname, int page, int count);
    //查看日志
    QueryVo<LeaveLog> queryLog(int page, int count);
}
