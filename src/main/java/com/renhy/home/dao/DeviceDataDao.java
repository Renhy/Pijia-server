package com.renhy.home.dao;

import org.apache.ibatis.annotations.Mapper;

import com.renhy.home.model.DeviceData;

/**
 * Device Data Dao
 */
@Mapper
public interface DeviceDataDao {

    int saveData(DeviceData data);



}
