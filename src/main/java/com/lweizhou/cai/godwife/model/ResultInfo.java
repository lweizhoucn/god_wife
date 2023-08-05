package com.lweizhou.cai.godwife.model;

import lombok.Data;

import java.util.Collections;
import java.util.List;


@Data
public class ResultInfo<T> {

    private int code;

    private String message;

    private List<T> data;

    public static <T> ResultInfo<T> of(T date) {
        ResultInfo<T> resultInfo = success();
        resultInfo.data = Collections.singletonList(date);
        return resultInfo;
    }

    public static <T> ResultInfo<T> ofList(List<T> date) {
        ResultInfo<T> resultInfo = success();
        resultInfo.data = date;
        return resultInfo;
    }

    public static <T> ResultInfo<T> success() {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.code = 0;
        resultInfo.message = "success";
        return resultInfo;
    }
    public static <T> ResultInfo<T> fail() {
        return fail("fail");
    }
    public static <T> ResultInfo<T> fail(String message) {
        ResultInfo<T> resultInfo = new ResultInfo<>();
        resultInfo.code = -1;
        resultInfo.message = message;
        return resultInfo;
    }
}
