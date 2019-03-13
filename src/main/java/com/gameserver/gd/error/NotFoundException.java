package com.gameserver.gd.error;

/**
 * Some requested entity (e.g., file or directory) was not found.
 *
 * Note to server developers: if a request is denied for an entire class
 * of users, such as gradual feature rollout or undocumented whitelist,
 * `NOT_FOUND` may be used. If a request is denied for some users within
 * a class of users, such as user-based access control, `PERMISSION_DENIED`
 * must be used.
 *
 * HTTP Mapping: 404 Not Found
 */
public class NotFoundException extends ApiException {

    public NotFoundException() {
        super(new ApiError(ApiError.ErrorType.ERR_NOT_FOUND));
    }

    public NotFoundException(String message) {
        super(new ApiError(ApiError.ErrorType.ERR_NOT_FOUND, message));
    }

    public NotFoundException(ApiError error) {
        super(error);
    }
}
