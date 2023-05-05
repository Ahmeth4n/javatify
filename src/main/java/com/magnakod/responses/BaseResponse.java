package com.magnakod.responses;

import com.google.gson.annotations.SerializedName;

public class BaseResponse {
    @SerializedName("status")
    private boolean status;
    @SerializedName("message")
    private String message;

    public BaseResponse(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
