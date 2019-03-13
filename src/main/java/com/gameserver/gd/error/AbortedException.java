package com.gameserver.gd.error;

/**
 * The operation was aborted, typically due to a concurrency issue such as
 * a sequencer check failure or transaction abort.
 *
 * See the guidelines above for deciding between `FAILED_PRECONDITION`,
 * `ABORTED`, and `UNAVAILABLE`.
 *
 * HTTP Mapping: 409 Conflict
 */
public class AbortedException extends ApiException {

    public AbortedException() {
        super(new ApiError("ERROR_ABORTED", 10000,
                "The operation was aborted, typically due to a concurrency issue such as a sequencer check failure or transaction abort"));
    }

    public AbortedException(ApiError error) {
        super(error);
    }
}
