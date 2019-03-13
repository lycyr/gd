package com.gameserver.gd.error;

/**
 * The request does not have valid authentication credentials for the
 * operation.
 *
 * HTTP Mapping: 401 Unauthorized
 */
public class UnauthenticatedException extends ApiException {

    public UnauthenticatedException() {
        super(new ApiError("ERROR_UNAUTHENTICATED", 16000,
                "The request does not have valid authentication credentials for the operation"));
    }

    public UnauthenticatedException(ApiError error) {
        super(error);
    }
}
