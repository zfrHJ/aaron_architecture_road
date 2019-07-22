package com.zfr.aaron.spring.project.batch;

import com.zfr.aaron.spring.project.utils.MyStringUtils;

import java.util.*;

/**
 * @author zfr
 * List 批量操作
 */
public class ListBatch {
    private List<Map> sendCode(Set<String> erpCodes) {
        ArrayList<String> dataList = new ArrayList<>(erpCodes);
        List<Map> map = new ArrayList<>(dataList.size());
        if (MyStringUtils.isObjNotEmpty(dataList) && dataList.size() > 0) {
            //限制条数
            int pointsDataLimit = 10000;
            Integer size = dataList.size();
            //判断是否有必要分批
            if (pointsDataLimit < size) {
                //分批数
                int part = size / pointsDataLimit;
                for (int i = 0; i < part; i++) {
                    //1000条
                    List<String> listPage = dataList.subList(0, pointsDataLimit);
                    HashSet<String> objects = new HashSet<>(listPage);

                    //3.操作
                    List<Map> list = new ArrayList<>();
                    if (MyStringUtils.isObjNotEmpty(list) && list.size() > 0) {
                        map.addAll(list);
                    }
                    //剔除
                    dataList.subList(0, pointsDataLimit).clear();
                }
                if (!dataList.isEmpty()) {
                    HashSet<String> list = new HashSet<>(dataList);
                    //操作
                    List<Map> listResult = new ArrayList<>();
                    if (MyStringUtils.isObjNotEmpty(listResult) && listResult.size() > 0) {
                        map.addAll(listResult);
                    }

                }

            } else {
                //操作
                return null;
            }
        }
        return map;
    }
}
