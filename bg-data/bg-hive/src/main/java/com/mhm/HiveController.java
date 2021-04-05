package com.mhm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Mhm
 * @Title: ${file_name}
 * @Package ${package_name}
 * @Description: ${todo}
 * @date 2020-4-15 14:33
 */
@RestController
@RequestMapping("/hive")
public class HiveController {
    private static final Logger logger = LoggerFactory.getLogger(HiveController.class);
//    @Autowired
//    @Qualifier("hiveJdbcDataSource")
//    org.apache.tomcat.jdbc.pool.DataSource jdbcDataSource;

    @Autowired
    @Qualifier("hiveDruidDataSource")
    DataSource druidDataSource;

    /**
     * 列举当前Hive库中的所有数据表
     */
    @RequestMapping("/table/list")
    public List<String> listAllTables() throws SQLException {
        List<String> list = new ArrayList<String>();
        // Statement statement = jdbcDataSource.getConnection().createStatement();
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "show tables";
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        while (res.next()) {
            list.add(res.getString(1));
        }
        return list;
    }

    @RequestMapping("/table/create")
    public boolean createTable() throws SQLException {
        // Statement statement = jdbcDataSource.getConnection().createStatement();
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "create table testmhm"+ new Random().nextInt(10) +"(a string)";
        logger.info("Running: " + sql);
        boolean result  = statement.execute(sql);
        return result;
    }

    /**
     * 查询Hive库中的某张数据表字段信息
     */
    @RequestMapping("/table/describe")
    public List<String> describeTable(String tableName) throws SQLException {
        List<String> list = new ArrayList<String>();
        // Statement statement = jdbcDataSource.getConnection().createStatement();
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "describe " + tableName;
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        while (res.next()) {
            list.add(res.getString(1));
        }
        return list;
    }

    /**
     * 查询指定tableName表中的数据
     */
    @RequestMapping("/table/select")
    public List<String> selectFromTable(String tableName) throws SQLException {
        // Statement statement = jdbcDataSource.getConnection().createStatement();
        Statement statement = druidDataSource.getConnection().createStatement();
        String sql = "select * from " + tableName;
        logger.info("Running: " + sql);
        ResultSet res = statement.executeQuery(sql);
        List<String> list = new ArrayList<String>();
        int count = res.getMetaData().getColumnCount();
        String str = null;
        while (res.next()) {
            str = "";
            for (int i = 1; i < count; i++) {
                str += res.getString(i) + " ";
            }
            str += res.getString(count);
            logger.info(str);
            list.add(str);
        }
        return list;
    }


}
