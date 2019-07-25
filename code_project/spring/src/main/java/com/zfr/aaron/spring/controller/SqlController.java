package com.zfr.aaron.spring.controller;

import com.zfr.aaron.spring.entity.AreaImport;
import com.zfr.aaron.spring.mapper.AreaImportMapper;
import com.zfr.aaron.spring.project.config.JDBCDataSourceConfig;
import com.zfr.aaron.spring.project.utils.MyStringUtils;
import com.zfr.aaron.spring.project.utils.cvs.Record;
import io.swagger.annotations.ApiOperation;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.ResultContext;
import org.apache.ibatis.session.ResultHandler;
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
import java.util.*;


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


    @GetMapping("/cursor")
    public String  getCursor(){

        long start = System.currentTimeMillis()/1000;
        List<AreaImport> list =  new ArrayList<>();

        areaImportMapper.selectForwardOnly(new ResultHandler<AreaImport>() {
            @Override
            public void handleResult(ResultContext<? extends AreaImport> resultContext) {
                list.add(resultContext.getResultObject());
            }
        });

        long end = System.currentTimeMillis()/1000;
        System.out.println("时间:"+ (end - start));
        System.out.println(list.size());
        /*Cursor<AreaImport> cursor= areaImportMapper.scrollResult();
        Iterator<AreaImport> iter= cursor.iterator();
        int count=0;
        while (iter.hasNext()){
            System.out.println(iter.next().getAreaCode());

        }*/
        return "";
    }

    @GetMapping("/all")
    public String  getAll(){

        long start = System.currentTimeMillis()/1000;

        List<AreaImport> all = areaImportMapper.getAll();

        long end = System.currentTimeMillis()/1000;
        System.out.println("时间:"+ (end - start));
        System.out.println(all.size());
        return "";
    }


    /**
     * 适中7秒
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "测试jdbc", notes = "测试jdbc")
    @GetMapping("/sql")
    public String testSql() throws SQLException {
        long start = System.currentTimeMillis()/1000;
        Connection conn = jdbcDataSourceConfig.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement ps = null;
        String sql = "INSERT INTO `ework_platform`.`t_area_import` " +
                "( `area_code`, `state`, `create_time`, `update_time`) VALUES ( ?, ?, ?, ?)";
        // 批量插入时ps对象必须放到for循环外面
        ps = conn.prepareStatement(sql);
        for (int i=0;i < 100000;i++){
            ps.setString(1, i+"");
            ps.setInt(2, 1);
            Date date = new Date(2019);
            ps.setDate(3, date);
            ps.setDate(4, date);

            ps.addBatch();
            // 每10000条记录插入一次
            if (i % 10000 == 0){
                ps.executeBatch();
                conn.commit();
                ps.clearBatch();
            }
        }
        // 剩余数量不足10000
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
    @ApiOperation(value = "测试mybatis", notes = "测试mybatis")
    @GetMapping("/sql/mybatis")
    public String testMybatisSql() throws SQLException {
        long start = System.currentTimeMillis()/1000;

        List<AreaImport> areaImports = new LinkedList<>();
        //List<AreaImport> areaImports = new ArrayList<>();
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
        if (MyStringUtils.isObjNotEmpty(areaImports) && areaImports.size() > 0) {
            //限制条数
            int pointsDataLimit = 10000;
            Integer size = areaImports.size();
            //判断是否有必要分批
            if (pointsDataLimit < size) {
                //分批数
                int part = size / pointsDataLimit;
                for (int i = 0; i < part; i++) {
                    //1000条
                    List<AreaImport>  listPage = areaImports.subList(0, pointsDataLimit);
                    areaImportMapper.insertList(listPage);
                    //剔除
                    areaImports.subList(0, pointsDataLimit).clear();
                }
                if (!areaImports.isEmpty()) {
                    areaImportMapper.insertList(areaImports);
                }
            } else {
                //操作
                areaImportMapper.insertList(areaImports);
            }
        }

        long end = System.currentTimeMillis()/1000;
        System.out.println(end - start);

        return "成功";
    }

    /**
     * 最慢 279秒
     * @return
     * @throws SQLException
     */
    @ApiOperation(value = "测试mybatis-bath", notes = "测试mybatis-bath")
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
