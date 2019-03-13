package com.gameserver.gd.error;

/**
 * The operation is not implemented or is not supported/enabled in this
 * service.
 *
 * HTTP Mapping: 501 Not Implemented
 */
public class UnimplementedException extends ApiException {
    
    public UnimplementedException() {
        super(new ApiError("ERROR_UNIMPLEMENTED", 12000,
                "The operation is not implemented or is not supported/enabled in this service"));
    }

    public UnimplementedException(ApiError error) {
        super(error);
    }
}
