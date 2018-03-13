package com.renhy.home.service;

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
}
