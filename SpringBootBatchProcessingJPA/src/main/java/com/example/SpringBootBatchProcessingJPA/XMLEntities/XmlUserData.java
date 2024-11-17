package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Table(name = "XML_USERS_DATA")
@Data
@ToString
public class XmlUserData {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
    @SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_XML_USER_DATA", allocationSize = 1)
    private int Id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "given_name")
    private String givenName;

    @Column(name = "family_name")
    private String familyName;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @Column(name = "email_verified")
    private Boolean emailVerified;

    @Column(name = "last_ip")
    private String lastIp;

    @Column(name = "logins_count")
    private Integer loginsCount;

    @Column(name = "last_login")
    private LocalDateTime lastLogin;

    @Column(name = "language")
    private String language;

    @Column(name = "timezone")
    private String timezone;

    @Column(name = "notifications_email")
    private Boolean notificationsEmail;

    @Column(name = "notifications_sms")
    private Boolean notificationsSms;
}
