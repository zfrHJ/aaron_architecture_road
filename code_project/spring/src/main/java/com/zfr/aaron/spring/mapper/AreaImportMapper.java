package com.zfr.aaron.spring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfr.aaron.spring.entity.AreaImport;
import org.apache.ibatis.annotations.Lang;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ResultHandler;

import java.util.List;

/**
 * @author zfr
 * mapper
 */
public interface AreaImportMapper extends BaseMapper {
    /**
     * 批量插入
     * @param areaImports 批量list
     * @return
     */
    int insertList(List<AreaImport> areaImports);

    /**
     * mysql情况比较特殊，只能这样设置
     * @return
     */
    @Select("select area_code as areaCode , create_time as createTime , state from t_area_import")
    @Options(fetchSize = Integer.MIN_VALUE)
    Cursor<AreaImport> scrollResult();

    /**
     * 流式查询
     * @param handler
     */
    void selectForwardOnly(ResultHandler<AreaImport> handler);


    List<AreaImport> getAll();





}
