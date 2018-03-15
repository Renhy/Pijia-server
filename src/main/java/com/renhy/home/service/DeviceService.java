package com.renhy.home.service;

import java.util.Date;
import java.util.List;

import com.renhy.home.model.DeviceData;

/**
 * Device Service Interface
 */
public interface DeviceService {

    int saveDeviceData(DeviceData data);

    DeviceData getLatest(String device);

    List<DeviceData> list(String device, Date start, Date end, int pageSize, int pageNum);

}
