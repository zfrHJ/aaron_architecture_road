package com.zfr.aaron.spring.project.utils.cvs;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class CSVDigester {

    /**文件名字和需要转换的目标类作为入参
     *
     * @param filename
     * @param type
     * @return
     */
    public static List parseCSV2Bean (String filename, Class type){

        List list =null;

        try {
            //每条记录以逗号分隔数据, 并以'\'作为行结束符。
            CSVReader reader = new CSVReader(new FileReader(filename), ',', '"');

            HeaderColumnNameMappingStrategy mappingStrategy =

                    new HeaderColumnNameMappingStrategy();

            mappingStrategy.setType(type);

            CsvToBean csv = new CsvToBean();

            //数据被按行解析并存入list
            list = csv.parse(mappingStrategy, reader);

        } catch (FileNotFoundException e) {

            // TODO Auto-generated catch block

            e.printStackTrace();

        }

        return list;

    }

    public static void main(String[] args) {
        List list = CSVDigester.parseCSV2Bean("E:\\历史数据.csv", Record.class);
        System.out.println(list);
    }
}
