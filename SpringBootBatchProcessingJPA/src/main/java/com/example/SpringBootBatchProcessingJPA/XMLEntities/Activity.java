package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import com.example.SpringBootBatchProcessingJPA.Config.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;
import java.util.List;

@XmlRootElement(name = "activity")
public class Activity {

    private String lastIp;

    private Integer loginsCount;

    private LocalDateTime lastLogin;

    private DeviceXmlEntityList deviceXmlEntityList;

    @XmlElement(name = "last_ip")
    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    @XmlElement(name = "logins_count")
    public Integer getLoginsCount() {
        return loginsCount;
    }

    public void setLoginsCount(Integer loginsCount) {
        this.loginsCount = loginsCount;
    }

    @XmlElement(name = "last_login")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    @XmlElement(name = "devices")
    public DeviceXmlEntityList getDeviceXmlEntityList() {
        return deviceXmlEntityList;
    }

    public void setDeviceXmlEntityList(DeviceXmlEntityList deviceXmlEntityList) {
        this.deviceXmlEntityList = deviceXmlEntityList;
    }
}
