package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "notifications")
public class Notifications {

    private Boolean notificationsEmail;

    private Boolean notificationsSms;

    @XmlElement(name = "email")
    public Boolean getNotificationsEmail() {
        return notificationsEmail;
    }

    public void setNotificationsEmail(Boolean notificationsEmail) {
        this.notificationsEmail = notificationsEmail;
    }

    @XmlElement(name = "sms")
    public Boolean getNotificationsSms() {
        return notificationsSms;
    }

    public void setNotificationsSms(Boolean notificationsSms) {
        this.notificationsSms = notificationsSms;
    }
}
