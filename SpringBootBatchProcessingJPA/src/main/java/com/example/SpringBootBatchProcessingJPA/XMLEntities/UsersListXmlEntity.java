package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.util.List;

@XmlRootElement(name = "userdata")
public class UsersListXmlEntity {

    private List<UserXmlEntity> userXmlEntity;

    @XmlElement(name = "user")
    public List<UserXmlEntity> getUserXmlEntity() {
        return userXmlEntity;
    }

    public void setUserXmlEntity(List<UserXmlEntity> userXmlEntity) {
        this.userXmlEntity = userXmlEntity;
    }
}
