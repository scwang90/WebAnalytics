package com.simpletech.webanalytics.aspect;

import static java.lang.System.out;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;

import com.simpletech.webanalytics.util.JacksonUtil;

/**
 * 日志处理切面
 * @author 树朾
 * @date 2015-09-21 17:03:53 中国标准时间
 */
@Aspect
public class LoggingAspect {

	public static boolean log = true;
	
	public void before(JoinPoint point) {
		if (log){
			out.print(LoggingAspect.class.getSimpleName()+"-");
			out.print(point.getTarget().getClass().getSimpleName()+".");
			out.print(point.getSignature().getName()+"-begin-args-");
			try {
				out.print(JacksonUtil.toJson(point.getArgs()));
			} catch (Throwable e) {
				// TODO: handle exception
				out.print("[");
				for (Object object : point.getArgs()) {
					out.print(object+",");
				}
				out.print("\b]");
			}
			out.println();
		}
	}
	
	public void after(JoinPoint point) {
		if (log){
			out.print(LoggingAspect.class.getSimpleName()+"-");
			out.print(point.getTarget().getClass().getSimpleName()+".");
			out.print(point.getSignature().getName()+"-after-");
			out.println();
		}
	}
	
	public void returned(JoinPoint point,Object result) {
		if (log){
			out.print(LoggingAspect.class.getSimpleName()+"-");
			out.print(point.getTarget().getClass().getSimpleName()+".");
			out.print(point.getSignature().getName()+"-returned-result-");
			out.print((result instanceof String)?result:JacksonUtil.toJson(result));
			out.println();
		}
	}
	
	public void throwed(JoinPoint point,Throwable ex) {
		if (log){
			out.print(LoggingAspect.class.getSimpleName()+"-");
			out.print(point.getTarget().getClass().getSimpleName()+".");
			out.print(point.getSignature().getName()+"-throwed-ex-");
			out.print(ex.getMessage()+"-"+ex.getClass().getSimpleName());
			out.println();
		}
	}

}
