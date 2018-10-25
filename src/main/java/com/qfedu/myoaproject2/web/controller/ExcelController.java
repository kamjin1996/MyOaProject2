package com.qfedu.myoaproject2.web.controller;


import com.qfedu.myoaproject2.base.controller.BaseController;
import com.qfedu.myoaproject2.mapper.ExcelMapper;
import com.qfedu.myoaproject2.service.ExcelService;
import com.qfedu.myoaproject2.utils.WriteExcel;
import com.qfedu.myoaproject2.vo.ExcelVo;
import com.qfedu.myoaproject2.vo.QueryVo;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * 专门对excel进行poi操作的控制器
 */

@Controller
public class ExcelController extends BaseController {
    @Autowired
    private ExcelService excelServie;

    @Autowired
    ExcelMapper excelMapper;

    //导入Excel
    @RequestMapping("/inexcel.do")
    public @ResponseBody
    QueryVo uploadExcel(@RequestParam("file") CommonsMultipartFile file) throws IOException {
        QueryVo queryVo = new QueryVo();
        int i = excelServie.inExcel(file);
        if(i>0){
            queryVo.setCode(0);
        } else {
            queryVo.setCode(1);
        }
        return queryVo;
    }

    //导出excel
    @RequestMapping("/outexcel.do")
    public void outExcel(HttpServletResponse response) throws Exception {
        String[] rowName= {"ID","用户名","密码","flag"};
        List<Object[]> datalist = new ArrayList<>();
        List<ExcelVo> excelVos = excelMapper.selectExcel();
        for (ExcelVo excelVo : excelVos) {
            Object[] o = {excelVo.getId(),excelVo.getUsername(),excelVo.getPassword(),excelVo.getFlag()};
            datalist.add(o);
        }
        String sheetName = "教育";
        WriteExcel writeExcel = new WriteExcel(rowName,datalist,sheetName);
        writeExcel.saveToFile("D:/info9.xls");
        File file = new File("D:/info9.xls");
        byte[] bytes = FileUtils.readFileToByteArray(file);
        String filename="default.xls";
        filename= URLEncoder.encode(filename,"utf-8");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment; filename="+filename);
        response.getOutputStream().write(bytes);

    }
}