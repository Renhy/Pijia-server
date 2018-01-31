package com.renhy.home.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(MAPPER_FILE_PATH));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource(MYBATIS_CONFIG_PATH));
        sqlSessionFactoryBean.setVfs(SpringBootVFS.class);

        return sqlSessionFactoryBean.getObject();
    }

}
