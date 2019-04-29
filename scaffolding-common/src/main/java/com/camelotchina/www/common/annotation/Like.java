package com.camelotchina.www.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p> Description: [构造Example时，此字段为like]</p>
 * Created on: 2017/11/6 12:02
 *
 * @author <a href="mailto: liruifeng@camelotchina.com">尹归晋</a>
 * @version 1.0
 */
@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Like {
}
