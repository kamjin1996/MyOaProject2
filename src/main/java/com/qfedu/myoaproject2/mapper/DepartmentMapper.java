package com.qfedu.myoaproject2.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.Department;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DepartmentMapper extends BaseMapper<Department> {

    List<Department> selectByPage(@Param("index") int index, @Param("limit") int limit);

    int insert(@Param("did") Integer[] did, @Param("uid") int uid);

    int insertUsersByDid(@Param("did") int did, @Param("uids") List<Integer> uids);

}