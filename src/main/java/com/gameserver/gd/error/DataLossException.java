package com.gameserver.gd.error;

/**
 * Unrecoverable data loss or corruption.
 *
 * HTTP Mapping: 500 Internal Server Error
 */
public class DataLossException extends ApiException {

    public DataLossException() {
        super(new ApiError("ERROR_DATA_LOSS", 15000, "Unrecoverable data loss or corruption"));
    }

    public DataLossException(ApiError error) {
        super(error);
    }
}
