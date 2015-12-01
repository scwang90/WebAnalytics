package com.simpletech.webanalytics.service.impl;

import com.ipmapping.BDIP;
import com.simpletech.webanalytics.dao.IspDao;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.model.entity.IspValue;
import com.simpletech.webanalytics.service.IspService;
import com.simpletech.webanalytics.service.base.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Service接实现
 * @author 树朾
 * @date 2015-10-15 12:18:28 中国标准时间
 */
@Service
public class IspServiceImpl implements IspService {

	@Autowired
	IspDao dao;

	@Override
	public List<Visit> findWhereIsp(String where) throws Exception {
		List<Visit> visits=dao.findWhereIsp(where);
		List<Visit> newVisit=new ArrayList<>();
//		IPTest txIp = new IPTest();
		for(Visit vv:visits){
			//提取运营商信息
			BDIP bd=new BDIP();
			String isp=bd.getIPXY(vv.getLocationIp())[2];
//			String[] tx_location = txIp.txIpParser(vv.getLocationIp());
//			vv.setLocationIsp(tx_location[4]);
//			vv.setLocationIsp(null);
			vv.setLocationIsp(isp);
			vv.setUpdateTime(new Date());
			dao.updateIsp(vv);
			newVisit.add(vv);
		}
		return newVisit;
	}

	@Override
	public List<IspValue> isp(String siteId, Date start, Date end) throws Exception {
		List<IspValue> values = dao.isp(siteId, start, end);
		List<IspValue> newvalues = new ArrayList<>();
		int count = 0;
		for (IspValue ispValue : values) {
			count += ispValue.getNum();
		}

		for (IspValue isp : values) {
			int num = isp.getNum();
			isp.setRate(1f * isp.getNum() / count);
			newvalues.add(isp);
		}
		return newvalues;
	}
}
