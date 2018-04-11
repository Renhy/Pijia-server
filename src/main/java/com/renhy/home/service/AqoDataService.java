package com.renhy.home.service;

import java.util.Date;

import com.github.pagehelper.PageInfo;
import com.renhy.home.model.AqoData;

/**
 * Data Service interface
 */
public interface AqoDataService {

//    int save(AqoData data);

    AqoData getLatest(String device);

    PageInfo<AqoData> list(String device, Date start, Date end, int pageSize, int pageNum);

}
