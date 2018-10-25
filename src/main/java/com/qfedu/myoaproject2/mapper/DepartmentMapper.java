package com.qfedu.myoaproject2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {
    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    Department selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);

    int selectCount();

    List<Department> selectByPage(@Param("index") int index, @Param("limit") int limit);

    List<Department> selectAll();

    List<Department> selectByUid(int uid);

    int deleteByUid(int uid);

    int insertUserDepartment(@Param("did") Integer[] did, @Param("uid") int uid);


    Department selectByName(String sheetName);

    int selectIDByName(String sheetName);

    int insertUsersByDid(@Param("did") int did, @Param("uids") List<Integer> uids);

}