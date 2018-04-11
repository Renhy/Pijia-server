package com.renhy.home.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.renhy.home.model.AqoData;

/**
 * AQO Data Dao
 */
@Mapper
public interface AqoDataDao {

    int saveData(AqoData data);

    AqoData getLatest(@Param("device")String device);

    List<AqoData> list(
        @Param("device") String device,
        @Param("start") Date start,
        @Param("end") Date end,
        @Param("pageSize") int pageSize,
        @Param("pageNum") int pageNum);

}
