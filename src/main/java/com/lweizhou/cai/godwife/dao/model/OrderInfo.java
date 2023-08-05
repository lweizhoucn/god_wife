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
import java.util.Date;

/**
 * 用户下单信息
 */
@Entity
@DynamicUpdate
@Table(name = "order_info")
@EntityListeners(AuditingEntityListener.class)
@Data
public class OrderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name="client_id", updatable=false)
    private ClientInfo clientInfo;

    @ManyToOne()
    @JoinColumn(name="sku_id", updatable=false)
    private SkuInfo skuInfo;

    @Comment("订单数量")
    private int orderCount;

    @Comment("订单价格")
    private double orderPrice;

    /**
     * 订单时间，用于查看当天订单的信息
     */
    @Temporal(TemporalType.DATE)
    private LocalDate orderDate;

    @Temporal(TemporalType.DATE)
    private LocalDate sendDate;

    private int state;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;
    @LastModifiedDate
    private LocalDateTime updateTime;

}
