package com.zfr.aaron.spring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfr.aaron.spring.entity.AreaImport;

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
}
