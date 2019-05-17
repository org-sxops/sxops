package com.sxops.www.linfen.customException;

import com.sxops.www.basicException.AbstractBasicException;
import com.sxops.www.common.annotation.OpLog;
import com.sxops.www.common.enums.APIStatus;

public class CarInfoException extends AbstractBasicException {
    public CarInfoException(APIStatus apiStatus) {
        super(apiStatus);
    }

    public CarInfoException(APIStatus apiStatus, Throwable throwable) {
        super(apiStatus, throwable);
    }

    public CarInfoException(int code, String message) {
        super(code, message, null);
    }

    public CarInfoException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }



}
