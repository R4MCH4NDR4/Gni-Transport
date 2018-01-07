package com.example.root.gni_transport.gni.ui.models;

/**
 * Created by root on 7/1/18.
 */

public class Notificatonmodel  {
    public String notification;
    public String timestamp;
    Notificatonmodel(String notification,String timestamp){
        this.notification=notification;
        this.timestamp=timestamp;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
