package com.simpletech.webanalytics.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 意图，在Controller使用
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface Intent {
	public String value();
}
