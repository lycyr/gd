package com.gameserver.gd.error;

/**
 * The service is currently unavailable.  This is most likely a
 * transient condition, which can be corrected by retrying with
 * a backoff.
 *
 * See the guidelines above for deciding between `FAILED_PRECONDITION`,
 * `ABORTED`, and `UNAVAILABLE`.
 *
 * HTTP Mapping: 503 Service Unavailable
 */
public class UnavailableException extends ApiException {

    public UnavailableException() {
        super(new ApiError("ERROR_UNAVAILABLE", 14000, "The service is currently unavailable"));
    }

    public UnavailableException(ApiError error) {
        super(error);
    }
}
