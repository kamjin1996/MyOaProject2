package com.qfedu.myoaproject2.service.impl;

import com.qfedu.myoaproject2.base.service.impl.BaseServiceImpl;
import com.qfedu.myoaproject2.mapper.ExcelMapper;
import com.qfedu.myoaproject2.service.ExcelService;
import com.qfedu.myoaproject2.utils.poi.ExportExcel;
import com.qfedu.myoaproject2.utils.poi.ImportExcel;
import com.qfedu.myoaproject2.vo.ExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl extends BaseServiceImpl<ExcelMapper,ExcelVo> implements ExcelService<ExcelVo> {
    @Autowired
    private ExcelMapper excelMapper;


    @Override
    public int inExcel(CommonsMultipartFile file) throws IOException {
        ExcelVo excelVo = new ExcelVo();
        File targ = new File("D:/excel/userdepartment.xls");
        String path = targ.getAbsolutePath();
        file.transferTo(targ);

        List<?> list = ImportExcel.importExcel(path, 0, 0, excelVo.getClass());
        return list.size();
    }

    @Override
    public void outExcel(HttpServletResponse response) throws Exception {
        List<ExcelVo> dataSet = excelMapper.selectExcel();
        System.out.println(dataSet);
       for(int i = 0;i< dataSet.size();i++){
           ExcelVo excelVo = dataSet.get(i);
           String sheetName = excelVo.getDepartmentName();
           String titleName="部门用户";
           String[] headers = {"用户ID", "用户名", "密码", "状态" ,"部门"};
           String resultUrl="D:info.xls";
           String pattern="yyyy-MM-dd";
           ExportExcel.exportExcel(sheetName, titleName, headers, dataSet, resultUrl,response, pattern);

       }
    }
}