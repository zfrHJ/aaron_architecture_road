package com.zfr.aaron.spring.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zfr.aaron.spring.entity.AreaImport;

import java.util.List;

public interface AreaImportMapper extends BaseMapper {

    int insertList(List<AreaImport> areaImports);
}
