package com.renhy.home.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

/**
 * Mybatis Configuration
 */
@Configuration
public class MybatisConfig {

    private static final String MYBATIS_CONFIG_PATH = "classpath:mybatis-config.xml";
    private static final String MAPPER_FILE_PATH = "classpath*:/mapper/*.xml";

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_FILE_PATH));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(MYBATIS_CONFIG_PATH));
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }

}
