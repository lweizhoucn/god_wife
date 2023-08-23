package com.lweizhou.cai.godwife.model.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class OrderRequest {
    private Long id;
    private Long clientId;
    private Long skuId;
    private Integer state;
    private Integer orderCount;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sendDate;
}
