package com.lweizhou.cai.godwife.model.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lweizhou.cai.godwife.model.PageInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
public class SkuRequest extends PageInfo {

    private Long id;
    private String name;
    private Double price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime closeTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate sendDate;
    private String description;
    private Integer state;

}
