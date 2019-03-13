package com.gameserver.gd.error;

/**
 * The client specified an invalid argument.  Note that this differs
 * from `FAILED_PRECONDITION`.  `INVALID_ARGUMENT` indicates arguments
 * that are problematic regardless of the state of the system
 * (e.g., a malformed file name).
 *
 * HTTP Mapping: 400 Bad Request
 */
public class InvalidArgumentException extends ApiException {

    public InvalidArgumentException() {
        super(new ApiError(ApiError.ErrorType.ERR_INVALID_ARGUMENT));
    }

    public InvalidArgumentException(String message) {
        super(new ApiError(ApiError.ErrorType.ERR_INVALID_ARGUMENT));
        this.getApiError().setMessage(message);
    }

    public InvalidArgumentException(ApiError error) {
        super(error);
    }
}
