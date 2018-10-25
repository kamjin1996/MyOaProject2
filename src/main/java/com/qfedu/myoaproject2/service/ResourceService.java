package com.qfedu.myoaproject2.service;


import com.qfedu.myoaproject2.base.service.BaseService;
import com.qfedu.myoaproject2.mapper.ResourceMapper;
import com.qfedu.myoaproject2.pojo.Resource;
import com.qfedu.myoaproject2.vo.MenuVo;
import com.qfedu.myoaproject2.vo.QueryVo;

import java.util.List;

public interface ResourceService<R> extends BaseService<ResourceMapper,Resource> {
    //左侧菜单栏资源的获取
    List<MenuVo> queryLeftMenus();
    //资源列表
    QueryVo<Resource> queryByPage(int page, int limit);

    boolean save(Resource resource);

    List<Resource> initFirstMenu();

    //根据用户查询对应的权限
    List<Resource> selectByUid(Integer uid);

    List<Resource> selectAll();

    List<Resource> selectByRid(int rid);

    int updateResourcebyRid(int rid, Integer[] resid);

    int updateResourceById(Resource resource);

    //根据id删权限
    int deleteResById(int id);
}
