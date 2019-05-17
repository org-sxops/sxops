package com.sxops.www.basicException;

import com.sxops.www.common.enums.APIStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Locale;

@Data
@EqualsAndHashCode(callSuper = true)
public abstract class AbstractBasicException extends RuntimeException {

    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private final Integer code;

    public AbstractBasicException(APIStatus apiStatus) {
        this(apiStatus, null);
    }

    public AbstractBasicException(APIStatus apiStatus, Throwable exception) {
        this(apiStatus.getCode(), apiStatus.getMessage(), exception);
    }

    public AbstractBasicException(Integer code, String message, Throwable exception) {
        super(message, exception);
        this.code = code;
    }


}
