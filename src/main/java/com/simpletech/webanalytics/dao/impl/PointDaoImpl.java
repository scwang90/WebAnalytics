package com.simpletech.webanalytics.dao.impl;

import com.ipmapping.BDIP;
import com.ipmapping.cnIP.IPCalculate;
import com.simpletech.webanalytics.dao.IPDao;
import com.simpletech.webanalytics.dao.PointDao;
import com.simpletech.webanalytics.mapper.IPMapper;
import com.simpletech.webanalytics.mapper.PointMapper;
import com.simpletech.webanalytics.model.IP;
import com.simpletech.webanalytics.model.IpLocation;
import com.simpletech.webanalytics.model.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * WebAnalytics
 * Created by ChenQi on 2015/12/11 16:04.
 */
@Repository
public class PointDaoImpl implements PointDao {
    @Autowired
    PointMapper mapper;

    @Override
    public List<Point> findPoint(String id){
        return mapper.findPoint(id);
    }
}
