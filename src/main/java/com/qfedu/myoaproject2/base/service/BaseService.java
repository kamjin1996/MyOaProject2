package com.qfedu.myoaproject2.base.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.poi.hssf.record.formula.functions.T;

/**
 * @Auther: Kam
 * @Date: 下午 8:54 2018-10-23
 * @Description: 基础服务类
 * @Version: 1.0
 */
public interface BaseService<M extends BaseMapper<T>,T> extends IService<T> {

}
