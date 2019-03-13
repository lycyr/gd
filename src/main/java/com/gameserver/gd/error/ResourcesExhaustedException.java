package com.gameserver.gd.error;

/**
 * Some resource has been exhausted, perhaps a per-user quota, or
 * perhaps the entire file system is out of space.
 *
 * HTTP Mapping: 429 Too Many Requests
 */
public class ResourcesExhaustedException extends ApiException {

    public ResourcesExhaustedException() {
        super(new ApiError("ERROR_RESOURCES_EXHAUSTED", 8000,
                "Some resource has been exhausted, perhaps a per-user quota, or perhaps the entire file system is out of space"));
    }

    public ResourcesExhaustedException(ApiError error) {
        super(error);
    }
}
