package com.renhy.home.service;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.renhy.home.dao.AqoDataDao;
import com.renhy.home.model.AqoData;
import com.renhy.home.model.Device;
import com.renhy.home.model.DeviceData;

import lombok.extern.slf4j.Slf4j;

/**
 * Data Service Implement
 */
@Slf4j
@Service
public class AqoDataServiceImpl implements AqoDataService {

    private final static int INTERVAL = 10;

    @Autowired
    private AqoDataDao aqoDataDao;

    @Autowired
    private DeviceService deviceService;

    @PostConstruct
    private void initial() {
        for (Device device : deviceService.listDevice()) {
            AqoData latest = aqoDataDao.getLatest(device.getName());
            if (latest == null) {
                continue;
            }

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(latest.getTs());
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            Long ts = calendar.getTimeInMillis();
            ts += INTERVAL * 90 * 1000;
            while (ts < System.currentTimeMillis()) {
                calculateIntervalData(device.getName(), ts);
                ts += INTERVAL * 60 * 1000;
            }
        }
    }

    @Scheduled(cron = "0 5,15,25,35,45,55 * * * ?")
    private void calculateHistory() {
        log.info("begin calculate history");
        Long ts = System.currentTimeMillis();
        for (Device device : deviceService.listDevice()) {
            calculateIntervalData(device.getName(), ts);
        }
    }

    private void calculateIntervalData(String device, Long ts) {
        log.info("begin calculate data for name={}, ts={}", device, new Date(ts).toString());
        Date start = new Date(ts - INTERVAL * 60 * 1000);
        Date end = new Date(ts);

        PageInfo<DeviceData> datas = deviceService.listData(device, start, end, 0, 1);

        AqoData data = new AqoData();
        data.setDevice(device);
        data.setTs(new Date(ts - INTERVAL * 30 * 1000));

        if (datas.getList() == null || datas.getList().size() == 0) {
            aqoDataDao.saveData(data);
            return;
        }

        Double tem = 0.0;
        Double hum = 0.0;
        Double ch2o = 0.0;
        Double pm10 = 0.0;
        Double pm25 = 0.0;
        Double co2 = 0.0;
        Double tvoc = 0.0;
        int count = 0;
        try {
            for (DeviceData deviceData : datas.getList()) {
                JSONObject content = JSON.parseObject(deviceData.getContent());
                tem += content.getDouble("tem");
                hum += content.getDouble("hum");
                ch2o += content.getDouble("ch2o");
                pm10 += content.getDouble("pm10");
                pm25 += content.getDouble("pm25");
                co2 += content.getDouble("co2");
                tvoc += content.getDouble("tvoc");
                count++;
            }

            data.setTem(tem / count);
            data.setHum(hum / count);
            data.setCh2o(ch2o / count);
            data.setPm10(pm10 / count);
            data.setPm25(pm25 / count);
            data.setCo2(co2 / count);
            data.setTvoc(tvoc / count);
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
