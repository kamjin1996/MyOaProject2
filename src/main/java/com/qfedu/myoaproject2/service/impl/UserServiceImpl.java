package com.qfedu.myoaproject2.service.impl;

import com.qfedu.myoaproject2.base.service.impl.BaseServiceImpl;
import com.qfedu.myoaproject2.mapper.UserMapper;
import com.qfedu.myoaproject2.pojo.User;
import com.qfedu.myoaproject2.service.UserService;

import com.qfedu.myoaproject2.utils.ShiroEncryUtil;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserMapper,User> implements UserService<User> {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User queryByName(String username, String password){
       User user = userMapper.selectByName(username);
        if(user!=null){
            //只有状态正常的角色才可以登录！
            if(user.getFlag()==0){
                if(Objects.equals(password,user.getPassword())){
                    return user;
                }
            }
        }
        return null;
    }

    @Override
    public boolean register(User user) {
      user.setPassword(user.getPassword());
        return  userMapper.insert(user)>0;
    }

    //分页查询
    @Override
    public QueryVo<User> queryAllByPage(int page, int limit) {
        //limit分页所需的第一个参数坐标
        int index = 0;
        if(page>0){
            index= (page-1)*limit;
        }
        return  QueryVo.createPage(userMapper.selectCount(), userMapper.selectUsersByPageAndLimit(index, limit));
    }

    @Override
    public boolean add(User user) {
        try {
            user.setPassword(ShiroEncryUtil.md5(user.getPassword()));
            return userMapper.insert(user)>0;
        } catch (Exception e){
            return false;
        }


    }

    @Override
    public boolean checkName(String username) {
        //为null表示没有查到
        return userMapper.selectByName(username)==null;
    }

    @Override
    public boolean editUserByID(User user) {
        user.setPassword(ShiroEncryUtil.md5(user.getPassword()));
        return userMapper.updateByPrimaryKeySelective(user)>0;
    }

    @Override
    public boolean updateFlagById(int id) {
        User user = new User();
        user.setFlag(2);
        user.setId(id);
        boolean b = userMapper.updateByPrimaryKeySelective(user) > 0;
        return b;
    }

}
