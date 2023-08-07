package com.lweizhou.cai.godwife.controller;

import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.dao.model.OrderInfo;
import com.lweizhou.cai.godwife.model.ResultInfo;
import com.lweizhou.cai.godwife.model.request.OrderRequest;
import com.lweizhou.cai.godwife.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("order")
@RestController
public class OrderController {

    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("list")
    public ResultInfo<OrderInfo> list() {
        List<OrderInfo> orderInfoList = orderService.list();
        return ResultInfo.ofList(orderInfoList);
    }

    @PostMapping("add")
    public ResultInfo<OrderInfo> add(@RequestBody OrderRequest orderInfo) {
        OrderInfo cl = orderService.add(orderInfo);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }

    @PostMapping("update")
    public ResultInfo<OrderInfo> update(@RequestBody OrderRequest orderInfo) {
        OrderInfo cl = orderService.add(orderInfo);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }

    @PostMapping("delete")
    public ResultInfo<OrderInfo> delete(@RequestBody OrderRequest orderInfo) {
        OrderInfo cl = orderService.add(orderInfo);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }
}
