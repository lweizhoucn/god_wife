package com.lweizhou.cai.godwife.dao.model;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@DynamicUpdate
@Table(name = "client_info")
@Data
@EntityListeners(AuditingEntityListener.class)
public class ClientInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String phoneNumber;

    /**
     * 楼栋
     */
    private String building;

    /**
     * 房号
     */
    private String roomNumber;

    /**
     * 客户昵称
     */
    @Column(length = 50)
    private String nickName;

    @Comment("状态")
    @Column(nullable = false)
    private int state;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createTime;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updateTime;

}
