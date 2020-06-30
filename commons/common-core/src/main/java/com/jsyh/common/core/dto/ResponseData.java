package com.jsyh.common.core.dto;

public class ResponseData<T> {

    private boolean success;

    private String message; //消息

    private String code;

    private T result; //返回的数据

    

    @Override
	public String toString() {
		return "ResponseData [success=" + success + ", message=" + message + ", code=" + code + ", result=" + result
				+ ", timestamp=" + timestamp + "]";
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 时间戳
     */
    private long timestamp = System.currentTimeMillis();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}

