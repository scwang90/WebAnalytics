package com.simpletech.webanalytics.format;

import com.simpletech.webanalytics.model.constant.BDIPIspType;
import org.junit.Test;

/**
 * JavaFormat
 * Created by Administrator on 2015/12/17.
 */
public class JavaFormat {

    @Test
    public void testDIPIspType() {
        System.out.println(BDIPIspType.valueOf("11212"));
    }
}
