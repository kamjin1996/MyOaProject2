package com.qfedu.myoaproject2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByName(String username);

    List<User> selectUsersByPageAndLimit(@Param("index") int index, @Param("limit") int limit);

    int selectCount();

}