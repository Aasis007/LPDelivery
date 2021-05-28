package com.Islington.lpdelivery.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestOrderResponse {

        @SerializedName("status")
        @Expose
        private String status;

        @SerializedName("message")
        @Expose
        private String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
