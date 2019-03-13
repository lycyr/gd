package com.gameserver.gd.error;

/**
 * Unknown error.  For example, this error may be returned when
 * a `Status` value received from another address space belongs to
 * an error space that is not known in this address space.  Also
 * errors raised by APIs that do not return enough error information
 * may be converted to this error.
 *
 * HTTP Mapping: 500 Internal Server Error
 */
public class UnknownException extends ApiException {

    public UnknownException() {
        super(new ApiError(ApiError.ErrorType.ERR_UNKNOWN));
    }

    public UnknownException(String message) {
        super(new ApiError(ApiError.ErrorType.ERR_UNKNOWN, message));
    }

    public  UnknownException(ApiError error) {
        super(error);
    }
}
