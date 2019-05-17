package com.sxops.www.linfen.customException;

import com.sxops.www.basicException.AbstractBasicException;
import com.sxops.www.common.enums.APIStatus;

public class UserInfoException extends AbstractBasicException {
    public UserInfoException(APIStatus apiStatus) {
        super(apiStatus);
    }

    public UserInfoException(APIStatus apiStatus, Throwable throwable) {
        super(apiStatus, throwable);
    }

    public UserInfoException(int code, String message) {
        super(code, message, null);
    }

    public UserInfoException(int code, String message, Throwable throwable) {
        super(code, message, throwable);
    }


}
