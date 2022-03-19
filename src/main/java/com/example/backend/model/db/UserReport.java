package com.example.backend.model.db;

import com.example.backend.model.shared.UserReportType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "user_reports", schema= "licenta")
public class UserReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reporter")
    private String reporter;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private UserReportType type;

    @Column(name = "lat")
    private Double lat;

    @Column(name = "lng")
    private Double lng;

    @Column(name = "timestamp")
    private Timestamp timestamp;
}
