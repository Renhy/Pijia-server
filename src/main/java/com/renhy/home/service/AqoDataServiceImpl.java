package com.renhy.home.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.renhy.home.dao.AqoDataDao;
import com.renhy.home.model.AqoData;

/**
 * Data Service Implement
 */
@Service
public class AqoDataServiceImpl implements AqoDataService {

    @Autowired
    private AqoDataDao aqoDataDao;

    @Autowired
    private DeviceService deviceService;


    @Scheduled(cron = "")
    private void cleanData() {

    }


    @Override
    public AqoData getLatest(String device) {
        return aqoDataDao.getLatest(device);
    }

    @Override
    public PageInfo<AqoData> list(String device, Date start, Date end, int pageSize, int pageNum) {
        return new PageInfo<>(aqoDataDao.list(device, start, end, pageSize, pageNum));
    }
}
