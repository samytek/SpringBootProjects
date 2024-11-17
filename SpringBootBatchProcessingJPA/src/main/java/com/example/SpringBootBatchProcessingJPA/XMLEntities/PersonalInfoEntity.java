package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "personal_info")
public class PersonalInfoEntity {

    private String givenName;

    private String familyName;

    private String nickname;

    @XmlElement(name = "given_name")
    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    @XmlElement(name = "family_name")
    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    @XmlElement(name = "nickname")
    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
