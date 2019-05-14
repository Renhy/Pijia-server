package com.renhy.home.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.renhy.home.model.Device;
import com.renhy.home.model.DeviceData;

/**
 * Device Data Dao
 */
@Mapper
public interface DeviceDao {

    Device findDeviceByName(@Param("name") String name);

    List<Device> listDevice();


    int saveData(DeviceData data);

    DeviceData getLatest(@Param("device") String device);

    List<DeviceData> listData(
        @Param("device") String device,
        @Param("start") Date start,
        @Param("end") Date end,
        @Param("pageSize") int pageSize,
        @Param("pageNum") int pageNum);

}
