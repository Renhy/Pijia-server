package com.renhy.home.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.renhy.home.model.Device;
import com.renhy.home.model.DeviceData;

/**
 * Device Service Interface
 */
public interface DeviceService {

    Device findDeviceByName(String name);

    List<Device> listDevice();

    int saveDeviceData(DeviceData data);

    DeviceData getLatest(String device);

    PageInfo<DeviceData> listData(String device, Date start, Date end, int pageSize, int pageNum);

}
