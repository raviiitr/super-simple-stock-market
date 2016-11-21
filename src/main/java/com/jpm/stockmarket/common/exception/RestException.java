package com.jpm.stockmarket.common.exception;

import com.jpm.stockmarket.common.model.Code;

/**
 * Created by dw1224.kang on 2016-07-19.
 */
public class RestException extends RuntimeException {

    private static final long serialVersionUID = 111988443560228411L;

    protected Code errorCode;

    public RestException() {
        super();
    }

    public RestException(String message) {
        super(message);
    }

    public RestException(Code errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public RestException(Code errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public Code getErrorCode() {
        return errorCode;
    }
}
