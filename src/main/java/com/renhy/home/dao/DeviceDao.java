package com.renhy.home.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.renhy.home.model.DeviceData;

/**
 * Device Data Dao
 */
@Mapper
public interface DeviceDao {

    int saveData(DeviceData data);

    DeviceData getLatest(@Param("device") String device);

    List<DeviceData> list(
        @Param("device") String device,
        @Param("start") Date start,
        @Param("end") Date end,
        @Param("pageSize") int pageSize,
        @Param("pageNum") int pageNum);



}
