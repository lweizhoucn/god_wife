package com.lweizhou.cai.godwife.model;

import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ClientResponse extends ClientInfo {

    private Long historyOrderCount;
}
