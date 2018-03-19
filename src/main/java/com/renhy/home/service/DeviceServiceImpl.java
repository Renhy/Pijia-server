package com.renhy.home.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.renhy.home.dao.DeviceDao;
import com.renhy.home.model.DeviceData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Device Service Implement
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Autowired
    private DeviceDao deviceDao;


    @Override
    public int saveDeviceData(DeviceData data) {
        return deviceDao.saveData(data);
    }

    @Override
    public DeviceData getLatest(String device) {
        return deviceDao.getLatest(device);
    }

    @Override
    public PageInfo<DeviceData> list(String device, Date start, Date end, int pageSize, int pageNum) {
        return new PageInfo<>(deviceDao.list(device, start, end, pageSize, pageNum));
    }
}
