package com.qfedu.myoaproject2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.UserDepartment;

public interface UserDepartmentMapper extends BaseMapper<UserDepartment> {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDepartment record);

    int insertSelective(UserDepartment record);

    UserDepartment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDepartment record);

    int updateByPrimaryKey(UserDepartment record);
}