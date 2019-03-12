package com.qfedu.myoaproject2.service;


import com.qfedu.myoaproject2.base.service.BaseService;
import com.qfedu.myoaproject2.mapper.DepartmentMapper;
import com.qfedu.myoaproject2.pojo.Department;
import com.qfedu.myoaproject2.vo.QueryVo;

import javax.validation.constraints.DecimalMax;
import java.util.List;

public interface DepartmentService extends BaseService<DepartmentMapper,Department> {

    boolean save(Department department);

    QueryVo<Department> queryAllByPage(int page, int limit);

    int updateDepartmentById(Department department);

    List<Department> queryAll();

    List<Department> queryByUid(int uid);

    int updateByUid(Integer[] did, int uid);

}
