package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "devices")
public class DeviceXmlEntityList {

    List<DeviceXmlEntity> deviceXmlEntityList;

    @XmlElement(name =  "device")
    public List<DeviceXmlEntity> getDeviceXmlEntityList() {
        return deviceXmlEntityList;
    }

    public void setDeviceXmlEntityList(List<DeviceXmlEntity> deviceXmlEntityList) {
        this.deviceXmlEntityList = deviceXmlEntityList;
    }
}
