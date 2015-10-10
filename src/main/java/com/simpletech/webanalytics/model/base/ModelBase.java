package com.simpletech.webanalytics.model.base;

import com.simpletech.webanalytics.annotations.Must;
import com.simpletech.webanalytics.util.AfReflecter;
import com.simpletech.webanalytics.util.AfStringUtil;
import com.simpletech.webanalytics.util.ServiceException;

import java.lang.reflect.Field;

/**
 * model 基类
 * @author 树朾
 * @date 2015-09-30 18:37:15 中国标准时间
 */
public class ModelBase {
    /**
     * 检测是否满足必须参数
     */
    public void check() throws Exception{
        Field[] fields = AfReflecter.getFieldAnnotation(this.getClass(), Must.class);
        for (Field field:fields){
            String name = field.getName() + ":" + field.getAnnotation(Must.class).value();
            Object val = AfReflecter.getMemberNoException(this, field.getName());
            if (val == null || AfStringUtil.isEmpty(val.toString())){
                throw new ServiceException("缺少参数["+name+"]");
            }
        }
    }
}
