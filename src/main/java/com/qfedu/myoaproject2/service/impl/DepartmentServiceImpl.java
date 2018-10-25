package com.qfedu.myoaproject2.service.impl;


import com.qfedu.myoaproject2.base.service.impl.BaseServiceImpl;
import com.qfedu.myoaproject2.mapper.DepartmentMapper;
import com.qfedu.myoaproject2.pojo.Department;
import com.qfedu.myoaproject2.service.DepartmentService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper,Department> implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Override
    public boolean save(Department department) {
        //默认
        department.setFlag(0);
        department.setLevel("一级部门");
        return departmentMapper.insert(department)>0;
    }

    @Override
    public QueryVo<Department> queryAllByPage(int page, int limit) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * limit;
        }
        return QueryVo.createPage(departmentMapper.selectCount(), departmentMapper.selectByPage(index, limit));
    }

    @Override
    public int updateDepartmentById(Department department) {
        return departmentMapper.updateByPrimaryKeySelective(department);
    }

    @Override
    public List<Department> queryAll() {
        return departmentMapper.selectAll();
    }

    @Override
    public List<Department> queryByUid(int uid) {
        return departmentMapper.selectByUid(uid);
    }

    @Override
    public int updateUserDepartmentByUid(Integer[] did, int uid) {
        //删除用户所在部门
        departmentMapper.deleteByUid(uid);
        //插入用户部门
        return  departmentMapper.insertUserDepartment(did,uid);
    }
}
