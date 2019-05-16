package com.sxops.www.basicException;

import com.sxops.www.common.enums.APIStatus;

public class UserInfoException extends AbstractBasicException {
    public UserInfoException(APIStatus apiStatus) {
        super(apiStatus);
    }
    public UserInfoException(APIStatus apiStatus,Throwable throwable){
        super(apiStatus,throwable);
    }
    public UserInfoException(int code,String message){
            super(code,message,null);
    }
    public UserInfoException(int code,String message,Throwable throwable){
        super(code,message,throwable);
    }
}
