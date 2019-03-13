package com.gameserver.gd.error;

/**
 * The deadline expired before the operation could complete. For operations
 * that change the state of the system, this error may be returned
 * even if the operation has completed successfully.  For example, a
 * successful response from a server could have been delayed long
 * enough for the deadline to expire.
 *
 * HTTP Mapping: 504 Gateway Timeout
 */
public class DeadlineExceededException extends ApiException {

    public DeadlineExceededException() {
        super(new ApiError("ERROR_DEADLINE_EXCEEDED", 4000,
                "The deadline expired before the operation could complete"));
    }

    public DeadlineExceededException(ApiError error) {
        super(error);
    }
}
