package com.simpletech.webanalytics.mapper;

import com.simpletech.webanalytics.model.ShareLinePoint;
import com.simpletech.webanalytics.model.ShareStartPoint;
import com.simpletech.webanalytics.util.JacksonUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * ShareStartPointMapperTest
 * Created by Administrator on 2015/12/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-*.xml")
public class ShareStartPointMapperTest {

    @Autowired
    ShareStartPointMapper mapper;

    @Autowired
    ShareLinePointMapper linePointMapper;

    @Test
    public void setMapper() {
        List<ShareStartPoint> all = mapper.findAll("");
        System.out.println(JacksonUtil.toJson(all).replace("{","\n{"));
        System.out.println();
        for (ShareStartPoint point : all) {
            String where = "WHERE idrefervisitor='%s' AND idurl='%s'";
            List<ShareLinePoint> list = linePointMapper.findWhere("", String.format(where,point.getIdvisitor(),point.getIdurl()));
            System.out.println(JacksonUtil.toJson(list).replace("{","\n{"));
            System.out.println();
            for (ShareLinePoint line : list) {
                line.setIsStartPoint(true);
//                linePointMapper.update(line);
            }
        }
    }

}