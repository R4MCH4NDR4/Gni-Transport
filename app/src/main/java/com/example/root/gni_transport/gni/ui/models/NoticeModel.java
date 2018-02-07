package com.example.root.gni_transport.gni.ui.models;

/**
 * Created by ram on 7/1/18.
 */

public class NoticeModel {
    private   String message;
    private String timestamp;
    public NoticeModel(String message,String timestamp)
    {
        this.message=message;
        this.timestamp=timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
