package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import  com.example.SpringBootBatchProcessingJPA.Config.LocalDateTimeAdapter;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDateTime;

@XmlRootElement(name = "user")
public class UserXmlEntity {

    private String userId;

    private String email;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Boolean emailVerified;

    private PersonalInfoEntity personalInfoEntity;

    private Activity activity;

    private Preferences preferences;

    @XmlElement(name = "user_id")

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @XmlElement(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlElement(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement(name = "created_at")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @XmlElement(name = "updated_at")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @XmlElement(name = "email_verified")
    public Boolean getEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(Boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    @XmlElement(name = "personal_info")
    public PersonalInfoEntity getPersonalInfoEntity() {
        return personalInfoEntity;
    }

    public void setPersonalInfoEntity(PersonalInfoEntity personalInfoEntity) {
        this.personalInfoEntity = personalInfoEntity;
    }

    @XmlElement(name = "activity")
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @XmlElement(name = "preferences")
    public Preferences getPreferences() {
        return preferences;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}

