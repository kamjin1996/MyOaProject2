package com.qfedu.myoaproject2.mapper;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.pojo.LeaveLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LeaveLogMapper extends BaseMapper<LeaveLog> {
    int insert(LeaveLog record);

    List<LeaveLog> selectByPage(@Param("index") int index, @Param("count") int count);

    int selectCount();
}