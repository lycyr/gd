package com.gameserver.gd.error;

/**
 * RESTful API 中发生错误时抛出的异常，可被拦截器捕获，
 * 并返回 REST 风格的错误信息 (JSON)
 */
public class ApiException extends Exception {

    public class ResponseBody {

        private ApiError error;

        public ResponseBody(ApiError error) {
            this.error = error;
        }

        public void setError(ApiError error) {
            this.error = error;
        }

        public ApiError getError() {
            return error;
        }
    }

    private ApiError error;

    public ApiException() {}

    public ApiException(ApiError error) {
        this.error = error;
    }

    public void setApiError(ApiError apiError) {
        this.error = apiError;
    }

    public ApiError getApiError() {
        return error;
    }

    public ResponseBody buildResponseBody() {
        return new ResponseBody(error);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
