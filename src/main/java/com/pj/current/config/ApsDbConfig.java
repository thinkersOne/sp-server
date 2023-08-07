package com.pj.current.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * 从数据源配置
 * 若需要配置更多数据源 , 直接在yml中添加数据源配置再增加相应的新的数据源配置类即可
 */
@Configuration
@MapperScan(basePackages  = ApsDbConfig.PACKAGE , sqlSessionFactoryRef = "apsClusterSqlSessionFactory")
public class ApsDbConfig {
    private Logger logger = LoggerFactory.getLogger(ApsDbConfig.class);
    // 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.pj.project.aps";
    private static final String MAPPER_LOCATION = "classpath*:mapper/aps/*.xml";
    private static final String DOMAIN_PACKAGE = "com.pj.project.aps";

    @Value("${spring.datasource.url3}")
    private String dbUrl;

    @Value("${spring.datasource.username2}")
    private String username;

    @Value("${spring.datasource.password2}")
    private String password;

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;



    @Bean(name="apsClusterDataSource")   //声明其为Bean实例
    public DataSource clusterDataSource() {
        DruidDataSource datasource = new DruidDataSource();

        datasource.setUrl(this.dbUrl);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        return datasource;
    }

    @Bean(name = "apsClusterTransactionManager")
    public DataSourceTransactionManager clusterTransactionManager() {
        return new DataSourceTransactionManager(clusterDataSource());
    }

    @Bean(name = "apsClusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("apsClusterDataSource") DataSource culsterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(culsterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ApsDbConfig.MAPPER_LOCATION));
        sessionFactory.setTypeAliasesPackage(DOMAIN_PACKAGE);
        //mybatis 数据库字段与实体类属性驼峰映射配置
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory.getObject();
    }
}
