package com.example.demo.entity;

import com.example.demo.utils.CustomLocalDateTimeDeserializer;
import com.example.demo.utils.LocalDateTimeConverter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "Process_Complex_Json")
public class ProcessComplexJsonData {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen")
    @SequenceGenerator(name = "seq_gen", sequenceName = "SEQ_Process_Complex_Json_Data", allocationSize = 1)
    private int id;

    @JsonProperty("user_id")
    private String userId;

    @Column(name = "EMAIL")
    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("created_at")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonFormat(pattern = "dd-MM-yyyy h:mm:ss a")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonFormat(pattern = "dd-MM-yyyy h:mm:ss a")
    private LocalDateTime updatedAt;

    @JsonProperty("email_verified")
    private boolean emailVerified;

    @Embedded
    @JsonProperty("personal_info")
    private PersonalInfo personalInfo;

    @Embedded
    @JsonProperty("activity")
    private Activity activity;

    @Embedded
    @JsonProperty("preferences")
    private Preferences preferences;
}

@Embeddable
@Data
class PersonalInfo {

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("nickname")
    private String nickname;
}

@Embeddable
@Data
class Activity {

    @JsonProperty("last_ip")
    private String lastIp;

    @JsonProperty("logins_count")
    private int loginsCount;

    @JsonProperty("last_login")
    @JsonDeserialize(using = CustomLocalDateTimeDeserializer.class)
    @Convert(converter = LocalDateTimeConverter.class)
    @JsonFormat(pattern = "dd-MM-yyyy h:mm:ss a")
    private LocalDateTime lastLogin;

    @ElementCollection
    @JsonProperty("devices")
    @CollectionTable(
            name = "Activity_devices",
            joinColumns = @JoinColumn(name = "activity_id", foreignKey = @ForeignKey(name = "FKI_ACTIVITY_DEVICE"))
    )
    private List<Devices> devices;
}

@Embeddable
@Data
class Devices {

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("os")
    private String os;
}

@Embeddable
@Data
class Preferences {

    @JsonProperty("language")
    private String language;

    @JsonProperty("timezone")
    private String timezone;

    @Embedded
    @JsonProperty("notifications")
    private Notifications notifications;
}

@Embeddable
@Data
class Notifications {

    @Column(name = "IS_EMAIL_ENABLED")
    @JsonProperty("email")
    private boolean email;

    @JsonProperty("sms")
    private boolean sms;
}
