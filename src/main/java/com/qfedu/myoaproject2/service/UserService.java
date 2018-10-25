package com.qfedu.myoaproject2.service;

import com.qfedu.myoaproject2.base.service.BaseService;
import com.qfedu.myoaproject2.mapper.UserMapper;
import com.qfedu.myoaproject2.pojo.User;
import com.qfedu.myoaproject2.vo.QueryVo;

public interface UserService<U> extends BaseService<UserMapper,User> {

    User queryByName(String username, String password);
    boolean register(User user);

    QueryVo<User> queryAllByPage(int page, int limit);

    boolean add(User user);

    boolean checkName(String username);

    boolean editUserByID(User user);

    boolean updateFlagById(int id);
}
