package com.sxops.linfen.common.annotation;

import com.sxops.linfen.common.enums.OpLogMethod;
import com.sxops.linfen.common.enums.OpLogSystem;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> Description: [需要记录操作日志的方法]</p>
 * Created on: 2017/11/6 11:54
 * @author <a href="mailto: liruifeng@sxops.com">尹归晋</a>
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {

    /** 操作描述 **/
    String desc() default "";

    /** 默认记录普通日志 **/
    OpLogMethod method() default OpLogMethod.NORMAL;

    /** 日志系统，默认无需填写，只有在pad调用或app调用的时候填写 **/
    OpLogSystem system() default OpLogSystem.NULL;


}
