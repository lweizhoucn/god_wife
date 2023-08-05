package com.lweizhou.cai.godwife.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class PageResultInfo<T> extends ResultInfo<T>{
    private int current;
    private int pageSize;
    private long total;

    public static <T> PageResultInfo<T> ofList(List<T> data,long total) {
        PageResultInfo<T> resultInfo = success();
        resultInfo.setData(data);
        resultInfo.total = total;
        return resultInfo;
    }

    public static <T> PageResultInfo<T> success() {
        PageResultInfo<T> resultInfo = new PageResultInfo<>();
        resultInfo.setCode(0);
        resultInfo.setMessage("success");
        return resultInfo;
    }
}
