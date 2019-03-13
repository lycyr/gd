package com.gameserver.gd.error;

/**
 * The entity that a client attempted to create (e.g., file or directory)
 * already exists.
 *
 * HTTP Mapping: 409 Conflict
 */
public class AlreadyExistsException extends ApiException {

    public AlreadyExistsException() {
        super(new ApiError("ERROR_ALREADY_EXISTS", 6000,
                "The entity that a client attempted to create"));
    }

    public AlreadyExistsException(ApiError error) {
        super(error);
    }
}
