package com.qfedu.myoaproject2.service.impl;

import com.qfedu.myoaproject2.base.service.impl.BaseServiceImpl;
import com.qfedu.myoaproject2.mapper.UserMapper;
import com.qfedu.myoaproject2.pojo.User;
import com.qfedu.myoaproject2.service.UserService;

import com.qfedu.myoaproject2.utils.ShiroEncryUtil;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper, User> implements UserService<User> {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User queryByName(String username, String password) {
        User user = this.userMapper.selectByName(username);
        if (user != null
                && user.getFlag() == 0
                && Objects.equals(password, user.getPassword())) {
            return user;
        }
        return null;
    }

    @Override
    public boolean register(User user) {
        user.setPassword(user.getPassword());
        return this.userMapper.insert(user) > 0;
    }

    //分页查询
    @Override
    public QueryVo<User> queryAllByPage(int page, int limit) {
        int index = 0;
        if (page > 0) {
            index = (page - 1) * limit;
        }
        return QueryVo.createPage(this.userMapper.selectCount(), this.userMapper.selectUsersByPageAndLimit(index, limit));
    }

    @Override
    public boolean add(User user) {
        user.setPassword(ShiroEncryUtil.md5(user.getPassword()));
        return this.userMapper.insert(user) > 0;
    }

    @Override
    public User checkName(String username) {
        return this.userMapper.selectByName(username);
    }

    @Override
    public boolean editUserByID(User user) {
        user.setPassword(ShiroEncryUtil.md5(user.getPassword()));
        return this.userMapper.updateByPrimaryKeySelective(user) > 0;
    }

    @Override
    public boolean updateFlagById(int id) {
        User user = new User();
        user.setFlag(2);
        user.setId(id);
        return this.userMapper.updateByPrimaryKeySelective(user) > 0;
    }

}
