package com.simpletech.webanalytics.dao.impl;

import java.util.Date;
import java.util.List;

import com.ipmapping.BDIP;
import com.ipmapping.TBIP1;
import com.ipmapping.txIP.IPTest;
import com.simpletech.webanalytics.mapper.IpLocationMapper;
import com.simpletech.webanalytics.mapper.VisitMapper;
import com.simpletech.webanalytics.model.IpLocation;
import com.simpletech.webanalytics.model.entity.IspValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.simpletech.webanalytics.dao.base.BaseDaoImpl;
import com.simpletech.webanalytics.dao.VisitDao;
import com.simpletech.webanalytics.model.Visit;

/**
 * 数据库表t_visit的Dao实现
 * @author 树朾
 * @date 2015-10-13 10:15:55 中国标准时间
 */
@Repository
public class VisitDaoImpl extends BaseDaoImpl<Visit> implements VisitDao{

	@Autowired
	VisitMapper visitMapper;
	@Autowired
	IpLocationMapper ipLocationMapper;
	@Override
	public int insert(Visit t) {
		return super.insert(t);
	}

	@Override
	public int update(Visit t) {
		return super.update(t);
	}

	@Override
	public int delete(Object id) {
		return super.delete(id);
	}

	@Override
	public int countAll() {
		return super.countAll();
	}

	@Override
	public Visit findById(Object id) {
		return super.findById(id);
	}

	@Override
	public List<Visit> findAll() {
		return super.findAll();
	}

	@Override
	public List<Visit> findByPage(int limit, int start) {
		return super.findByPage(limit, start);
	}

//	@Override
//	public List<Visit> findWhereByPage(String where,int limit, int start) {
//		return super.findWhereByPage(where,limit, start);
//	}

	//	@Override
//	public int updateCompared(Visit t) {
//		List<IpLocation> list=ipLocationMapper.findAllIp();
//		List<Visit> visitlist=visitMapper.findAll("");
//
//		int i = 1;
//		for (IpLocation str : list) {
//			String ip = str.getIp();
////			/**
////			 * 纯真IP转换
////			 */
////			IPTest txIp = new IPTest();
////			String[] tx_location = txIp.txIpParser(ip);
////			System.out.println("TXTranslate()");
////			//System.out.println("ip=" + ip );
////
////			str.setTxCountry(tx_location[0]);
////			str.setTxProvince(tx_location[1]);
////			str.setTxCity(tx_location[2]);
////			str.setTxDistrick(tx_location[3]);
////			str.setTxIsp(tx_location[4]);
////
////			/**
////			 * 百度转换
////			 */
////			BDIP bd = new BDIP();
////			String[] locate = bd.getIPXY(ip);
////			System.out.println("BDTranslate()");
////			String[] location = bd.getIPLocation(locate[1], locate[0]);
////			str.setBdCountry(location[0].toString());
////			str.setBdProvince(location[1].toString());
////			str.setBdCity(location[2].toString());
////			str.setBdDistrick(location[3].toString());
////			str.setBdIsp(location[4].toString());
////
////
////			/**
////			 * 淘宝转换
////			 */
////			TBIP1 tb=new TBIP1();
////			System.out.println("TBTranslate()");
////			//System.out.println("ip=" + ip );
////			String[] tb_location=tb.getTBLocation(ip);
////
////			str.setTbCountry(tb_location[0]);
////			str.setTbProvince(tb_location[1]);
////			str.setTbCity(tb_location[2]);
////			str.setTbDistrick(tb_location[3]);
////			str.setTbIsp(tb_location[4]);
////
////
////			str.setCreateTime(new Date());
////			str.setUpdateTime(new Date());
////			ipLocationService.insert(str);
//
//			//更新t_visit中location_compared字段
//			for(Visit ss:visitlist){
//				if(ss.getLocationIp().equals(ip)){
//					ss.setLocationCompared(true);
//					visitMapper.updateCompared(ss);
//				}
//			}
//			System.out.println(i++);
//		}
//		return i;
//	}

}

