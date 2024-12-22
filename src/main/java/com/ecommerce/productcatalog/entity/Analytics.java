package com.ecommerce.productcatalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "tbl_analytics")
public class Analytics {
    @Id
    @Column(name = "objectid", nullable = false, length = 50)
    private String objectid;

    @Column(name = "event", length = 50)
    private String event;

    @Column(name = "productid")
    private Integer productid;

    @Column(name = "timestamp")
    private LocalTime timestamp;

    @Column(name = "message", length = 50)
    private String message;

}