package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "Process_Json_Data")
public class ProcessJsonData {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gen_json")
    @SequenceGenerator(name = "seq_gen_json", sequenceName = "SEQ_Process_Json_Data", allocationSize = 1)
    private int id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("given_name")
    private String givenName;

    @JsonProperty("family_name")
    private String familyName;

    @JsonProperty("nickname")
    private String nickname;

    @JsonProperty("last_ip")
    private String lastIp;

    @JsonProperty("logins_count")
    private Integer loginsCount;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("last_login")
    private String lastLogin;

    @JsonProperty("email_verified")
    private Boolean emailVerified;
}
