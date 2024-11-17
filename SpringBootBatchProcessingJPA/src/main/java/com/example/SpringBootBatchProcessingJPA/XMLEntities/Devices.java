package com.example.SpringBootBatchProcessingJPA.XMLEntities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "XML_Activity_devices")
@Data
public class Devices {

    @jakarta.persistence.Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orcl_seq_gen")
    @SequenceGenerator(name = "orcl_seq_gen", sequenceName = "SEQ_XML_Activity_devices", allocationSize = 1)
    private int Id;

    private int activityId;
    private String deviceId;
    private String type;
    private String os;
}
