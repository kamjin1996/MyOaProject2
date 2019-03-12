package com.qfedu.myoaproject2.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.qfedu.myoaproject2.base.service.impl.BaseServiceImpl;
import com.qfedu.myoaproject2.mapper.DepartmentMapper;
import com.qfedu.myoaproject2.pojo.Department;
import com.qfedu.myoaproject2.service.DepartmentService;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentServiceImpl extends BaseServiceImpl<DepartmentMapper, Department> implements DepartmentService {

    @Override
    public boolean save(Department department) {
        return this.baseMapper.insert(department.setFlag(0).setLevel("一级部门")) > 0;
    }

    @Override
    public QueryVo<Department> queryAllByPage(int page, int limit) {
        int index = page > 0 ? (page - 1) * limit : 0;
        return QueryVo.createPage(this.baseMapper.selectCount(new QueryWrapper<>()), this.baseMapper.selectByPage(index, limit));
    }

    @Override
    public int updateDepartmentById(Department department) {
        return this.baseMapper.updateById(department);
    }

    @Override
    public List<Department> queryAll() {
        return this.baseMapper.selectList(null);
    }

    @Override
    public List<Department> queryByUid(int uid) {
        QueryWrapper<Department> wrapper = new QueryWrapper<>();
        wrapper.eq("uid", uid);
        return this.baseMapper.selectList(wrapper);
    }

    @Override
    public int updateByUid(Integer[] did, int uid) {
        //删除用户所在部门
        UpdateWrapper<Department> wrapper = new UpdateWrapper<>();
        wrapper.eq("uid", uid);
        this.baseMapper.delete(wrapper);
        //插入用户部门
        return this.baseMapper.insert(did, uid);
    }
}
