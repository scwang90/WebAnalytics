package com.simpletech.webanalytics.mapper;

import com.ipmapping.BDIP;
import com.ipmapping.TBIP;
import com.ipmapping.TBIP1;
import com.ipmapping.txIP.IPTest;
import com.simpletech.webanalytics.aspect.LoggingAspect;
import com.simpletech.webanalytics.model.IpLocation;
import com.simpletech.webanalytics.model.Visit;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 数据库表t_visit的Mapper层测试类
 *
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class IpLocationMapperTester {

	SimpleDateFormat monthf = new SimpleDateFormat("y-M-d");
	@Autowired
	IpLocationMapper mapper;
	@Autowired
	VisitMapper visitMapper;

	@Before
	public void setUp() {
		LoggingAspect.log = false;
	}

//	@Test
//	public void findAllIp() throws Exception {
//
//		Object result = mapper.findAllIp();
//		System.out.println(JacksonUtil.toJson(result).replace("{", "\n{"));
//	}
	@Test
	public void findAll() throws Exception {

		Object result = visitMapper.findAll("");
		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}



//	@Test
//	public void insert()throws Exception{
//		List<IpLocation> list=mapper.findAllIp();
//		int i=1;
//		for(IpLocation str:list){
//			String ip=str.getIp();
//			/**
//			 * 纯真IP转换
//			 */
//			IPTest txIp=new IPTest();
//			String[] tx_location=txIp.txIpParser(ip);
//			System.out.println("TXTranslate()");
//			//System.out.println("ip=" + ip );
//
//			str.setTxCountry(tx_location[0]);
//			str.setTxProvince(tx_location[1]);
//			str.setTxCity(tx_location[2]);
//			str.setTxDistrick(tx_location[3]);
//			str.setTxIsp(tx_location[4]);
//
//			/**
//			 * 百度转换
//			 */
//			BDIP bd=new BDIP();
//			String[] locate=bd.getIPXY(ip);
//			System.out.println("BDTranslate()");
//			String[] location=bd.getIPLocation(locate[1], locate[0]);
//			str.setBdCountry(location[0].toString());
//			str.setBdProvince(location[1].toString());
//			str.setBdCity(location[2].toString());
//			str.setBdDistrick(location[3].toString());
//			str.setBdIsp(location[4].toString());
//
//
//
//			/**
//			 * 淘宝转换
//			 */
//			TBIP1 tb=new TBIP1();
//			System.out.println("TBTranslate()");
//			//System.out.println("ip=" + ip );
//			String[] tb_location=tb.getTBLocation(ip);
//
//			str.setTbCountry(tb_location[0]);
//			str.setTbProvince(tb_location[1]);
//			str.setTbCity(tb_location[2]);
//			str.setTbDistrick(tb_location[3]);
//			str.setTbIsp(tb_location[4]);
//
//
//			str.setCreateTime(new Date());
//			str.setUpdateTime(new Date());
//			mapper.insert(str);
//
//			System.out.println(i++);
////			update();
//		}
//	}

	@Test
	public void update() throws Exception {

		List<IpLocation> list= mapper.findAll("");
		int i=1;
		for(IpLocation str:list){
			String ip=str.getIp();

			TBIP tb=new TBIP();
			System.out.println("TBTranslate()");
			//System.out.println("ip=" + ip );
			String[] tb_location=tb.getTBLocation(ip);

			str.setTbCountry(tb_location[0]);
			str.setTbProvince(tb_location[1]);
			str.setTbCity(tb_location[2]);
			str.setTbDistrick(tb_location[3]);
			str.setTbIsp(tb_location[4]);
			str.setUpdateTime(new Date());
			mapper.update(str);
			System.out.println(i++);
		}
//		System.out.println(JacksonUtil.toJson(result).replace("{","\n{"));
	}

	@Test
	public void findWhereByPage() throws Exception {

//		Object list= mapper.findVisitWhereByPage("where location_compared is null group by location_ip order by visit_servertime", 5, 0);
//
//		System.out.println(JacksonUtil.toJson(list).replace("{", "\n{"));
	}

}
