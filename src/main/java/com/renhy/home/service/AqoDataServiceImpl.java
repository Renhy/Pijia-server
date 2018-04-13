package com.renhy.home.service;

import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.renhy.home.dao.AqoDataDao;
import com.renhy.home.model.AqoData;
import com.renhy.home.model.DeviceData;

import lombok.extern.slf4j.Slf4j;

/**
 * Data Service Implement
 */
@Slf4j
@Service
public class AqoDataServiceImpl implements AqoDataService {

    @Autowired
    private AqoDataDao aqoDataDao;

    @Autowired
    private DeviceService deviceService;

    @PostConstruct
    private void initial() {

    }

    @Scheduled(cron = "0 5,15,25,35,45,55 * * * ?")
    private void calculateHistory() {
        log.info("begin calculate history");
        Long ts = System.currentTimeMillis();

        Date start = new Date(ts - 10 * 60 * 1000);
        Date end = new Date(ts);
        PageInfo<DeviceData> datas = deviceService.listData(null, start, end, 0, 1);

        AqoData data = new AqoData();
        data.setTs(new Date(ts - 5 * 60 * 1000));

        try {

        } catch(Exception e) {
            log.warn("exception: " + e);
        }
        aqoDataDao.saveData(data);
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
