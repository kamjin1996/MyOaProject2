package com.qfedu.myoaproject2.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper extends BaseMapper<Role> {
    int deleteByPrimaryKey(Integer id);

    //新增
    int insert(Role record);


    Role selectByPrimaryKey(Integer id);



    int updateByPrimaryKey(Role record);

    List<Role> selectByPage(@Param("index") int index, @Param("limit") int limit);

    int selectCount();

    List<Role> selectAll();

    List<Role> selectByUid(int uid);
    //根据用户修改角色
    //因为用户可能有多个角色，所以先删除后插入，考虑性能，避免service遍历调用mapper操作数据库
    int deleteRoleByUid(int uid);

    int insertRoleByUid(@Param("uid") int uid, @Param("rid") Integer[] rid);
    //flag==0时，也要可修改才行
    int updateByPrimaryKeySelective(Role record);

    int updateFlagTo1ById(int id);

}