package com.gameserver.gd.error;

/**
 * RESTful API 错误信息的数据模型。
 */
public class ApiError {

    /**
     * 定义错误枚举量，以统一错误信息
     */
    public enum ErrorType {

        // 代码 0xxxxx: 全项目通用错误码
        ERR_UNKNOWN(2000, "Unknown error"),
        ERR_INVALID_ARGUMENT(3000, "The client specified an invalid argument"),
        ERR_MISSING_PARAMETER(3001, "Parameter required is missing"),
        ERR_INVALID_METHOD_ARGUMENT(3002, "Argument is invalid"),
        ERR_NOT_FOUND(5000, "Some requested entity was not found"),
        ERR_PERMISSION_DENIED(7000, "The caller does not have permission to execute the specified operation"),
        ERR_FAILED_PRECONDITION(9000, "The operation was rejected because the system is not in a state required for the operation's execution"),
        ERR_CONSTRAINT_VIOLATION(9001, "The operation violation database constraint"),
        ERR_LOGIN_REQUIRED(16001, "Login to execute the operation"),

        // 代码 2xxxxx: 身份验证子系统专用错误码
        ERR_INCORRECT_USERNAME_OR_PASSWORD(216000, "Username or password is incorrect"),
        ERR_INVALID_USERNAME(203001, "Password is invalid");

        private Integer code;
        private String message;

        ErrorType(int code, String message) {
            this.code = code;
            this.message = message;
        }

        public String getName() {
            return toString();
        }

        public String getMessage() {
            return message;
        }

        public Integer getCode() {
            return code;
        }
    }

    private String name;
    private Integer code;
    private String message;

    public ApiError() {}

    public ApiError(String name, Integer code, String message) {
        this.name = name;
        this.code = code;
        this.message = message;
    }

    /**
     * 可传递一个 {@code ErrorType} 的枚举常量来初始化错误信息
     * @param errorType 用于初始化 {@code ApiError} 的枚举常量
     */
    public ApiError(ErrorType errorType) {
        this.name = errorType.getName();
        this.code = errorType.getCode();
        this.message = errorType.getMessage();
    }

    /**
     * 可传递一个 {@code ErrorType} 的枚举常量来初始化错误信息，同时
     * 传入 {@code message} 以更改 {@code errorType}
     * 中定义的错误消息
     * @param errorType 用于初始化 {@code ApiError} 的枚举常量
     * @param message 要自定义
     */
    public ApiError(ErrorType errorType, String message) {
        this.name = errorType.getName();
        this.code = errorType.getCode();
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
