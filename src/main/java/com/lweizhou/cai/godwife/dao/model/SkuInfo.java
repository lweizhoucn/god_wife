package com.lweizhou.cai.godwife.dao.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "sku_info")
@DynamicUpdate
public class SkuInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Integer state;

    @Comment("商品截至时间")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime closeTime;

    @Comment("商品送达日期")
    @Temporal(TemporalType.DATE)
    private LocalDate sendDate;

    private String description;


    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;


}
