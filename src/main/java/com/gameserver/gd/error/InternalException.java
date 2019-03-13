package com.gameserver.gd.error;

/**
 * Internal errors.  This means that some invariants expected by the
 * underlying system have been broken.  This error code is reserved
 * for serious errors.
 *
 * HTTP Mapping: 500 Internal Server Error
 */
public class InternalException extends ApiException {

    public InternalException() {
        super(new ApiError("ERROR_INTERNAL", 13000, "Internal errors"));
    }

    public InternalException(ApiError error) {
        super(error);
    }
}
