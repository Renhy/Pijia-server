package com.renhy.home.config;

import java.util.Map;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.github.pagehelper.PageInterceptor;

import lombok.extern.slf4j.Slf4j;

/**
 * 扩展 Mybatis PageHelper 的 通用分页拦截器,添加分页大小的最大值限制.
 * 在 MyBatis 的配置文件，插件部分，添加 PageSizeMaxValue 属性，添加最大值.
 * PageHelper 的项目地址 : http://git.oschina.net/free/Mybatis_PageHelper
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts({
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}),
    @Signature(type = Executor.class, method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class,
            CacheKey.class, BoundSql.class})})
@Slf4j
public class PageWithPageSizeLimitInterceptor extends PageInterceptor {
    private static final String DEFAULT_PAGE_SIZE_MAX_VALUE = "200";
    private static final String PageSizeParam = "pageSize";

    private int pageSizeMaxValue;

    /**
     * 从分页插件的参数中，获取分页的最大值. 默认的最大值为200, 如果获取不到或者解析失败，
     * 则设置该值为 默认值.
     */
    @Override
    public void setProperties(Properties properties) {

        // get pageSizeMaxValue from settings.
        String maxValue = properties.getProperty("pageSizeMaxValue", DEFAULT_PAGE_SIZE_MAX_VALUE);
        try {
            pageSizeMaxValue = Integer.parseInt(maxValue);
        } catch (NumberFormatException e) {
            log.warn("Cannot parse properties 'pageSizeMaxValue' [{}], set default 200", maxValue);
            pageSizeMaxValue = 200;
        }
        super.setProperties(properties);
    }

    /**
     * 从参数中获取分页的大小，解析分页的大小，如果分页大小超过设置的值，则设置该值为设置的最大值，同时
     * 解析失败，则跳过设置的值.
     * {@inheritDoc}
     */
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();

        if (args[1] != null && args[1] instanceof Map) {
            Map para = (Map) args[1];

            Object pageSizeObj;
            if (para.containsKey(PageSizeParam) && (pageSizeObj = para.get(PageSizeParam)) != null
                && pageSizeObj instanceof Integer) {

                try {
                    int pageSize = (Integer) pageSizeObj;
                    if (pageSize > pageSizeMaxValue) {
                        para.put(PageSizeParam, pageSizeMaxValue);
                    }
                } catch (Exception e) {
                    // 如果解析失败，忽略修改该值.
                    log.debug("unexpected error when convert {} value [{}] to int", PageSizeParam, pageSizeObj);
                }
            }
        }

        return super.intercept(invocation);
    }

}