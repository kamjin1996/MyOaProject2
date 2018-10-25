package com.qfedu.myoaproject2.service;

import com.qfedu.myoaproject2.base.service.BaseService;
import com.qfedu.myoaproject2.mapper.RoleMapper;
import com.qfedu.myoaproject2.pojo.Role;
import com.qfedu.myoaproject2.vo.QueryVo;

import java.util.List;

public interface RoleService<R> extends BaseService<RoleMapper,Role> {

    QueryVo<Role> queryAllByPage(int page, int limit);

    boolean save(Role role);

    List<Role> queryAll();

    List<Role> queryByUid(int uid);

    int updateUserRoleByUid(int uid, Integer[] rid);

    int updateRoleById(Role role);

    int updataFlagById(int id);
}
