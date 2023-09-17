package com.yupi.springbootinit.utils;

import cn.hutool.core.collection.CollUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Utils for Excel - excelToCsv
 * @author philip
 */
@Slf4j
public class ExcelUtils {
    /**
     * convert excel to csv
     * @param multipartFile
     * @return
     */
    public static String excelToCsv(MultipartFile multipartFile){
          //for the local testing
//        File file = null;
//        try{
//            file = ResourceUtils.getFile("classpath:test_excel.xlsx");
//        }catch(FileNotFoundException e){
//            e.printStackTrace();
//        }

        //Read the file
        List<Map<Integer, String>> list = null;
        try{
            list = EasyExcel.read(multipartFile.getInputStream())
                    .excelType(ExcelTypeEnum.XLSX)
                    .sheet()
                    .headRowNumber(0)
                    .doReadSync();
        }catch (IOException e){
            log.error("Sheet handle error", e);
        }


        if(CollUtil.isEmpty(list)){
            return "";
        }

        //Convert to CSV
        StringBuilder stringBuilder = new StringBuilder();

        //title row
        LinkedHashMap<Integer,String> headerMap = (LinkedHashMap) list.get(0);
        List<String> headerList = headerMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
        stringBuilder.append(StringUtils.join(headerList,",")).append("\n");

        //data rows
        for(int i = 1;i<list.size();i++){
            LinkedHashMap<Integer,String> dataMap = (LinkedHashMap) list.get(i);
            List<String> dataList = dataMap.values().stream().filter(ObjectUtils::isNotEmpty).collect(Collectors.toList());
            stringBuilder.append(StringUtils.join(dataList,",")).append("\n");
        }
        System.out.println(list);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        excelToCsv(null);
    }

}
