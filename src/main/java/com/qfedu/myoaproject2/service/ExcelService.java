package com.qfedu.myoaproject2.service;

import com.qfedu.myoaproject2.base.service.BaseService;
import com.qfedu.myoaproject2.mapper.ExcelMapper;
import com.qfedu.myoaproject2.vo.ExcelVo;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public interface ExcelService<E> extends BaseService<ExcelMapper,ExcelVo> {
    //对userlist进行poi
    //导入excel
    int inExcel(CommonsMultipartFile file) throws IOException;
    //导出excel
    void outExcel(HttpServletResponse response) throws Exception;
}
