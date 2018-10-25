package com.qfedu.myoaproject2.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.qfedu.myoaproject2.vo.ExcelVo;

import java.util.List;

public interface ExcelMapper extends BaseMapper<ExcelVo> {
    List<ExcelVo> selectExcel();
}
