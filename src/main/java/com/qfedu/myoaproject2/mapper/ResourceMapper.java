package com.qfedu.myoaproject2.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.Resource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResourceMapper extends BaseMapper<Resource> {

    int insertSelective(Resource record);

    Resource selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Resource record);



    int insert(Resource record);

    List<Resource> selectAll();

    List<Resource> selectByPage(@Param("index") int index, @Param("limit") int limit);

    int selectCount();

    List<Resource> selectAllFirstMenu();

    //根据用户查询拥有的权限
    List<Resource> selectByUid(Integer uid);

    List<Resource> selectResourceByRidAtRR(int rid);

    int deleteResoucrceByRid(int rid);

    int insertResourceByResid(@Param("rid") int rid, @Param("resid") Integer[] resid);

    int updateByPrimaryKeySelective(Resource record);

    int deleteByPrimaryKey(Integer id);


}