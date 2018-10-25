package com.qfedu.myoaproject2.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.Leave;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveMapper extends BaseMapper<Leave> {
    int deleteByPrimaryKey(Integer id);

    int insert(Leave record);

    int insertSelective(Leave record);

    Leave selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Leave record);

    int updateByPrimaryKey(Leave record);


    List<Leave> selectByPage(@Param("index") int index, @Param("count") int count);
    int selectCount();

    int updateFlag(@Param("id") int id, @Param("flag") int flag);

    List<Leave> selectByUid(int uid);

    List<Leave> selectAll();

}