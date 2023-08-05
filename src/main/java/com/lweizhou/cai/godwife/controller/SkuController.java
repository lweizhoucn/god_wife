package com.lweizhou.cai.godwife.controller;

import com.lweizhou.cai.godwife.dao.model.ClientInfo;
import com.lweizhou.cai.godwife.dao.model.OrderInfo;
import com.lweizhou.cai.godwife.dao.model.SkuInfo;
import com.lweizhou.cai.godwife.model.PageResultInfo;
import com.lweizhou.cai.godwife.model.ResultInfo;
import com.lweizhou.cai.godwife.model.request.SkuRequest;
import com.lweizhou.cai.godwife.service.SkuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("sku")
public class SkuController {


    private final SkuService skuService;

    @Autowired
    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @GetMapping("list")
    public ResultInfo<SkuInfo> list(SkuRequest request) {
        return skuService.list(request);
    }

    @PostMapping("add")
    public ResultInfo<SkuInfo> add(@RequestBody SkuRequest request) {
        SkuInfo cl = skuService.save(request);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }

    @PostMapping("update")
    public ResultInfo<ClientInfo> update(@RequestBody SkuRequest request) {

        if (request.getId() == null) {
            return ResultInfo.fail("sku id is null");
        }
        if (!skuService.exist(request.getId())) {
            return ResultInfo.fail("sku id not exist");
        }

        SkuInfo cl = skuService.save(request);
        return cl != null ? ResultInfo.success() : ResultInfo.fail();
    }

    @GetMapping("delete")
    public ResultInfo<ClientInfo> delete(Long id) {
        if (id == null) {
            return ResultInfo.fail("client id is null");
        }
        if (!skuService.exist(id)) {
            return ResultInfo.fail("client id not exist");
        }
        skuService.delete(id);
        return ResultInfo.success();
    }
}
