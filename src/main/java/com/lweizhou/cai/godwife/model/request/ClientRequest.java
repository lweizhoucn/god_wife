package com.lweizhou.cai.godwife.model.request;

import com.lweizhou.cai.godwife.model.PageInfo;

import lombok.Data;
import lombok.EqualsAndHashCode;


@EqualsAndHashCode(callSuper = true)
@Data
public class ClientRequest extends PageInfo {
    private Long id;
    private String phoneNumber;
    private int building;
    private int roomNumber;
    private String nickName;
    private int state;
}
