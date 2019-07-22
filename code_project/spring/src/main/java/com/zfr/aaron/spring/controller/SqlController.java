package com.zfr.aaron.spring.controller;

import com.zfr.aaron.spring.entity.AreaImport;
import com.zfr.aaron.spring.mapper.AreaImportMapper;
import com.zfr.aaron.spring.project.config.JDBCDataSourceConfig;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


/**
 * 测试mysql性能
 * @author zfr
 */
@Controller
public class SqlController {

    @Autowired
    private JDBCDataSourceConfig jdbcDataSourceConfig;

    @Resource
    private AreaImportMapper areaImportMapper;

    @Resource
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 适中7秒
     * @return
     * @throws SQLException
     */
    @GetMapping("/sql")
    public String testSql() throws SQLException {
        long start = System.currentTimeMillis()/1000;
        Connection conn = jdbcDataSourceConfig.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = null;

        String sql = "INSERT INTO `ework_platform`.`t_area_import` ( `area_code`, `state`, `create_time`, `update_time`) VALUES ( ?, ?, ?, ?)";
        // 批量插入时ps对象必须放到for循环外面
        ps = conn.prepareStatement(sql);
        for (int i=0;i < 100000;i++){
            ps.setString(1, i+"");
            ps.setInt(2, 1);
            Date date = new Date(2019);
            ps.setDate(3, date);
            ps.setDate(4, date);

            ps.addBatch();
            // 每1000条记录插入一次
            if (i % 10000 == 0){
                ps.executeBatch();
                conn.commit();
                ps.clearBatch();
            }
        }
        // 剩余数量不足1000
        ps.executeBatch();
        conn.commit();
        ps.clearBatch();
        long end = System.currentTimeMillis()/1000;
        System.out.println(end - start);

        return "成功";
    }

    /**
     * 最快 1-3秒
     * @return
     * @throws SQLException
     */
    @GetMapping("/sql/mybatis")
    public String testMybatisSql() throws SQLException {
        long start = System.currentTimeMillis()/1000;

        List<AreaImport> areaImports = new LinkedList<>();

        AreaImport areaImport ;

        for (int i=0;i < 100000;i++){

            areaImport = new AreaImport();

            areaImport.setAreaCode(String.valueOf(i));

            java.util.Date date = new java.util.Date();

            areaImport.setCreateTime(date);

            areaImport.setState(1);

            areaImport.setUpdateTime(date);

            areaImports.add(areaImport);
        }
        areaImportMapper.insertList(areaImports);

        long end = System.currentTimeMillis()/1000;
        System.out.println(end - start);

        return "成功";
    }

    /**
     * 最慢 279秒
     * @return
     * @throws SQLException
     */
    @GetMapping("/sql/mybatis/bath")
    public String testMybatis1Sql() throws SQLException {
        long start = System.currentTimeMillis()/1000;

        //跟上述sql区别
        SqlSession sqlSession = sqlSessionTemplate.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);

        AreaImportMapper mapper = sqlSession.getMapper(AreaImportMapper.class);

        AreaImport areaImport ;

        for (int i=0;i < 100000;i++){

            areaImport = new AreaImport();

            areaImport.setAreaCode(String.valueOf(i));

            java.util.Date date = new java.util.Date();

            areaImport.setCreateTime(date);

            areaImport.setState(1);

            areaImport.setUpdateTime(date);

            mapper.insert(areaImport);
        }
        sqlSession.commit();
        long end = System.currentTimeMillis()/1000;
        System.out.println(end - start);

        return "成功";
    }



}
