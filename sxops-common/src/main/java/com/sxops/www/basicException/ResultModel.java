package com.sxops.www.basicException;

import com.sxops.www.common.enums.APIStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ResultModel<T> {
    /** 是否执行成功 */
    private boolean statusFlag = true;

    /** 具体内容 */
    private T data;

    /** 错误码 */
    private String code = "200";

    /** 提示信息 */
    private String msg = "成功";

    private Status status;
    public ResultModel(int code, String message, boolean statusFlag, T data) {
        super();
        Status statu = new Status();
        statu.setCode(code);
        statu.setMessage(message);
        this.status = statu;
        this.code = Integer.toString((int) code);
        this.msg = message;
        this.statusFlag = statusFlag;
        this.data = data;
    }

    public static ResultModel success() {
        return success(null);
    }


    public static <T> ResultModel<T> success(T data) {
        return new ResultModel<>(APIStatus.SUCESS.getCode(), APIStatus.SUCESS.getMessage(), true, data);
    }
    public static <T> ResultModel<T> success(APIStatus apiStatus, T data) {
        return new ResultModel<>(apiStatus.getCode(), apiStatus.getMessage(), true, data);
    }

    public static ResultModel error(APIStatus apiStatus) {
        return error(apiStatus.getCode(), apiStatus.getMessage());
    }

    public static ResultModel error(int code, String message) {
        return error(code, message, null);
    }

    public static <T> ResultModel<T> error(int code, String message, T data) {
        return new ResultModel<>(code, message, false, data);
    }
}

