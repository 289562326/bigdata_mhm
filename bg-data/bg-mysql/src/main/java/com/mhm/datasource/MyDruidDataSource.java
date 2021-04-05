package com.mhm.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by MHm on 2019/6/12.
 */
@Configuration
@MapperScan(basePackages = {"com.mhm.**.dao"}, sqlSessionTemplateRef = "baseSqlSessionTemplate")
public class MyDruidDataSource {
    @Bean(name = "baseDataSource")
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource setDataSource() {
        //阿里巴巴的数据库连接池
        return new DruidDataSource();
    }

    /**
     * 配置Druid的监控
     */

    @Bean
    public ServletRegistrationBean statViewServlet() {
        ServletRegistrationBean<StatViewServlet> bean =
        new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        Map<String, String> initParms = new HashMap<>(3);
        initParms.put("loginUsername", "root");
        initParms.put("loginPassword", "888888");
        //允许访问,默认所有的
        initParms.put("allow", "");
        bean.setInitParameters(initParms);
        return bean;
    }

    /**
     * 配置一个Web监控的Filter
     */
    @Bean
    public FilterRegistrationBean webStatFilter() {
        FilterRegistrationBean<WebStatFilter> bean = new FilterRegistrationBean<>(new WebStatFilter());
        Map<String, String> initParams = new HashMap<>(2);
        initParams.put("exclusions", "*.js,*.css,/druid/*");
        //拦截所以请求
        bean.setUrlPatterns(Arrays.asList("/*"));
        bean.setInitParameters(initParams);
        return bean;
    }

    @Bean(name = "baseTransactionManager")
    public DataSourceTransactionManager setTransactionManager(@Qualifier("baseDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "baseSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("baseDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //mybatis 下划线转驼峰
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);

        //mybatis 分页插件
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        //通过设置pageSize=0或者RowBounds.limit = 0就会查询出全部的结果。
        //p.setProperty("pageSizeZero", "true");
        //配置mysql数据库的方言
        //p.setProperty("dialect","mysql");
        pageHelper.setProperties(p);
        //添加插件
        bean.setPlugins(new Interceptor[]{pageHelper});


        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:/mybatis/mapper/*/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "baseSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("baseSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
