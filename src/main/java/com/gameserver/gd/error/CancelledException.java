package com.gameserver.gd.error;

/**
 * The operation was cancelled, typically by the caller.
 *
 * HTTP Mapping: 499 Client Closed Request
 */
public class CancelledException extends ApiException {

    public CancelledException() {
        super(new ApiError("ERROR_CANCELLED", 1000, "The operation was cancelled"));
    }

    public CancelledException(ApiError error) {
        super(error);
    }
}
